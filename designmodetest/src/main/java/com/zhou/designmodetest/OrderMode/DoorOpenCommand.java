package com.zhou.designmodetest.OrderMode;

/**
 * 开门命令
 * Created by zhou on 2016/7/18.
 */
public class DoorOpenCommand implements Command {

    private Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

}
