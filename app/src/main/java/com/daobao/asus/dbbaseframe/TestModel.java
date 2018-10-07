package com.daobao.asus.dbbaseframe;
import android.os.Handler;
import android.os.Message;

import com.daobao.asus.dbbaseframe.mvp.model.BaseModel;

/**
 * Created by db on 2018/9/26.
 */
public class TestModel extends BaseModel {

    public TestModel(Handler handler) {
        super(handler);
    }

    public void login(){
        //这里假装进行了模拟登录
        Message message = new Message();
        message.what = 0; //0表示请求成功
        message.obj = "登录成功"; //这里存放数据
        sendMessage(message);
    }
}
