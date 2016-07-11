package com.zhou.designmodetest.FactoryMode;

/**
 * 肉夹馍商店（总店）
 * Created by zhou on 2016/7/11.
 */
public abstract class RoujiaMoStore {

//    //简单工厂
//    private SimpleRouJiaMoFactroy factroy;
//
//    public RoujiaMoStore(SimpleRouJiaMoFactroy factroy) {
//        this.factroy = factroy;
//    }

    //工厂方法
    public abstract RouJiaMo createRouJiaMo(String type);

    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = createRouJiaMo(type);
        rouJiaMo.prepare();
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }

//    /**
//     * 根据传入类型卖不同的肉夹馍
//     * 这时候商店只负责卖肉夹馍
//     *
//     * @param type
//     * @return
//     */
//    public RouJiaMo sellRouJiaMo(String type) {
//        RouJiaMo rouJiaMo = factroy.createcreateRouJiaMo(type);
//        rouJiaMo.prepare();
//        rouJiaMo.fire();
//        rouJiaMo.pack();
//        return rouJiaMo;
//    }

//    /**
//     * 根据传入类型卖不同的肉夹馍
//     *
//     * @param type
//     * @return
//     */
//    public RouJiaMo sellRouJiaMo(String type) {
//        RouJiaMo rouJiaMo = null;
//        switch (type) {
//            case "La":
//                rouJiaMo = new LaRouJiaMo();
//                break;
//            case "Suan":
//                rouJiaMo = new SuanRouJiaMo();
//                break;
//            case "Tian":
//                rouJiaMo = new TianRouJiaMo();
//                break;
//        }
//        rouJiaMo.prepare();
//        rouJiaMo.fire();
//        rouJiaMo.pack();
//        return rouJiaMo;
//    }

}
