package com.daobao.asus.dbbaseframe.mvp.presenter;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import com.daobao.asus.dbbaseframe.mvp.view.IView;

/**
 * Created by db on 2018/9/22.
 */
public abstract class FrameBasePresenter<V extends IView> implements IPresenter{

    private V mView;

    public FrameBasePresenter(V view){
        this.mView = view;
    }

    @Override
    public void OnDestroy(@NonNull LifecycleOwner owner) {
        //解绑V层和M层 避免导致内存泄漏
        mView = null;
    }

}
