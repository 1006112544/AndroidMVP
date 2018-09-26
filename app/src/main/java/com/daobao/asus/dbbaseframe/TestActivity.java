package com.daobao.asus.dbbaseframe;

import com.daobao.asus.dbbaseframe.mvp.view.BaseActivity;

/**
 * Created by db on 2018/9/26.
 */
public class TestActivity extends BaseActivity<TestPresenter> {
    @Override
    public TestPresenter binPresenter() {
        return new TestPresenter(this);
    }
}
