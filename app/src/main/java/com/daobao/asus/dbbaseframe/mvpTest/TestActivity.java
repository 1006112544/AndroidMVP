package com.daobao.asus.dbbaseframe.mvpTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.daobao.asus.dbbaseframe.mvp.view.BaseActivity;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.IFailure;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.ISuccess;
import com.daobao.asus.dbbaseframe.netUtil.RestClient;

/**
 * Created by db on 2018/10/13.
 */
public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //模拟登陆
        mPresenter.login();
        RestClient.builder()
                .context(this)
                .url("http://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .post();
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
