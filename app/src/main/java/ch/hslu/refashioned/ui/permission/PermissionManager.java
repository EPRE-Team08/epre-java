package ch.hslu.refashioned.ui.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {
    private final ActivityResultLauncher<String[]> activityResultLauncher;
    private final Fragment fragment;
    private final String[] requiredPermissions;
    private final Runnable onPermissionGrantedCallback;

    private PermissionManager(Fragment fragment, String[] requiredPermissions, Runnable onPermissionGrantedCallback) {
        this.fragment = fragment;
        this.requiredPermissions = requiredPermissions;
        this.onPermissionGrantedCallback = onPermissionGrantedCallback;
        this.activityResultLauncher = this.initActivityResultLauncher();
    }

    public boolean arePermissionsGranted() {
        return arePermissionsGranted(List.of(requiredPermissions), fragment.requireActivity());
    }

    public void requestPermission() {
        if (arePermissionsGranted() && onPermissionGrantedCallback != null) {
            onPermissionGrantedCallback.run();
        } else {
            this.activityResultLauncher.launch(requiredPermissions);
        }
    }

    private static boolean arePermissionsGranted(List<String> requiredPermissions, @NotNull Context context) {
        return requiredPermissions.stream().allMatch(permission -> ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    private ActivityResultLauncher<String[]> initActivityResultLauncher() {
        return fragment.registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (result.values().stream().allMatch(granted -> granted) && arePermissionsGranted() && onPermissionGrantedCallback != null) {
                onPermissionGrantedCallback.run();
            }
        });
    }

    public static class Builder implements PermissionManagerBuilderContext, PermissionManagerBuilderPermissions, PermissionManagerBuilderOptional {
        private final List<String> requiredPermissions = new ArrayList<>();
        private Runnable onPermissionGrantedCallback;
        private Fragment fragment;

        private Builder() {
        }

        public static PermissionManagerBuilderContext getInstance() {
            return new Builder();
        }

        @Override
        public PermissionManagerBuilderOptional requiredPermissions(String... permissions) {
            requiredPermissions.addAll(List.of(permissions));
            return this;
        }

        @Override
        public PermissionManagerBuilderOptional permissionsUntilVersion(int versionCode, String... permissions) {
            if (Build.VERSION.SDK_INT <= versionCode) {
                requiredPermissions.addAll(List.of(permissions));
            }
            return this;
        }

        @Override
        public PermissionManagerBuilderOptional onPermissionGranted(Runnable runnable) {
            onPermissionGrantedCallback = runnable;
            return this;
        }

        @Override
        public PermissionManager build() {
            return new PermissionManager(fragment, requiredPermissions.toArray(new String[0]), onPermissionGrantedCallback);
        }

        @Override
        public PermissionManagerBuilderPermissions forFragment(@NonNull Fragment fragment) {
            this.fragment = fragment;
            return this;
        }
    }
}
