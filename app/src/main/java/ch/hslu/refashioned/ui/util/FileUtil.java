package ch.hslu.refashioned.ui.util;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import ch.hslu.refashioned.ui.scanInfo.ScanInfoActivity;

public class FileUtil {
    public static void deleteImage(@NonNull Activity activity, Uri imageUri) {
        int rowsDeleted = activity.getContentResolver().delete(imageUri, null, null);
        if (rowsDeleted < 1) {
            Log.e(ScanInfoActivity.class.getName(), "Could not delete image.");
            Toast.makeText(activity, "Could not delete image.", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(ScanInfoActivity.class.getName(), "Image deleted.");
        }
    }
}
