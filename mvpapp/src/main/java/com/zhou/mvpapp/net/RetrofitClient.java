package com.zhou.mvpapp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装类
 * Created by zhou on 2016/9/1.
 */
public class RetrofitClient {

    //单例
    private static RetrofitClient instance;
    //retrofit对象
    private static Retrofit retrofit;

    //请求超时时间
    private static final int DEFAULT_CONN_TIMEOUT = 10;
    private static final int DEFAULT_WRITE_TIMEOUT = 10;
    private static final int DEFAULT_READ_TIMEOUT = 30;

    /**
     * 单一实例
     */
    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 构造方法
     */
    private RetrofitClient() {
        //初始化okHttpClient对象
        //可设置统一日志拦截器、Header添加、Cookie设置、SSL配置、超时时间等
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //添加日志拦截器
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        //连接超时时间
                .connectTimeout(DEFAULT_CONN_TIMEOUT, TimeUnit.SECONDS)
                        //写入超时时间
                .writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                        //读取超时时间
                .readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
        //初始化retrofit对象
        retrofit = new Retrofit.Builder()
                //okHttpclient对象
                .client(okHttpClient)
                        //url基地址（必填项）
                .baseUrl(ServerAPI.BASE_URL)
                        //添加返回参数转化器时调用，需引入相关依赖包，默认返回ResponseBody类型
                .addConverterFactory(GsonConverterFactory.create())
                        //构建retrofit对象
                .build();
    }

    /**
     * 获取retrofit对象
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

}
