package com.daobao.asus.dbbaseframe.netUtil.CallBack;

/**
 * 请求错误回调
 *
 * Created by ASUS on 2017/10/29.
 */

public interface IError {
    void onError(int code, String msg);
}
