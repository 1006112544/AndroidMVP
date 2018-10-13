package com.daobao.asus.dbbaseframe.mvpTest;

/**
 * 契约类
 *
 * Created by db on 2018/10/13.
 */
public interface TestContract {

    interface View{
        void loginResponse(String msg);
    }

    interface Model{
        void login();
    }
}
