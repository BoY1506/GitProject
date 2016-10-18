package com.zhou.mvpapp.net;

import com.zhou.mvpapp.base.IBaseView;
import com.zhou.mvpapp.utils.AppManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * httpRequest请求封装类
 * Created by zhou on 2016/9/1.
 */
public class HttpCall {

    //Retrofit对象
    public static Retrofit retrofit = RetrofitClient.getInstance().getRetrofit();

    /**
     * 获取api对象
     */
    public static ServerAPI getApi() {
        //显示加载控件
        ((IBaseView) AppManager.getInstance().getCurActivity()).showLoadingViews();
        return retrofit.create(ServerAPI.class);
    }

    /**
     * 获取api对象
     */
    public static ServerAPI getApi(int loadingType) {
        //显示加载控件(有时一个界面有多个loadingType)
        ((IBaseView) AppManager.getInstance().getCurActivity()).showLoadingViews(loadingType);
        return retrofit.create(ServerAPI.class);
    }

    /**
     * 获取图片文件part
     *
     * @param key
     * @param file
     * @return
     */
    public static MultipartBody.Part getPicPart(String key, File file) {
        //根据文件对象创建RequestBody
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        //根据RequestBody创建Part，第一个参数为key值，第二个参数没什么用，第三个参数为value
        return MultipartBody.Part.createFormData(key, "image.jpg", fileBody);
    }

    /**
     * 获取图片文件map
     *
     * @param files
     * @return
     */
    public static Map<String, RequestBody> getPicPartMap(Map<String, File> files) {
        //如果参数为Map<String,RequestBody>
        Map<String, RequestBody> map = new HashMap<>();
        for (String key : files.keySet()) {
            //files的key即为参数的key值
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), files.get(key));
            map.put(key + "\";filename=\"image.jpg", fileBody);
        }
        return map;
    }

}
