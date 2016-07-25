package com.zhou.designmodetest.OrderMode;

/**
 * 开灯命令
 * Created by zhou on 2016/7/18.
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
    
}
