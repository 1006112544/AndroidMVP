package com.daobao.asus.dbbaseframe.mvpTest;

import android.os.Handler;
import android.os.Message;
import com.daobao.asus.dbbaseframe.mvp.model.BaseModel;

/**
 * Created by db on 2018/10/13.
 */
public class TestModel extends BaseModel implements TestContract.Model {
    public TestModel(Handler handler) {
        super(handler);
    }


    @Override
    public void login() {
        //网络请求
        //假设接收到了数据
        String response = "登陆成功";
        Message message = new Message();
        message.what = 1;
        message.obj = response;
        sendMessage(message);
    }
}
