package ch.hslu.refashioned.ui.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;

import java.nio.file.Path;

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

    public static boolean doesPathExist(String path, Context context) {
        Uri uri = Uri.parse(path);
        DocumentFile documentFile = DocumentFile.fromSingleUri(context, uri);
        return documentFile != null && documentFile.exists();
    }
}
