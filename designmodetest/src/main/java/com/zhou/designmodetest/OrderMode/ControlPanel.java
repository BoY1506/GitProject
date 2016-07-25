package com.zhou.designmodetest.OrderMode;

/**
 * 控制器包板
 * Created by zhou on 2016/7/18.
 */
public class ControlPanel {

    private static final int CONTROL_SIZE = 9;
    private Command[] commands;

    public ControlPanel() {
        this.commands = new Command[CONTROL_SIZE];
        //初始化所有按钮指向空对象
        for (int i = 0; i < CONTROL_SIZE; i++) {
            commands[i] = new NoCommand();
        }
    }

    /**
     * 设置每个按钮对应的命令
     *
     * @param index
     * @param command
     */
    public void setCommand(int index, Command command) {
        commands[index] = command;
    }

    /**
     * 模拟点击按钮
     *
     * @param index
     */
    public void keyPressed(int index) {
        commands[index].execute();
    }

}
