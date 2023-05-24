package ch.hslu.refashioned.ui.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PermissionManager {
    private final Context context;
    private final String[] requiredPermissions;

    public PermissionManager(Context context, String[] requiredPermissions) {
        this.context = context;
        this.requiredPermissions = requiredPermissions;
    }

    public boolean arePermissionsGranted() {
        return Stream.of(requiredPermissions).allMatch(permission -> ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    public static class Builder implements PermissionManagerBuilderContext, PermissionManagerBuilderPermissions, PermissionManagerBuilderOptional {
        List<String> requiredPermissions = new ArrayList<>();
        private Context context;

        public Builder() {
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
        public PermissionManager build() {
            return new PermissionManager(context, requiredPermissions.toArray(new String[0]));
        }

        @Override
        public PermissionManagerBuilderPermissions withContext(@NonNull Context context) {
            this.context = context;
            return this;
        }
    }
}
