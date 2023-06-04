package ch.hslu.refashioned.service.classification;

import android.graphics.Bitmap;

import java.util.function.Consumer;

import ch.hslu.refashioned.model.history.Brand;

public interface BrandClassificationService {
    void classifyBrand(Bitmap bitmap, Consumer<Brand> callback);
}
