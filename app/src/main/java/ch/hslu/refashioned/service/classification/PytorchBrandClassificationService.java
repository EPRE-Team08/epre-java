package ch.hslu.refashioned.service.classification;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.pytorch.IValue;
import org.pytorch.LiteModuleLoader;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import ch.hslu.refashioned.database.converter.BrandConverter;
import ch.hslu.refashioned.model.history.Brand;

public class PytorchBrandClassificationService implements BrandClassificationService {
    private final String MODEL_PATH = "model_cpu_v2.ptl";
    private final float PRECISION = 0.7f;
    private final Context context;

    public PytorchBrandClassificationService(Context context) {
        this.context = context;
    }

    @Override
    public void classifyBrand(Bitmap bitmap, Consumer<Brand> callback) {
        Brand brand;
        try {
            Module module = LiteModuleLoader.load(assetFilePath(MODEL_PATH));
            Tensor inputTensor = convertBitmapToTensor(bitmap);
            Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();
            brand = getBrandFromTensor(outputTensor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        callback.accept(brand);
    }

    private Brand getBrandFromTensor(Tensor outputTensor) {
        float[] scores = outputTensor.getDataAsFloatArray();
        double[] mappedScores = IntStream.range(0, scores.length).mapToDouble(index -> scores[index]).toArray();
        Log.e("PytorchBrandClassificationService", "0: " + scores[0] + " 1: " + scores[1] + " 2: " + scores[2]);
        int min = (int) Math.floor(DoubleStream.of(mappedScores).min().orElse(0));
        int max = (int) Math.ceil(DoubleStream.of(mappedScores).max().orElse(0));
        int add = IntStream.of(min, max).map(Math::abs).max().orElse(0);
        double[] positiveScores = DoubleStream.of(mappedScores).map(score -> score + add).toArray();
        double[] normalizedScores = DoubleStream.of(positiveScores).map(value -> softmax(value, positiveScores)).toArray();
        Log.e(PytorchBrandClassificationService.class.getName(), Arrays.toString(normalizedScores));
        int maxIndex = IntStream.range(0, scores.length)
                .reduce((i, j) -> normalizedScores[i] > normalizedScores[j] ? i : j)
                .orElse(-1);

        if (maxIndex == -1 || normalizedScores[maxIndex] < PRECISION) {
            return null;
        } else {
            return BrandConverter.from(maxIndex);
        }
    }

    private double softmax(double input, double[] values) {
        double total = DoubleStream.of(values).map(Math::exp).sum();
        return Math.exp(input) / total;
    }

    private Tensor convertBitmapToTensor(Bitmap bitmap) {
        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
        return TensorImageUtils.bitmapToFloat32Tensor(bitmap, TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);
    }

    /**
     * Copies specified asset to the file in /files app directory and returns this file absolute path.
     *
     * @return absolute file path
     */
    public String assetFilePath(String assetName) throws IOException {
        File file = new File(context.getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        }
    }
}
