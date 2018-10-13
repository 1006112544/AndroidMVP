package com.daobao.asus.dbbaseframe.mvpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daobao.asus.dbbaseframe.mvp.view.BaseActivity;

/**
 * Created by db on 2018/10/13.
 */
public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //模拟登陆
        mPresenter.login();
    }

    @Override
    public TestPresenter binPresenter() {
        return new TestPresenter(this);
    }

    @Override
    public void loginResponse(String msg) {
        showMessage(msg);
    }
}
