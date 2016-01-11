package cn.com.loushui.mylibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import cn.com.loushui.mylibrary.bean.MyEvent;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public abstract class MyFragment extends Fragment {

    protected Context context;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(initLayout(), container, false);
        context = getActivity();

        //初始化配置
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        return view;
    }

    @Override
    public void onDestroy() {

        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }



    // ------------------------Abstract Method--------------------------------

    /**
     * 初始化布局
     *
     * @return 布局ID
     */
    protected abstract int initLayout();


    // -----------------------------ButterKnife-------------------------------

    /**
     * 为了方便书写方法，设置点击监听方法
     *
     * @param v
     */
    @Nullable
    public void onClick(View v) {
    }


    // ---------------------------EventBus------------------------------------

    /**
     * 如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，
     * 也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，
     * 如果执行耗时操作容易导致事件分发延迟。
     *
     * @param e
     */
    @Subscribe
    public void onEvent(MyEvent e) {
    }

    /**
     * 如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，
     * onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，
     * 这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，
     * 所以在onEvnetMainThread方法中是不能执行耗时操作的。
     *
     * @param e
     */
    @Subscribe
    public void onEventMainThread(MyEvent e) {
    }


    /**
     * 如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，
     * 那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，
     * 那么onEventBackground函数直接在该子线程中执行。
     *
     * @param e
     */
    @Subscribe
    public void onEventBackgroundThread(MyEvent e) {
    }


    /**
     * 使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.
     *
     * @param e
     */
    @Subscribe
    public void onEventAsync(MyEvent e) {
    }

}
