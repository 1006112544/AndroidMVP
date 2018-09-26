package com.daobao.asus.dbbaseframe.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.daobao.asus.dbbaseframe.mvp.presenter.BasePresenter;
import com.daobao.asus.dbbaseframe.util.NetStateUtil;
import static com.daobao.asus.dbbaseframe.util.NetStateUtil.NETWORK_FAIL;

/**
 * Created by db on 2018/9/22.
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView{

    public P mPresenter;

    abstract P binPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化mPresenter
        mPresenter = binPresenter();
        //绑定生命周期
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑P层 避免内存泄漏
        getLifecycle().removeObserver(mPresenter);
        mPresenter = null;
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(){

    }

    @Override
    public void dismissProgress() {

    }

    /**
     * 判断是否有网络
     * @param context 上下文
     * @return 是否有网络
     */
    @Override
    public boolean hasNetwork(Context context) {
        int netState = NetStateUtil.getNetworkState(context);
        if(netState!=NETWORK_FAIL){
            return true;
        }
        return false;
    }
}
