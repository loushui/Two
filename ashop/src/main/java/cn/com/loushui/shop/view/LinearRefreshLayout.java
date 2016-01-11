package cn.com.loushui.shop.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Administrator on 2015/12/21.
 */
public class LinearRefreshLayout extends SwipeRefreshLayout {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private OnLoadListener mOnLoadListener;
    private OnRefreshListener mOnRefreshListener;
    private boolean isLoading = false;
    private boolean loadable = false;

    public LinearRefreshLayout(Context context) {
        this(context, null);
    }

    public LinearRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoRefresh() {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefreshing(true); // 直接调用是没有用的
                mOnRefreshListener.onRefresh();
            }
        }, 1);
    }

    public void setAutoLoad() {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                setLoading(true);
                mOnLoadListener.onLoad();
            }
        }, 1);
    }

    public void setRecyclerView(RecyclerView rv, LinearLayoutManager llm) {
        this.mRecyclerView = rv;
        this.mLinearLayoutManager = llm;
        this.mRecyclerView.setLayoutManager(llm);
        this.mRecyclerView.addOnScrollListener(new MOnScrollListener());
    }


    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
        if (this.mOnRefreshListener == null) {
            this.mOnRefreshListener = new OnRefreshListener() {
                @Override
                public void onRefresh() {
                }
            };
        }
        super.setOnRefreshListener(mOnRefreshListener);
    }

    public void setOnLoadListener(OnLoadListener listener) {
        this.mOnLoadListener = listener;
        if (this.mOnLoadListener == null) {
            this.mOnLoadListener = new OnLoadListener() {
                @Override
                public void onLoad() {
                }
            };
        }
    }


    private class MOnScrollListener extends RecyclerView.OnScrollListener {

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (mRecyclerView.getAdapter() == null) {
                Log.i("LinearRefreshLayout","RecyclerView has no adapter!");
                return;
            }
            int totalItemCount = mRecyclerView.getAdapter().getItemCount();
            int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem >= totalItemCount - 1
                    && !isRefreshing() && !isLoading) {
                isLoading = true;
                mOnLoadListener.onLoad();
            }

        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }

    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public interface OnLoadListener {
        void onLoad();
    }
}
