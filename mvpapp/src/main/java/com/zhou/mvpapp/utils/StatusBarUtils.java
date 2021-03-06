package com.zhou.mvpapp.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 沉浸式状态栏完美实现
 * 注：Android5.x可直接设置getWindow().setStatusBarColor(color);
 * 与在样式文件中设置colorPrimaryDark效果一致，只是在5.0以下的系统无效
 * 因此工具类实际上可直接处理4.4~5.0之间的适配问题即可
 * Created by zhou on 2016/5/6.
 */
public class StatusBarUtils {

    /**
     * 将acitivity中的activity中的状态栏设置为一个纯色
     *
     * @param activity 需要设置的activity
     * @param color    设置的颜色（一般是titlebar的颜色）
     */
    public static void setColor(Activity activity, int color) {
        Logger.show("当前手机的Andorid版本:" + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //大于4.4才设置
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //大于5.0
                //5.0及以上，不设置透明状态栏，设置会有半透明阴影
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //设置statusBar的背景色，5.0默认已经fitSystemWindow=true
                activity.getWindow().setStatusBarColor(color);
            } else {
                //4.4需要添加此flag
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 生成一个状态栏大小的矩形
                View statusView = createStatusBarView(activity, color);
                // 添加statusView到根布局中
                ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                decorView.addView(statusView);
                //设置fitSystemWindow=true
                setRootView(activity);
            }
        }
    }

    /**
     * 解决drawlayout兼容问题,使得状态栏和标题栏相重叠（fisSystemWindow=false）
     * 需传入main_layout布局，使main_layout上添加一个与状态栏等高且颜色相同的view
     * 而menu_layout上图片或背景色不受影响
     *
     * @param activity 需要设置的activity
     */
    public static void setColor4Drawlayout(Activity activity, ViewGroup mainLayout, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //大于4.4才设置
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //大于5.0
                //5.0及以上，不设置透明状态栏，设置会有半透明阴影
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //这句作用等同于fitsSystemWindows=false，使状态栏和标题栏重合
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                //设置状态栏颜色透明
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                //4.4，直接设置透明
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            // 生成一个状态栏大小的矩形（注意这里的View只作用于main_layout上）
            View statusView = createStatusBarView(activity, color);
            // 添加 statusView到titlebar中
            mainLayout.addView(statusView, 0);
        }
    }

    /**
     * 当顶部是图片时，直接将图片显示到状态栏上
     * 此时状态栏透明，fitSystemWindow=false
     *
     * @param activity
     */
    public static void setImageBackground(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //大于4.4才设置
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //大于5.0
                //5.0及以上，不设置透明状态栏，设置会有半透明阴影
                activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //使activity_main.xml中的图片可以沉浸到状态栏上
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                //设置状态栏颜色透明
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                //直接设置透明
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 设置根布局参数，让跟布局参数适应透明状态栏
     */
    private static void setRootView(Activity activity) {
        //获取到activity_main.xml文件
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        //如果不设置参数，会使内容显示到状态栏上
        rootView.setFitsSystemWindows(true);
    }

    /**
     * 获取状态栏的高度
     *
     * @param acitivity
     * @return
     */
    private static int getStatusBarHeight(Activity acitivity) {
        int resourceId = acitivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return acitivity.getResources().getDimensionPixelOffset(resourceId);
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static View createStatusBarView(Activity activity, int color) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }

}

