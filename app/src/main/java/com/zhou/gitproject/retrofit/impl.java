package com.zhou.gitproject.retrofit;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 请求接口
 * Created by zhou on 2016/7/12.
 */
public interface Impl {

    String BASE_URL = "http://101.200.199.110:8081/";
//    String TOKEN = "ib714b83qjok2q1osiuf8mmqs3";

    /**
     * 普通get请求
     */
    @GET("project/search")
    Call<ResponseBody> getAllJobs();

    /**
     * 可变参数
     * path中的值会替换{ }中的值
     */
    @GET("{test}")
    Call<ResponseBody> getPath(@Path("test") String value);

    /**
     * 查询参数
     * xxx/?sort=xxx
     */
    @GET("sort")
    Call<String> getSort(@Query("sort") String sort);

    /**
     * 请求体为一个字符串
     * 可将实体类转化为字符串
     * 内部利用requestBodyConverter进行转化
     */
    @POST("xxx")
    Call<String> getBody(@Body String str);

    /**
     * 表单提交键值对
     * FormUrlEncoded + Field
     */
    @FormUrlEncoded
    @POST("contract/bzorderlist")
    Call<ResponseBody> getMyJobs(@Field("cidentify") String token);

    /**
     * 表单提交键值对(复杂参数)
     * FormUrlEncoded + FieldMap
     */
    @FormUrlEncoded
    @POST("project/search")
    Call<String> getJobs2(@FieldMap Map<String, String> map);

    /**
     * 表单提交单文件
     * Multipart + Part
     * 文件为 MultipartBody.Part
     */
    @Multipart
    @POST("user/resume")
    Call<ResponseBody> getSingleFile(@Part MultipartBody.Part file,
                                     @Part("cidentify") RequestBody name);

    /**
     * 表单提交单文件
     * Multipart + Part
     * 文件为文件类型的requestBody
     * 注意：
     * 这里实际是做了拼接："form-data; name=“file_key\"; filename=\"pp.png”
     * name为参数key，filename没什么用，这里只写死pp.png，如果想动态写就利用MultipartBody.Part来构建Part
     */
    @Multipart
    @POST("user/resume")
    Call<ResponseBody> getSingleFile2(@Part("c_avatar\";filename =\"pp.jpg") RequestBody photo,
                                      @Part("cidentify") String token);

    /**
     * 表单提交多文件
     * 多文件利用PartMap存储
     * map.put("image\";filename=\"pp.jpg",fileBody)
     */
    @Multipart
    @POST("xxx")
    Call<String> getMultiFile(@PartMap Map<String, RequestBody> params,
                              @Part("pwd") RequestBody pwd);

    /**
     * 添加单个Header
     */
    @Headers("Cache-Control: max-age=640000")
    @GET("xxx")
    Call<String> getSingleHeader();

    /**
     * 添加多条Header
     * header不能被互相覆盖
     * 所有具有相同名字的header将会被包含到请求中
     */
    @Headers({"Cache-Control: max-age=640000",
            "User-Agent: Retrofit-Sample-App"})
    @GET("xxx")
    Call<String> getMultiHeader();

    /**
     * 动态添加Header
     * 必须给@Header提供相应的参数，如果参数的值为空header将会被忽略
     */
    @GET("xxx")
    Call<String> getHeader(@Header("Authorization") String authorization);

    /**
     * 下载文件，建议用okHttp
     * 因为此处返回的为默认返回类型ResponseBody
     * 拿到ResponseBody.body.stream
     * 此时已在主线程，而操作流最好开启新线程处理
     * 因此，文件下载建议直接利用okHttp处理即可
     */
    @GET("download")
    Call<ResponseBody> downloadTest();

}

