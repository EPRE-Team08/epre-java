package ch.hslu.refashioned.ui.permission;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public interface PermissionManagerBuilderContext {
    PermissionManagerBuilderPermissions forFragment(@NotNull Fragment fragment);
}
