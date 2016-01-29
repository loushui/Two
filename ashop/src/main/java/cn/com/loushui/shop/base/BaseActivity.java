package cn.com.loushui.shop.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import cn.com.loushui.mylibrary.base.MyActivity;
import cn.com.loushui.shop.R;

/**
 * Created by Administrator on 2015/12/29.
 */
public abstract class BaseActivity extends MyActivity {

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Bind(R.id.tv_title)
    TextView tv_title;

    protected void initTitle(String title) {
        tv_title.setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
    }


    //-----------------------------ToolBar-------------------------------------------------------
    private SystemBarTintManager tintManager;
    private View mDecorView;
    private boolean hasHideSystemUi;

    protected int getTheColor(int res) {
        if (res <= 0)
            throw new IllegalArgumentException("resource id can not be less 0");
        return getResources().getColor(res);
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getTheColor(R.color.colorPrimary));
            tintManager.setStatusBarTintEnabled(true);
        }
        mDecorView = getWindow().getDecorView();
    }

    @TargetApi(19)
    public void hideSystemUI() {
        hasHideSystemUi = true;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mDecorView != null) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }

        if (tintManager != null) {
            tintManager.setStatusBarTintEnabled(false);
        }
    }

    @TargetApi(16)
    public void showSystemUI() {
        hasHideSystemUi = false;
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (mDecorView != null) {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (tintManager != null) {
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    public boolean hasNavBar() {
        if (tintManager == null)
            return false;
        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
        return config.hasNavigtionBar();
    }

}
