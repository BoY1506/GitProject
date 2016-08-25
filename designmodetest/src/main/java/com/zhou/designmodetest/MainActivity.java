package com.zhou.designmodetest;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhou.designmodetest.AdapterMode.Mobile;
import com.zhou.designmodetest.AdapterMode.PowerAdapter;
import com.zhou.designmodetest.AdapterMode.V220Power;
import com.zhou.designmodetest.AdapterMode.V5Power;
import com.zhou.designmodetest.DecoratorMode.ArmEquip;
import com.zhou.designmodetest.DecoratorMode.BlueGemDecorator;
import com.zhou.designmodetest.DecoratorMode.IEquip;
import com.zhou.designmodetest.DecoratorMode.RedGemDecorator;
import com.zhou.designmodetest.DecoratorMode.ShoeEquip;
import com.zhou.designmodetest.FacadeMode.HomeTheaterFacade;
import com.zhou.designmodetest.FacadeMode.Player;
import com.zhou.designmodetest.FacadeMode.PopcornPopper;
import com.zhou.designmodetest.FacadeMode.Projector;
import com.zhou.designmodetest.FacadeMode.RoomLight;
import com.zhou.designmodetest.MVP.test1.UserLoginActivity;
import com.zhou.designmodetest.MVP.test2.ShowTextActivity;
import com.zhou.designmodetest.ObserverMode.ObjectCase;
import com.zhou.designmodetest.ObserverMode.ObjectCaseSys;
import com.zhou.designmodetest.ObserverMode.ObserverSys1;
import com.zhou.designmodetest.OrderMode.Command;
import com.zhou.designmodetest.OrderMode.Computer;
import com.zhou.designmodetest.OrderMode.ComputerOffCommand;
import com.zhou.designmodetest.OrderMode.ComputerOnCommand;
import com.zhou.designmodetest.OrderMode.ControlPanel;
import com.zhou.designmodetest.OrderMode.Door;
import com.zhou.designmodetest.OrderMode.DoorCloseCommand;
import com.zhou.designmodetest.OrderMode.DoorOpenCommand;
import com.zhou.designmodetest.OrderMode.Light;
import com.zhou.designmodetest.OrderMode.LightOffCommand;
import com.zhou.designmodetest.OrderMode.LightOnCommand;
import com.zhou.designmodetest.OrderMode.QuickCommand;
import com.zhou.designmodetest.StateMode.VendingMachine2;
import com.zhou.designmodetest.StrategyMode.AttackJYSG;
import com.zhou.designmodetest.StrategyMode.DefendTBS;
import com.zhou.designmodetest.StrategyMode.Display1;
import com.zhou.designmodetest.StrategyMode.RoleAnew;
import com.zhou.designmodetest.StrategyMode.RunJCTQ;
import com.zhou.designmodetest.TemplateMode.HRWorker;
import com.zhou.designmodetest.TemplateMode.ITWorker;
import com.zhou.designmodetest.TemplateMode.ManagerWorker;
import com.zhou.designmodetest.TemplateMode.QAWorker;
import com.zhou.designmodetest.TemplateMode.Worker;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn4)
    Button btn4;
    @InjectView(R.id.btn5)
    Button btn5;
    @InjectView(R.id.btn6)
    Button btn6;
    @InjectView(R.id.btn7)
    Button btn7;
    @InjectView(R.id.btn8)
    Button btn8;
    @InjectView(R.id.btn9)
    Button btn9;
    @InjectView(R.id.btn10)
    Button btn10;
    @InjectView(R.id.btn11)
    Button btn11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
            R.id.btn9, R.id.btn10, R.id.btn11})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                ObjectCase objectCase;
                ObjectCaseSys objectCaseSys;

//        //服务号
//        objectCase = new ObjectCase();
//        //订阅者
//        Observer1 observer1 = new Observer1(objectCase);
//        Observer2 observer2 = new Observer2(objectCase);
                //服务号
                objectCaseSys = new ObjectCaseSys();
                ObserverSys1 observerSys1 = new ObserverSys1(objectCaseSys);

//                objectCase.setMsg("新的消息");
                objectCaseSys.setMsg("新的消息");
                break;
            case R.id.btn4:
                Worker zhou, xiong, fang, wang;
                //工作者
                zhou = new ITWorker("周博");
                xiong = new QAWorker("大熊");
                fang = new HRWorker("房云丽");
                wang = new ManagerWorker("王振");
                zhou.workOneDay();
                xiong.workOneDay();
                fang.workOneDay();
                wang.workOneDay();
                break;
            case R.id.btn5:
                Mobile mobile = new Mobile();
                V5Power v5Power = new PowerAdapter(new V220Power());
                mobile.inputPower(v5Power);
                break;
            case R.id.btn6:
