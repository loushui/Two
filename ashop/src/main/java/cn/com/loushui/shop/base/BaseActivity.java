package cn.com.loushui.shop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
    }
}
