package com.zhou.designmodetest.OrderMode;

/**
 * 关电脑命令
 * Created by zhou on 2016/7/18.
 */
public class ComputerOffCommand implements Command {

    private Computer computer;

    public ComputerOffCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.off();
    }

}
