package ch.hslu.refashioned.ui.util;

import android.content.res.Resources;

public class ThemeUtil {
    public static boolean isDarkTheme(Resources resources) {
        return (resources.getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK) == android.content.res.Configuration.UI_MODE_NIGHT_YES;
    }
}
