package ch.hslu.refashioned.ui.permission;

public interface PermissionManagerBuilderOptional {
    PermissionManagerBuilderOptional permissionsUntilVersion(int versionCode, String... permissions);

    PermissionManager build();
}
