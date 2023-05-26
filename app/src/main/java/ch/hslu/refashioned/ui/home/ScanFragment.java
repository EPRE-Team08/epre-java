package ch.hslu.refashioned.ui.home;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ch.hslu.refashioned.databinding.FragmentScanBinding;
import ch.hslu.refashioned.ui.permission.PermissionManager;

public class ScanFragment extends Fragment {

    private ScanViewModel scanViewModel;
    private FragmentScanBinding binding;
    private ImageCapture imageCapture;
    private ExecutorService cameraExecutor;

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
        return binding.getRoot();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cameraExecutor.shutdown();
        binding = null;
    }
}