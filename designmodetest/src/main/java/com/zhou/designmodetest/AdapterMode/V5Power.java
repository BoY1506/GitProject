package com.zhou.designmodetest.AdapterMode;

/**
 * 5V电压接口，提供5V电压
 * 需要适配器来实现，转换电压
 * Created by zhou on 2016/7/14.
 */
public interface V5Power {
    /**
     * 转化电压
     *
     * @return
     */
    int provideV5Power();

}
