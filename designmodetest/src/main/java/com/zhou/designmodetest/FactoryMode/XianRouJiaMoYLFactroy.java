package com.zhou.designmodetest.FactoryMode;

/**
 * 根据西安当地特色，提供这两种材料
 * Created by zhou on 2016/7/11.
 */
public class XianRouJiaMoYLFactroy implements RouJiaMoYLFactroy {


    @Override
    public Meat createMeat() {
        return new FreshMest();
    }

    @Override
    public YuanLiao createYuanliao() {
        return new XianTeSeYuanliao();
    }

    class FreshMest extends Meat {

    }

    class XianTeSeYuanliao extends YuanLiao {

    }

}
