package cn.com.loushui.shop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.loushui.shop.R;
import cn.com.loushui.shop.base.BaseActivity;
import cn.com.loushui.shop.fragment.FragmentFlea;
import cn.com.loushui.shop.fragment.FragmentForum;
import cn.com.loushui.shop.fragment.FragmentLAF;
import cn.com.loushui.shop.fragment.FragmentMe;

public class ActivityMain extends BaseActivity {

    Fragment flea, laf, forum, me;

    @Bind({R.id.rb_flea, R.id.rb_laf, R.id.rb_forum, R.id.rb_me})
    List<RadioButton> rbs;

    private int index;
    private int currentTabIndex;
    private Fragment fragments[];


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        flea = new FragmentFlea();
        laf = new FragmentLAF();
        forum = new FragmentForum();
        me = new FragmentMe();
        fragments = new Fragment[]{flea, laf, forum, me};
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rl, flea).show(flea).commit();

    }

    @Nullable
    @Override
    @OnClick({R.id.rb_flea, R.id.rb_laf, R.id.rb_forum, R.id.rb_me})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_flea:
                index = 0;
                break;
            case R.id.rb_laf:
                index = 1;
                break;
            case R.id.rb_forum:
                index = 2;
                break;
            case R.id.rb_me:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                ft.add(R.id.rl, fragments[index]);
            }
            ft.show(fragments[index]).commit();
        }
        currentTabIndex = index;
    }


}
