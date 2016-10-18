package com.zhou.mvpapp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 工人列表返回实体类
 * Created by zhou on 2016/9/2.
 */
public class WorkerResult {

    private int countAll;//总条数
    private int curPage;//当前页
    private int eachNum;//每页总数
    private int pageAll;//总页数

    private List<Worker> list;

    public int getCountAll() {
        return countAll;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getEachNum() {
        return eachNum;
    }

    public void setEachNum(int eachNum) {
        this.eachNum = eachNum;
    }

    public int getPageAll() {
        return pageAll;
    }

    public void setPageAll(int pageAll) {
        this.pageAll = pageAll;
    }

    public List<Worker> getList() {
        return list;
    }

    public void setList(List<Worker> list) {
        this.list = list;
    }

    /**
     * 工人实体类
     */
    public static class Worker {

        private String i_user_id;//工人id
        private String c_no;//编号
        private String c_avatar;//工人头像
        private String c_name;//工人姓名
        private String c_addr;//工人所在地
        private ArrayList<String> c_good_job;//擅长工种

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

        public String getC_addr() {
            return c_addr;
        }

        public void setC_addr(String c_addr) {
            this.c_addr = c_addr;
        }

        public ArrayList<String> getC_good_job() {
            return c_good_job;
        }

        public void setC_good_job(ArrayList<String> c_good_job) {
            this.c_good_job = c_good_job;
        }
    }

}
