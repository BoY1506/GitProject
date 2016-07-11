package com.zhou.designmodetest.FactoryMode;

/**
 * 肉夹馍父类
 * Created by zhou on 2016/7/11.
 */
public abstract class RouJiaMo {

    protected String name;

    /**
     * 准备工作
     */
    public final void prepare(RouJiaMoYLFactroy ylFactroy)
    {
        RouJiaMoYLFactroy.Meat meat = ylFactroy.createMeat();
        RouJiaMoYLFactroy.YuanLiao yuanliao = ylFactroy.createYuanliao();
        System.out.println("使用官方的原料" + meat + " , " + yuanliao + "作为原材料制作肉夹馍 ");
    }

    /**
     * 使用你们的专用袋-包装
     */
    public final void pack()
    {
        System.out.println("肉夹馍-专用袋-包装");
    }

    /**
     * 秘制设备-烘烤2分钟
     */
    public final void fire()
    {
        System.out.println("肉夹馍-专用设备-烘烤");
    }

//    /**
//     * 准备工作
//     */
//    public void prepare() {
//        Log.e("工厂模式", "揉面-剁肉-完成准备工作");
//    }
//
//    /**
//     * 秘制设备-烘烤2分钟
//     */
//    public void fire() {
//        Log.e("工厂模式", "肉夹馍-专用设备-烘烤");
//    }
//
//    /**
//     * 使用你们的专用袋-包装
//     */
//    public void pack() {
//        Log.e("工厂模式", "肉夹馍-专用袋-包装");
//    }

}
