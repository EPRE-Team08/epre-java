package ch.hslu.refashioned.ui.permission;

import android.content.Context;

import androidx.annotation.NonNull;

public interface PermissionManagerBuilderContext {
    PermissionManagerBuilderPermissions withContext(@NonNull Context context);
}
