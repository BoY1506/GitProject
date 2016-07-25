package com.zhou.designmodetest.OrderMode;

/**
 * 关门命令
 * Created by zhou on 2016/7/18.
 */
public class DoorCloseCommand implements Command {

    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

}
