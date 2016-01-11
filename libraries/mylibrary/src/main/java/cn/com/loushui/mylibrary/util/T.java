package cn.com.loushui.mylibrary.util;

import android.widget.Toast;

import cn.com.loushui.mylibrary.base.MyApp;

/**
 * Created by Administrator on 2015/11/25.
 */
public class T {

    private static Toast mToast;

    private T() {
    }


    private static Toast getSingletonToast(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApp.getContext(), resId, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
        }

        return mToast;
    }

    private static Toast getSingletonToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApp.getContext(), text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
        }

        return mToast;
    }


    public static void showSingleton(int resId) {
        getSingletonToast(resId).show();
    }

    public static void showSingleton(String text) {
        getSingletonToast(text).show();
    }

    public static void showShort(int resId) {
        Toast.makeText(MyApp.getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String text) {
        Toast.makeText(MyApp.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(MyApp.getContext(), resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String text) {
        Toast.makeText(MyApp.getContext(), text, Toast.LENGTH_LONG).show();
    }


    public static void show(int resId) {
        showSingleton(resId);
    }


    public static void show(String text) {
        showSingleton(text);
    }
}
