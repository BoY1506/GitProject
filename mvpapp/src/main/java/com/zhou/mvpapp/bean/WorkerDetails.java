package com.zhou.mvpapp.bean;

import java.util.ArrayList;

/**
 * 工人详情
 * Created by zhou on 2016/2/3.
 */
public class WorkerDetails {

    private String i_user_id;//工人id
    private String c_no;//编号
    private String c_avatar;//头像
    private String c_name;//姓名
    private String c_gender_name;//性别
    private int age;//年龄
    private String c_addr;//所在地
    private String i_tec_rate;//技能经验
    private String i_work_rate;//敬业协作
    private String i_safe_rate;//安全意识
    private String i_ly_rate;//履约率
    private String i_star;//综合评分
    private String c_phone;//联系方式
    private String c_good_job;//擅长工种
    private String c_day_price;//期望薪资
    private String i_work_days;//工作时长
    private String c_other;//自我描述
    private ArrayList<String> c_personal_advantage;//技能展示
    private ArrayList<String> c_personal_quality;//资质证书

    public String getI_user_id() {
        return i_user_id;
    }

    public void setI_user_id(String i_user_id) {
        this.i_user_id = i_user_id;
    }

    public String getC_no() {
        return c_no;
    }

    public void setC_no(String c_no) {
        this.c_no = c_no;
    }

    public String getC_avatar() {
        return c_avatar;
    }

    public void setC_avatar(String c_avatar) {
        this.c_avatar = c_avatar;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_gender_name() {
        return c_gender_name;
    }

    public void setC_gender_name(String c_gender_name) {
        this.c_gender_name = c_gender_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getC_addr() {
        return c_addr;
    }

    public void setC_addr(String c_addr) {
        this.c_addr = c_addr;
    }

    public String getI_tec_rate() {
        return i_tec_rate;
    }

    public void setI_tec_rate(String i_tec_rate) {
        this.i_tec_rate = i_tec_rate;
    }

    public String getI_work_rate() {
        return i_work_rate;
    }

    public void setI_work_rate(String i_work_rate) {
        this.i_work_rate = i_work_rate;
    }

    public String getI_safe_rate() {
        return i_safe_rate;
    }

    public void setI_safe_rate(String i_safe_rate) {
        this.i_safe_rate = i_safe_rate;
    }

    public String getI_ly_rate() {
        return i_ly_rate;
    }

    public void setI_ly_rate(String i_ly_rate) {
        this.i_ly_rate = i_ly_rate;
    }

    public String getI_star() {
        return i_star;
    }

    public void setI_star(String i_star) {
        this.i_star = i_star;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_good_job() {
        return c_good_job;
    }

    public void setC_good_job(String c_good_job) {
        this.c_good_job = c_good_job;
    }

    public String getC_day_price() {
        return c_day_price;
    }

    public void setC_day_price(String c_day_price) {
        this.c_day_price = c_day_price;
    }

    public String getI_work_days() {
        return i_work_days;
    }

    public void setI_work_days(String i_work_days) {
        this.i_work_days = i_work_days;
    }

    public String getC_other() {
        return c_other;
    }

    public void setC_other(String c_other) {
        this.c_other = c_other;
    }

    public ArrayList<String> getC_personal_advantage() {
        return c_personal_advantage;
    }

    public void setC_personal_advantage(ArrayList<String> c_personal_advantage) {
        this.c_personal_advantage = c_personal_advantage;
    }

    public ArrayList<String> getC_personal_quality() {
        return c_personal_quality;
    }

    public void setC_personal_quality(ArrayList<String> c_personal_quality) {
        this.c_personal_quality = c_personal_quality;
    }

}
