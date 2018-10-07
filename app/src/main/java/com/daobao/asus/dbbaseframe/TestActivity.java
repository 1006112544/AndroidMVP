package com.daobao.asus.dbbaseframe;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daobao.asus.dbbaseframe.mvp.view.BaseActivity;

/**
 * Created by db on 2018/9/26.
 */
public class TestActivity extends BaseActivity<TestPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //模拟请求登录
        mPresenter.login();
    }


    @Override
    public TestPresenter binPresenter() {
        return new TestPresenter(this);
    }

    public void loginReseponse(Message msg){
        showMessage((String) msg.obj);
    }
}
