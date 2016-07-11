package com.zhou.designmodetest.FactoryMode;

/**
 * 提供肉夹馍的原料
 * Created by zhou on 2016/7/11.
 */
public interface RouJiaMoYLFactroy {

    /**
     * 生产肉
     *
     * @return
     */
    public Meat createMeat();

    /**
     * 生产调料神马的
     *
     * @return
     */
    public YuanLiao createYuanliao();

    class Meat {
    }

    class YuanLiao {
    }

}
