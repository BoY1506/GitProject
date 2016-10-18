package com.zhou.mvpapp.bean;

import java.util.List;

/**
 * 工作返回实体类
 * Created by zhou on 2016/9/2.
 */
public class JobResult {

    private int countAll;//总条数
    private int curPage;//当前页
    private int eachNum;//每页总数
    private int pageAll;//总页数

    private List<Job> list;

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

    public List<Job> getList() {
        return list;
    }

    public void setList(List<Job> list) {
        this.list = list;
    }

    /**
     * 工作实体类
     */
    public static class Job {

        private String i_pob_id;//id
        private String c_name;//名称
        private String c_no;//编号
        private String i_need_people;//目标人数
        private String i_joined_people;//已招人数
        private String d_day_salary;//日薪
        private String c_gz_name;//工种名称
        private String i_project_id;//工程id
        private String c_project_name;//工程名称
        private String c_region_name;//地点
        private String c_region_detail;//详细地址
        private String i_project_days;//工期
        private String i_project_start;//开始时间
        private String i_project_end;//结束时间
        private String i_com_id;//公司id
        private String i_add_time;//发布时间
        private Company company;//公司

        public String getI_pob_id() {
            return i_pob_id;
        }

        public void setI_pob_id(String i_pob_id) {
            this.i_pob_id = i_pob_id;
        }

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getC_no() {
            return c_no;
        }

        public void setC_no(String c_no) {
            this.c_no = c_no;
        }

        public String getI_need_people() {
            return i_need_people;
        }

        public void setI_need_people(String i_need_people) {
            this.i_need_people = i_need_people;
        }

        public String getI_joined_people() {
            return i_joined_people;
        }

        public void setI_joined_people(String i_joined_people) {
            this.i_joined_people = i_joined_people;
        }

        public String getD_day_salary() {
            return d_day_salary;
        }

        public void setD_day_salary(String d_day_salary) {
            this.d_day_salary = d_day_salary;
        }

        public String getC_gz_name() {
            return c_gz_name;
        }

        public void setC_gz_name(String c_gz_name) {
            this.c_gz_name = c_gz_name;
        }

        public String getI_project_id() {
            return i_project_id;
        }

        public void setI_project_id(String i_project_id) {
            this.i_project_id = i_project_id;
        }

        public String getC_project_name() {
            return c_project_name;
        }

        public void setC_project_name(String c_project_name) {
            this.c_project_name = c_project_name;
        }

        public String getC_region_name() {
            return c_region_name;
        }

        public void setC_region_name(String c_region_name) {
            this.c_region_name = c_region_name;
        }

        public String getC_region_detail() {
            return c_region_detail;
        }

        public void setC_region_detail(String c_region_detail) {
            this.c_region_detail = c_region_detail;
        }

        public String getI_project_days() {
            return i_project_days;
        }

        public void setI_project_days(String i_project_days) {
            this.i_project_days = i_project_days;
        }

        public String getI_project_start() {
            return i_project_start;
        }

        public void setI_project_start(String i_project_start) {
            this.i_project_start = i_project_start;
        }

        public String getI_project_end() {
            return i_project_end;
        }

        public void setI_project_end(String i_project_end) {
            this.i_project_end = i_project_end;
        }

        public String getI_com_id() {
            return i_com_id;
        }

        public void setI_com_id(String i_com_id) {
            this.i_com_id = i_com_id;
        }

        public String getI_add_time() {
            return i_add_time;
        }

        public void setI_add_time(String i_add_time) {
            this.i_add_time = i_add_time;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        /**
         * 公司实体类
         */
        public static class Company {

            private String i_star;//评分
            private String i_star_name;//星级

            public String getI_star() {
                return i_star;
            }

            public void setI_star(String i_star) {
                this.i_star = i_star;
            }

            public String getI_star_name() {
                return i_star_name;
            }

            public void setI_star_name(String i_star_name) {
                this.i_star_name = i_star_name;
            }
        }
    }
}
