package com.daobao.asus.dbbaseframe.netUtil.CallBack;

/**
 * 下载文件进度回调
 *
 * Created by db on 2018/9/22.
 */
public interface IDownloadCallBack {
    //正在下载中
    void onDownloading(int progress);
}
