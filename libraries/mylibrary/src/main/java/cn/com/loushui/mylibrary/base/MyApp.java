package cn.com.loushui.mylibrary.base;

import android.app.Application;
import android.content.Context;

import com.socks.library.KLog;
import com.zhy.http.okhttp.OkHttpClientManager;

import java.util.concurrent.TimeUnit;

import cn.com.loushui.mylibrary.tool.LiteOrmTool;
import cn.com.loushui.mylibrary.tool.UILTool;

public class MyApp extends Application {

    protected static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        //初始化配置
        LiteOrmTool.init(context, "loushui");
        UILTool.init(context, "loushui/Cache");
        initOkHttpUtil(10000);
        KLog.init(true);
    }


    /**
     * 获得App实例
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }


    // ------------------------OkHttpUtil-------------------------------------

    /**
     * 初始化OkHttpUtil配置
     *
     * @param time_connect
     */
    public void initOkHttpUtil(long time_connect) {
        OkHttpClientManager.getInstance().getOkHttpClient().setConnectTimeout(time_connect, TimeUnit.MILLISECONDS);
    }


}
