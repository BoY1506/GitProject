package com.zhou.mvpapp.bean;

/**
 * 用户登录返回实体类
 * Created by zhou on 2016/9/2.
 */
public class User {

    private int i_user_id;//id
    private String c_name;//姓名
    private int i_status;//状态
    private String i_type;//身份
    private int i_banzu_id;//班组id
    private String cidentify;//token

    public int getI_user_id() {
        return i_user_id;
    }

    public void setI_user_id(int i_user_id) {
        this.i_user_id = i_user_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getI_status() {
        return i_status;
    }

    public void setI_status(int i_status) {
        this.i_status = i_status;
    }

    public String getI_type() {
        return i_type;
    }

    public void setI_type(String i_type) {
        this.i_type = i_type;
    }

    public int getI_banzu_id() {
        return i_banzu_id;
    }

    public void setI_banzu_id(int i_banzu_id) {
        this.i_banzu_id = i_banzu_id;
    }

    public String getCidentify() {
        return cidentify;
    }

    public void setCidentify(String cidentify) {
        this.cidentify = cidentify;
    }
}
