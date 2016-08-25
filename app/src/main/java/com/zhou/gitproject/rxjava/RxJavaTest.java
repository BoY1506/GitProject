package com.zhou.gitproject.rxjava;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJava练习
 * RxJava的优势：异步、简洁、链式编程、对象转化
 * Created by zhou on 2016/7/19.
 */
public class RxJavaTest extends Activity {

    /**
     * 读取文件夹里的图片显示到imageview上
     * 传统方式，谜之缩进，可读性差
     */
    private void showImgs(final File[] folders) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (File folder : folders) {
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        if (file.getName().endsWith(".png")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //把照片设置到imageView上
                                }
                            });
                        }
                    }
                }
            }
        }.start();
    }

    /**
     * 读取文件夹里的图片显示到imageview上
     * 使用RxJava
     */
    private void showImgsRx(final File[] folders) {
        Observable.from(folders)
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        return file.getName().endsWith(".png");
                    }
                })
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        //这里获取到这个bm
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        //在这里设置bitmap
                    }
                });
    }

    /**
     * 概念
     */
    private void gn() {
        /**
         * Observer
         * onCompleted和onError必须调用其中一个
         */
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                //完成时调用
            }

            @Override
            public void onError(Throwable e) {
                //出错时调用
            }

            @Override
            public void onNext(String s) {
                //更新通知
            }
        };

        /**
         * Subscriber
         * 与Observer一样，多了个onStart()方法
         * 在事件未被调用前执行
         */
        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        /**
         * Observable
         */
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        Observable<String> observable2 = Observable.just("Hello", "Hi", "Aloha");

        String[] words = {"Hello", "Hi", "Aloha"};
        Observable<String> observable3 = Observable.from(words);

        /**
         * 注册监听
         */
        observable.subscribe(observer);

        /**
         * Action1
         * 单参数，无返回值
         */
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                //onNext
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //onError
            }
        };

        /**
         * Action0
         * 无参数，无返回值
         */
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {

            }
        };

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }

    /**
     * 场景练习
     */
    private void senceTest() {
        /**
         * 接收数组打印字符
         */
        String[] names = {"a", "b", "c", "d", "e"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("TAG", s);
                    }
                });

        /**
         * 由 id 取得图片并显示
         */
        final int imgRes = 0;
        final ImageView imageView = null;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(imgRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }
        });

        /**
         * 线程控制
         */
        Observable.just(1, 2, 3, 4)
                //事件激活线程
                .subscribeOn(Schedulers.io())
                        //事件消费线程
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                });

        /**
         * 输入路径，返回bitmap并显示
         */
        Observable.just("image/logo.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        //在这里得到bm
                        return null;
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        //在这里赋值
                    }
                });

        /**
         * 输入学生数组，输出学生名字
         */
        Student[] stus = {new Student(), new Student(), new Student()};
        Observable.from(stus)
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        //打印学生名字
                        String name = student.stuName;
                    }
                });

        /**
         * 输入学生数组，输出课程名字
         */
        Observable.from(stus)
                .flatMap(new Func1<Student, Observable<Curse>>() {
                    @Override
                    public Observable<Curse> call(Student student) {
                        return Observable.from(student.curses);
                    }
                })
                .subscribe(new Action1<Curse>() {
                    @Override
                    public void call(Curse curse) {
                        String name = curse.curseName;
                    }
                });

    }

    /**
     * 学生实体类
     */
    class Student {
        String stuName;
        Curse[] curses;
    }

    /**
     * 课程实体类
     */
    class Curse {
        String curseName;
    }

    /**
     * 结合retrofit使用
     */
    private void withRetrofitTest() {
        /**
         * 传统
         */
        new Retrofit.Builder()
                .baseUrl("xxx")
                .build()
                .create(Request.class)
                .getStus("1", new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        //处理结果
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {

                    }
                });

        /**
         * 使用rxJava
         */
        new Retrofit.Builder()
                .baseUrl("xxx")
                .build()
                .create(Request.class)
                .getStusRx("1")
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        //处理结果
                    }
                });

        /**
         * 先获取token，再请求数据
         * 传统
         * */
        new Retrofit.Builder()
                .baseUrl("xxx")
                .build()
                .create(Request.class)
                .getToken(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        new Retrofit.Builder()
                                .baseUrl("xxx")
                                .build()
                                .create(Request.class)
                                .getUser(response.body(), "1", new Callback<Student>() {
                                    @Override
                                    public void onResponse(Call<Student> call, Response<Student> response) {
                                        //拿到结果
                                    }

                                    @Override
                                    public void onFailure(Call<Student> call, Throwable t) {

                                    }
                                });
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

        /**
         * 利用rxJava
         */
        new Retrofit.Builder()
                .baseUrl("xxx")
                .build()
                .create(Request.class)
                .getToken()
                .flatMap(new Func1<String, Observable<Student>>() {
                    @Override
                    public Observable<Student> call(String s) {
                        return new Retrofit.Builder()
                                .baseUrl("xxx")
                                .build()
                                .create(Request.class)
                                .getUser(s, "1");
                    }
                })
                .observeOn(Schedulers.newThread())
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        //处理结果
                    }
                });

    }

    interface Request {
        @GET("/user")
        void getStus(@Query("userId") String userId, Callback<Student> callback);

        @GET("/user")
        Observable<Student> getStusRx(@Query("userId") String userId);

        @GET("/token")
        public void getToken(Callback<String> callback);

        @GET("/user")
        public void getUser(@Query("token") String token, @Query("userId") String userId, Callback<Student> callback);

        @GET("/token")
        public Observable<String> getToken();

        @GET("/user")
        public Observable<Student> getUser(@Query("token") String token, @Query("userId") String userId);
    }

}