//                VendingMachine machine = new VendingMachine(10);
//                Log.e("状态模式", "- - - - - - 开始测试 - - - - - -");
//                machine.insertMoney();
//                machine.backMoney();
//                machine.insertMoney();
//                machine.turnCrank();
//                Log.e("状态模式", "- - - - - - 压力测试 - - - - - -");
//                machine.insertMoney();
//                machine.insertMoney();
//                machine.turnCrank();
//                machine.turnCrank();
//                machine.backMoney();
//                machine.turnCrank();

                VendingMachine2 machine = new VendingMachine2(10);
                machine.insertMoney();
                machine.backMoney();
                Log.e("状态模式", "- - - - - - 开始测试 - - - - - -");
                machine.insertMoney();
                machine.turnCrank();
                machine.insertMoney();
                machine.turnCrank();
                machine.insertMoney();
                machine.turnCrank();
                machine.insertMoney();
                machine.turnCrank();
                machine.insertMoney();
                machine.turnCrank();
                machine.insertMoney();
                machine.turnCrank();
                machine.insertMoney();
                machine.turnCrank();
                Log.e("状态模式", "- - - - - - 压力测试 - - - - - -");
                machine.insertMoney();
                machine.backMoney();
                machine.backMoney();
                machine.turnCrank();// 无效操作
                machine.turnCrank();// 无效操作
                machine.backMoney();
                break;
            case R.id.btn7:
                //三个家电
                Light light = new Light();
                Door door = new Door();
                Computer computer = new Computer();
                //一个控制器，假设是我们的app主界面
                ControlPanel controlPanel = new ControlPanel();
                // 为每个按钮设置功能
                controlPanel.setCommand(0, new LightOnCommand(light));
                controlPanel.setCommand(1, new LightOffCommand(light));
                controlPanel.setCommand(2, new ComputerOnCommand(computer));
                controlPanel.setCommand(3, new ComputerOffCommand(computer));
                controlPanel.setCommand(4, new DoorOpenCommand(door));
                controlPanel.setCommand(5, new DoorCloseCommand(door));
                // 模拟点击
                controlPanel.keyPressed(0);
                controlPanel.keyPressed(2);
                controlPanel.keyPressed(3);
                controlPanel.keyPressed(4);
                controlPanel.keyPressed(5);
                controlPanel.keyPressed(8);// 这个没有指定，但是不会出任何问题，我们的NoCommand的功劳
                // 定义一键搞定模式
                QuickCommand quickCommand = new QuickCommand(new Command[]{new DoorCloseCommand(door),
                        new LightOffCommand(light), new ComputerOnCommand(computer)});
                Log.e("命令模式", "- - - - - 多命令 - - - - -");
                controlPanel.setCommand(8, quickCommand);
                controlPanel.keyPressed(8);
                break;
            case R.id.btn8:
                // 一个镶嵌2颗红宝石，1颗蓝宝石的靴子
                Log.e("装饰着模式", "一个镶嵌2颗红宝石，1颗蓝宝石的靴子");
                IEquip equip = new RedGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ShoeEquip())));
                Log.e("装饰着模式", "攻击力  : " + equip.caculateAttack());
                Log.e("装饰着模式", "描述  : " + equip.description());
                Log.e("装饰着模式", "- - - - - - - - - - - - - - - ");
                // 一个镶嵌1颗红宝石，1颗蓝宝石的武器
                Log.e("装饰着模式", "一个镶嵌1颗红宝石，1颗蓝宝石的武器");
                IEquip equip2 = new RedGemDecorator(new BlueGemDecorator(new ArmEquip()));
                Log.e("装饰着模式", "攻击力  : " + equip2.caculateAttack());
                Log.e("装饰着模式", "描述  : " + equip2.description());
                break;
            case R.id.btn9:
                HomeTheaterFacade facade = new HomeTheaterFacade(new Computer(), new Player(),
                        new RoomLight(), new Projector(), new PopcornPopper());
                facade.stopMovie();
                break;
            case R.id.btn10:
                //只需要设置和使用即可
                RoleAnew roleA = new RoleAnew("A");
                roleA.setDisplayBehavior(new Display1())
                        .setAttackBehavior(new AttackJYSG())
                        .setDefendBehavior(new DefendTBS())
                        .setRunBehavior(new RunJCTQ());
                roleA.display();
                roleA.attack();
                roleA.defend();
                roleA.run();
                break;
            case R.id.btn11:
                Intent intent = null;
                new AlertDialog.Builder(this)
                        .setItems(new String[]{"test1", "test2"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent2 = new Intent(MainActivity.this, ShowTextActivity.class);
                                        startActivity(intent2);
                                        break;
                                }
                            }
                        }).create().show();

                break;
        }
    }
}
