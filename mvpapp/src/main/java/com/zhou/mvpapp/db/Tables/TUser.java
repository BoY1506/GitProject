package com.zhou.mvpapp.db.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * 用户表
 * Created by zhou on 2016/9/3.
 */
@Table(name = "TUser")
public class TUser extends Model {

    @Column(name = "uId")
    public int uId;//id
    @Column(name = "name")
    public String name;//姓名
    @Column(name = "status")
    public int status;//状态
    @Column(name = "type")
    public String type;//身份
    @Column(name = "banzuId")
    public int banzuId;//班组id
    @Column(name = "cidentify")
    public String cidentify;//token

    public TUser() {
        super();
    }

    public TUser(int uId, String name, int status, String type, int banzuId, String cidentify) {
        super();
        this.uId = uId;
        this.name = name;
        this.status = status;
        this.type = type;
        this.banzuId = banzuId;
        this.cidentify = cidentify;
    }

}
