package com.zhou.gitproject.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.zhou.gitproject.BaseActivity;
import com.zhou.gitproject.R;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Retrofit2.0练习
 * Created by zhou on 2016/7/11.
 */
public class RetrofitTest extends BaseActivity {

    @InjectView(R.id.request_btn)
    Button requestBtn;
    @InjectView(R.id.result_content)
    TextView resultContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.request_btn)
    public void onClick() {
        sendFile();
    }

    /**
     * 发起请求
     */
    private void sendRequest() {
        //创建retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                //url基地址（必填项）
                .baseUrl(Impl.BASE_URL)
                        //自定义okHttpClient时调用，默认创建okHttpClient
//                .callFactory(new OkHttpClient())
                        //添加返回参数转化器时调用，需引入相关依赖包，默认返回ResponseBody类型
//                .addConverterFactory(GsonConverterFactory.create())
                        //对Call进行转化，基本上不需要我们自己去自定义，配合RxJava使用
//                .addCallAdapterFactory()
                        //根据platForm平台对象，将返回结果发送至UI线程处理
//                .callbackExecutor()
                        //构建retrofit对象
                .build();
        //创建服务类
        Impl impl = retrofit.create(Impl.class);
        //获取Call对象
        Call<ResponseBody> call = impl.getMyJobs("xxx");
        //执行请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                showToast("请求成功");
                try {
                    if (response.errorBody() != null) {
                        //例如404仍会调用onResponse方法，这时response.errorBody()不为null
                        resultContent.setText(response.errorBody().string());
                        showLog("成功但失败：" + response.errorBody().string());
                    } else if (response.body() != null) {
                        //response.body()不为null才算真正请求成功
                        resultContent.setText(response.body().string());
                        showLog("成功：" + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resultContent.setText(t.toString());
                Log.e("失败！！！", t.toString());
                showToast("请求失败" + t.toString());
            }
        });
    }

    /**
     * 发送图片（MultipartBody.Part）
     */
    private void sendFile() {
        //创建文件对象
        File file = new File("file_path");
        //根据文件对象创建RequestBody
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        //根据RequestBody创建Part，第一个参数为key值，第二个参数没什么用，第三个参数为value
        MultipartBody.Part photo = MultipartBody.Part.createFormData("c_avatar", "testPhoto2.jpg", photoBody);
        //发送请求
        new Retrofit.Builder()
                .baseUrl(Impl.BASE_URL)
                .build()
                .create(Impl.class)
                .getSingleFile(photo, RequestBody.create(null, "xxx"))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.errorBody() != null) {
                                showToast("请求成功但失败");
                                resultContent.setText(response.errorBody().string());
                                showLog("成功但失败：" + response.errorBody().string());
                            } else if (response.body() != null) {
                                showToast("请求成功");
                                resultContent.setText(response.body().string());
                                showLog("成功：" + response.body().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        resultContent.setText(t.toString());
                        Log.e("失败！！！", t.toString());
                        showToast("请求失败" + t.toString());
                    }
                });
    }

    /**
     * 发送图片（RequestBody）
     */
    private void sendFile2() {

        File file = new File("file_path");
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), file);

        //如果参数为Map<String,RequestBody>
//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("key\";filename=\"pp.jpg", RequestBody.create(MediaType.parse("image/jpg"), file));

        new Retrofit.Builder()
                .baseUrl(Impl.BASE_URL)
                .build()
                .create(Impl.class)
                .getSingleFile2(photoBody, "xxx")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.errorBody() != null) {
                                showToast("请求成功但失败");
                                resultContent.setText(response.errorBody().string());
                                showLog("成功但失败：" + response.errorBody().string());
                            } else if (response.body() != null) {
                                showToast("请求成功");
                                resultContent.setText(response.body().string());
                                showLog("成功：" + response.body().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        resultContent.setText(t.toString());
                        Log.e("失败！！！", t.toString());
                        showToast("请求失败" + t.toString());
                    }
                });
    }
}
