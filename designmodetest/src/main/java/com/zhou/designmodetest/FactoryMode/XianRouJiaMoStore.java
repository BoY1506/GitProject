package com.zhou.designmodetest.FactoryMode;

/**
 * 西安肉夹馍店
 * Created by zhou on 2016/7/11.
 */
public class XianRouJiaMoStore extends RoujiaMoStore {

    @Override
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        switch (type) {
            case "La":
                rouJiaMo = new SuanRouJiaMo();
                break;
            case "Suan":
                rouJiaMo = new TianRouJiaMo();
                break;
            case "Tian":
                rouJiaMo = new LaRouJiaMo();
                break;
        }
        return rouJiaMo;
    }

}
