package com.daobao.asus.dbbaseframe.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.daobao.asus.dbbaseframe.mvp.presenter.FrameBasePresenter;

/**
 * Created by db on 2018/9/22.
 */
public abstract class FrameBaseFragment<P extends FrameBasePresenter> extends Fragment {

    private P mPresenter;

    abstract P getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化mPresenter
        mPresenter = getPresenter();
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
}
