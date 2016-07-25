package com.zhou.designmodetest.OrderMode;

/**
 * 开电脑命令
 * Created by zhou on 2016/7/18.
 */
public class ComputerOnCommand implements Command {

    private Computer computer;

    public ComputerOnCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.on();
    }

}
