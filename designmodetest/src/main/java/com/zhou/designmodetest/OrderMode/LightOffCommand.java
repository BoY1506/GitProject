package com.zhou.designmodetest.OrderMode;

/**
 * 关灯命令
 * Created by zhou on 2016/7/18.
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

}
