package ch.hslu.refashioned.ui.permission;

import androidx.fragment.app.Fragment;

public interface PermissionManagerBuilderOptional {
    PermissionManagerBuilderOptional permissionsUntilVersion(int versionCode, String... permissions);

    PermissionManagerBuilderOptional onPermissionGranted(Runnable runnable);

    PermissionManager build();
}
