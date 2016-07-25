package com.zhou.designmodetest.FacadeMode;

import com.zhou.designmodetest.OrderMode.Computer;

/**
 * 外观模式
 * 一般用于需要简化一个很大的接口或者一群复杂的接口
 * Created by zhou on 2016/7/20.
 */
public class HomeTheaterFacade {

    //电脑
    private Computer computer;
    //播放器
    private Player player;
    //灯光
    private RoomLight light;
    //投影仪
    private Projector projector;
    //爆米花机
    private PopcornPopper popper;

    public HomeTheaterFacade(Computer computer, Player player, RoomLight light, Projector projector, PopcornPopper popper) {
        this.computer = computer;
        this.player = player;
        this.light = light;
        this.projector = projector;
        this.popper = popper;
    }

    /**
     * 看电影
     */
    public void watchMovie() {
        popper.on();
        popper.make();
        light.down();
        projector.move();
        projector.on();
        computer.on();
        player.on();
    }

    /**
     * 关闭电影
     */
    public void stopMovie() {
        player.off();
        computer.off();
        projector.off();
        projector.move();
        light.up();
    }

}
