package com.daobao.asus.dbbaseframe.mvp.model;


import android.os.Handler;
import android.os.Message;

/**
 * 基础M层
 *
 * Created by db on 2018/9/26.
 */
public abstract class BaseModel {

    private Handler mHandler; //获取P层中的Handler来通信

    public BaseModel(Handler handler){
        this.mHandler = handler;
    }

    /**
     * 用于解除持有
     */
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
        if (mHandler!=null){
            mHandler.sendMessageDelayed(message,delayedTime);
        }
    }

    /**
     * 发送空消息到P层
     */
    public void sendEmptyMessage(int what){
        if (mHandler!=null){
            mHandler.sendEmptyMessage(what);
        }
    }

}
