package ch.hslu.refashioned.ui.home;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.hslu.refashioned.databinding.FragmentScanBinding;
import ch.hslu.refashioned.model.history.Brand;
import ch.hslu.refashioned.ui.permission.PermissionManager;
import ch.hslu.refashioned.ui.scanInfo.ScanInfoActivity;

public class ScanFragment extends Fragment {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private ScanViewModel scanViewModel;
    private FragmentScanBinding binding;
    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;

    private final ImageCapture.OnImageSavedCallback imageSavedCallback = new ImageCapture.OnImageSavedCallback() {
        @Override
        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
            scanViewModel.setSavedUri(outputFileResults.getSavedUri());
            //ToDo classify image
            startScanInfoActivity();
        }

        @Override
        public void onError(@NonNull ImageCaptureException exception) {
            Toast.makeText(requireContext(), "Error occurred, could not take photo!", Toast.LENGTH_LONG).show();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        scanViewModel = new ViewModelProvider(this).get(ScanViewModel.class);
        binding = FragmentScanBinding.inflate(inflater, container, false);
        imageCapture = new ImageCapture.Builder().build();
        cameraExecutor = Executors.newSingleThreadExecutor();

        var permissionManager = PermissionManager.Builder.getInstance()
                .forFragment(this)
                .requiredPermissions(Manifest.permission.CAMERA)
                .permissionsUntilVersion(Build.VERSION_CODES.P, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onPermissionGranted(this::startCamera)
                .build();
        permissionManager.requestPermission();

        binding.imageCaptureButton.setOnClickListener(this::onImageCaptureClicked);
        return binding.getRoot();
    }

    private void onImageCaptureClicked(View view) {
        if (imageCapture == null) {
            return;
        }
        ContentValues contentValues = buildImageContentValues();
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions
                .Builder(requireContext().getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                .build();

        imageCapture.takePicture(
                outputFileOptions,
                ContextCompat.getMainExecutor(requireContext()),
                imageSavedCallback);
    }

    private ContentValues buildImageContentValues() {
        String filename = new SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ReFashioned");
        }
        return contentValues;
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProvider = ProcessCameraProvider.getInstance(requireContext());
        cameraProvider.addListener(() -> showCameraOnPreview(cameraProvider), ContextCompat.getMainExecutor(requireContext()));
    }

    private void showCameraOnPreview(ListenableFuture<ProcessCameraProvider> cameraProvider) {
        Preview preview = initPreview();
        try {
            ProcessCameraProvider cameraProviderInstance = cameraProvider.get();
            cameraProviderInstance.unbindAll();
            cameraProviderInstance.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA, preview, imageCapture);
        } catch (ExecutionException | InterruptedException e) {
            Log.e(ScanFragment.class.getName(), "Use case binding failed", e);
        }
    }

    private Preview initPreview() {
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(binding.viewFinder.getSurfaceProvider());
        return preview;
    }

    private void startScanInfoActivity() {
        Intent intent = new Intent(requireActivity(), ScanInfoActivity.class);
        intent.putExtra(ScanInfoActivity.EXTRA_BRAND_VALUE, Brand.PUMA.getValue());
        intent.putExtra(ScanInfoActivity.EXTRA_IMAGE_URI, scanViewModel.getSavedUri());
        requireActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cameraExecutor.shutdown();
        binding = null;
    }
}