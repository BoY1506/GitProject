package com.zhou.designmodetest.OrderMode;

/**
 * 定义一个命令，可以干一系列的事情
 * Created by zhou on 2016/7/18.
 */
public class QuickCommand implements Command {

    private Command[] commands;

    public QuickCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }

}
