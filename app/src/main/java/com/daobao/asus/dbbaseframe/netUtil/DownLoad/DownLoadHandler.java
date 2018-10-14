package com.daobao.asus.dbbaseframe.netUtil.DownLoad;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.IDownloadCallBack;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.IFailure;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.IRequest;
import com.daobao.asus.dbbaseframe.netUtil.CallBack.ISuccess;
import com.daobao.asus.dbbaseframe.netUtil.RestCreator;
import java.util.WeakHashMap;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by db on 2017/10/30.
 */

public class DownLoadHandler{

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWANLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private Context context;
    private IDownloadCallBack DownloadCallBack;

    public DownLoadHandler(String url,
                           IRequest request,
                           String dowanload_dir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           Context context,
                           IDownloadCallBack DownloadCallBack) {
        URL = url;
        REQUEST = request;
        DOWANLOAD_DIR = dowanload_dir;
        EXTENSION = extension;
        NAME = name;
        SUCCESS = success;
        FAILURE = failure;
        this.context = context;
        this.DownloadCallBack = DownloadCallBack;
    }

    public final void handleDownload(){
        //开始下载了
        if(REQUEST!=null)
        {
            REQUEST.onRequsetStart();
        }
        RestCreator.getRestService().download(URL,PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    final ResponseBody responseBody = response.body();
                    if(responseBody!=null){
                        //文件总长度
                        long total =  responseBody.contentLength();
                        final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS, context,DownloadCallBack);
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWANLOAD_DIR,EXTENSION,responseBody,NAME,total);
                        //这里一定要注意判断，否则文件下载不安全
                        if(task.isCancelled())
                        {
                            if(REQUEST!=null)
                            {
                                REQUEST.onRequsetEnd();
                            }
                        }
                    }else if(FAILURE!=null) {
                        FAILURE.onFailure();
                    }
                }
                else if (FAILURE!=null) {
                    FAILURE.onFailure();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                if(FAILURE!=null)
                {
                    FAILURE.onFailure();
                }
            }
        });
    }

}
