package cn.com.loushui.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.OnClick;
import cn.com.loushui.shop.R;
import cn.com.loushui.shop.activity.ActivityEditBuy;
import cn.com.loushui.shop.base.BaseFragment;
import cn.com.loushui.shop.view.LinearRefreshLayout;

/**
 * Created by Administrator on 2015/12/29.
 */
public class FragmentFlea extends BaseFragment {


    @Bind(R.id.rb_left)
    RadioButton rb_left;
    @Bind(R.id.rb_right)
    RadioButton rb_right;
    @Bind(R.id.v_search)
    View v_search;

    @Bind(R.id.dl)
    DrawerLayout dl;

    @Bind(R.id.lrl)
    LinearRefreshLayout lrl;
    @Bind(R.id.rv)
    RecyclerView rv;

    @Bind(R.id.fam)
    FloatingActionMenu fam;
    @Bind(R.id.fab_buy)
    FloatingActionButton fab_buy;
    @Bind(R.id.fab_sell)
    FloatingActionButton fab_sell;

    @Override
    protected int initLayout() {
        return R.layout.fragment_flea;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        init();

        return v;
    }


    private void init() {
        rb_left.setText("出售");
        rb_right.setText("求购");

        fam.setClosedOnTouchOutside(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lrl.setRecyclerView(rv, llm);
        lrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lrl.setRefreshing(false);
            }
        });
        lrl.setOnLoadListener(new LinearRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                lrl.setLoading(false);
            }
        });
        lrl.setAutoRefresh();
    }

    @Nullable
    @Override
    @OnClick({R.id.rb_left, R.id.rb_right, R.id.v_search, R.id.fab_sell, R.id.fab_buy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_left:
                break;
            case R.id.rb_right:
                break;
            case R.id.fab_buy:
                startActivity(new Intent(context, ActivityEditBuy.class));
                break;
            case R.id.fab_sell:
                KLog.i("sell");
                break;
            case R.id.v_search:
                if (!dl.isDrawerOpen(GravityCompat.END)) {
                    dl.openDrawer(GravityCompat.END);
                } else {
                    dl.closeDrawer(GravityCompat.END);
                }
                break;

        }
        fam.close(true);
    }
}
