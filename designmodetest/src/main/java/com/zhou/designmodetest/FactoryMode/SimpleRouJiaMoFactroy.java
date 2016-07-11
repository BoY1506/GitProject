package com.zhou.designmodetest.FactoryMode;

/**
 * 简单工厂吧，把做肉夹馍的方法抽取出来
 * Created by zhou on 2016/7/11.
 */
public class SimpleRouJiaMoFactroy {

    public RouJiaMo createcreateRouJiaMo(String type) {

        RouJiaMo rouJiaMo = null;
        switch (type) {
            case "La":
                rouJiaMo = new SuanRouJiaMo();
                break;
            case "Suan":
                rouJiaMo = new SuanRouJiaMo();
                break;
            case "Tian":
                rouJiaMo = new SuanRouJiaMo();
                break;
        }
        return rouJiaMo;

    }

}
