package com.daobao.asus.dbbaseframe.netUtil;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 最终通过这个类来发出网络请求
 * Created by db on 2017/10/29.
 */

public class RestCreator {
    /**
     * 请求参数
     */
    private static final class ParamsHolder {
        static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    /**
     * 获取参数
     * @return params
     */
    public static WeakHashMap<String,Object> getParams()
    {
        return ParamsHolder.PARAMS;
    }

    /**
     * 获取retrofit service
     * @return service
     */
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder{
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder{
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(NetConfig.TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

}
