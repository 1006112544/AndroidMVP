package com.daobao.asus.dbbaseframe.mvp.model;


import android.os.Message;

import com.daobao.asus.dbbaseframe.mvp.presenter.BasePresenter.BaseHandler;

/**
 * Created by db on 2018/9/26.
 */
public abstract class BaseModel {

    private BaseHandler mHandler; //获取P层中的Handler来通信

    public BaseModel(BaseHandler handler){
        this.mHandler = handler;
    }

    public void onDestroy(){
        mHandler = null;
    }

    /**
     * 发送消息到P层
     */
    public void sendMessage(Message message){
        if(mHandler!=null){
            mHandler.sendMessage(message);
        }
    }

    /**
     * 发送延时消息到P层
     */
    public void sendMessageDelayed(Message message,long delayedTime){
        mHandler.sendMessageDelayed(message,delayedTime);
    }

}
