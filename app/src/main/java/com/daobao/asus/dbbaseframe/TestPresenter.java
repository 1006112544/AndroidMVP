package com.daobao.asus.dbbaseframe;

import android.os.Message;

import com.daobao.asus.dbbaseframe.mvp.presenter.BasePresenter;

/**
 * Created by db on 2018/9/26.
 */
public class TestPresenter extends BasePresenter<TestModel,TestActivity> {

    public TestPresenter(TestActivity view) {
        super(view);
    }

    @Override
    public TestModel binModel() {
        return new TestModel(mHandler);
    }

    @Override
    public void modelResponse(Message msg) {

    }

}
