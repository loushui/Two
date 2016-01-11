package cn.com.loushui.mylibrary.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.io.FileFilter;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/12/22.
 */
public class AppUtil {
    public AppUtil() {
    }

    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = ((ActivityManager.RunningTaskInfo) taskList.get(0)).topActivity;
            if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isServiceRunning(Context ctx, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List servicesList = activityManager.getRunningServices(2147483647);
        Iterator l = servicesList.iterator();

        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = (ActivityManager.RunningServiceInfo) l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }

        return isRunning;
    }

    public static boolean stopRunningService(Context ctx, String className) {
        Intent intent_service = null;
        boolean ret = false;

        try {
            intent_service = new Intent(ctx, Class.forName(className));
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        if (intent_service != null) {
            ret = ctx.stopService(intent_service);
        }

        return ret;
    }

    public static int getCpuCores() {
        try {
            File e = new File("/sys/devices/system/cpu/");
            File[] files = e.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return Pattern.matches("cpu[0-9]", pathname.getName());
                }
            });
            return files.length;
        } catch (Exception var2) {
            return 1;
        }
    }
}
