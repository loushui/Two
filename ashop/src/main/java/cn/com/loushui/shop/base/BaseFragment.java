package cn.com.loushui.shop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import cn.com.loushui.mylibrary.base.MyFragment;
import cn.com.loushui.shop.R;

/**
 * Created by Administrator on 2015/12/29.
 */
public abstract class BaseFragment extends MyFragment {
    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Bind(R.id.tv_title)
    TextView tv_title;

    protected void initTitle(String title) {
        tv_title.setText(title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
