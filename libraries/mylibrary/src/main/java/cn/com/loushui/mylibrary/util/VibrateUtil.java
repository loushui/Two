package cn.com.loushui.mylibrary.util;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Administrator on 2015/12/22.
 */
public class VibrateUtil {
    public VibrateUtil() {
    }

    public static void vibrate(Context context, long milliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

    public static void vibrate(Context context, long[] pattern, int repeat) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeat);
    }
}
