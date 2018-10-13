package com.daobao.asus.dbbaseframe.mvp.presenter;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.daobao.asus.dbbaseframe.mvp.model.BaseModel;
import com.daobao.asus.dbbaseframe.mvp.view.BaseActivity;
import com.daobao.asus.dbbaseframe.mvp.view.IView;

import java.lang.ref.WeakReference;

/**
 * 基础P层
 *
 * Created by db on 2018/9/22.
 */
public abstract class BasePresenter<M extends BaseModel,V extends IView> implements IPresenter{

    public V mView;
    public M mModel;

    @SuppressLint("HandlerLeak")
    public BasePresenter(V view){
        this.mView = view;
        this.mModel = binModel(getHandler());
    }

    /**
     *我们通过实现IPresenter中的OnDestroy方法来解除持有
     *
     * @param owner 生命周期管理者
     */
    @Override
    public void OnDestroy(@NonNull LifecycleOwner owner) {
        //解绑V层 避免导致内存泄漏
        mView = null;
        mModel.onDestroy();
        mModel = null;
    }

    public abstract M binModel(Handler handler);

    /**
     * 获取handler的方法
     *
     * @return BaseHandler
     */
    public Handler getHandler(){
        return new BaseHandler(this);
    }

    /**
     * 基础Handler 用于P层与M层通信
     */
    public static class BaseHandler extends Handler{

        //弱引用Activity或者Fragment 避免Handler持有导致内存泄漏
        private final WeakReference<BasePresenter> presenter;

        public BaseHandler(BasePresenter presenter) {
            this.presenter = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            if(presenter.get()!=null&&presenter.get().mView!=null){
                presenter.get().modelResponse(msg);
            }
        }
    }

    /**
     * 接收 M层返回的消息
     * @param msg
     */
    public abstract void modelResponse(Message msg);
}
