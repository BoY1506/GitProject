package com.zhou.mvpapp.net;

import com.zhou.mvpapp.bean.JobDetails;
import com.zhou.mvpapp.bean.JobResult;
import com.zhou.mvpapp.bean.SmsCode;
import com.zhou.mvpapp.bean.User;
import com.zhou.mvpapp.bean.WorkerDetails;
import com.zhou.mvpapp.bean.WorkerResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * App接口请求地址
 * Created by zhou on 2016/9/1.
 */
public interface ServerAPI {

    //Base地址前缀
//    String BASE_URL = "http://101.200.199.110:8081/";
    String BASE_URL = "http://www.jianzhuyougong.com/";

    /**
     * -------------------------------------- 用户模块 ---------------------------------------------
     */
    //获取注册验证码
    @GET("user/GetSmsCode/{phone}")
    Call<BaseResponse<SmsCode>> getRegisterSmsCode(@Path("phone") String phone);

    //用户注册
    @FormUrlEncoded
    @POST("user/register")
    Call<BaseResponse<Object>> userRegister(@Field("phone") String phone, @Field("code") int code,
                                            @Field("password") String password);

    //用户登录
    @FormUrlEncoded
    @POST("user/login")
    Call<BaseResponse<User>> userLogin(@Field("phone") String phone, @Field("password") String password,
                                       @Field("device") String device, @Field("special") String special);

    /**
     * -------------------------------------- 职位模块 ---------------------------------------------
     */
    //职位和班组搜索
    @FormUrlEncoded
    @POST("project/search")
    Call<BaseResponse<JobResult>> findJobs(@Field("search_attr") String searchAttr,
                                           @Field("eachNum") int eachNum, @Field("page") int page);

    //职位详情
    @FormUrlEncoded
    @POST("project/detail")
    Call<BaseResponse<JobDetails>> jobDetails(@Field("i_pob_id") String iPobId);

    /**
     * -------------------------------------- 班组模块 ---------------------------------------------
     */
    //工人搜索
    @FormUrlEncoded
    @POST("project/workersearch")
    Call<BaseResponse<WorkerResult>> findWorkers(@Field("search_attr") String searchAttr,
                                                 @Field("eachNum") int eachNum, @Field("page") int page);

    //工人详情
    @FormUrlEncoded
    @POST("personal/detail")
    Call<BaseResponse<WorkerDetails>> workerDetails(@Field("i_user_id") int iPobId, @Field("cidentify") String cidentify);

}
