package com.zhou.mvpapp.bean;

import java.util.List;

/**
 * 工作详情
 * Created by zhou on 2016/2/3.
 */
public class JobDetails {

    private String i_pob_id;
    private String i_project_id;
    private String c_name;
    private String c_project_name;
    private String c_region_name;
    private String c_region_detail;
    private String i_project_days;
    private String i_project_start;
    private String i_type;
    private String c_gz_name;
    private String i_need_people;
    private String i_joined_people;
    private String d_day_salary;
    private String c_desc;
    private String i_com_id;
    private String i_add_time;
    private String c_contract_name;
    private String c_contract_phone;

    private Company company;
    private List<String> c_project_pics;

    public String getI_pob_id() {
        return i_pob_id;
    }

    public void setI_pob_id(String i_pob_id) {
        this.i_pob_id = i_pob_id;
    }

    public String getI_project_id() {
        return i_project_id;
    }

    public void setI_project_id(String i_project_id) {
        this.i_project_id = i_project_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
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

    public String getI_type() {
        return i_type;
    }

    public void setI_type(String i_type) {
        this.i_type = i_type;
    }

    public String getC_gz_name() {
        return c_gz_name;
    }

    public void setC_gz_name(String c_gz_name) {
        this.c_gz_name = c_gz_name;
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

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
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

    public String getC_contract_name() {
        return c_contract_name;
    }

    public void setC_contract_name(String c_contract_name) {
        this.c_contract_name = c_contract_name;
    }

    public String getC_contract_phone() {
        return c_contract_phone;
    }

    public void setC_contract_phone(String c_contract_phone) {
        this.c_contract_phone = c_contract_phone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<String> getC_project_pics() {
        return c_project_pics;
    }

    public void setC_project_pics(List<String> c_project_pics) {
        this.c_project_pics = c_project_pics;
    }

    public static class Company {
        private String c_name;
        private String c_addr_detail;
        private String i_star;
        private String i_star_name;
        private String i_star1;
        private String i_star2;
        private String publish_job_nums;

        private List<Advantage> advantage;

        public String getC_name() {
            return c_name;
        }

        public void setC_name(String c_name) {
            this.c_name = c_name;
        }

        public String getC_addr_detail() {
            return c_addr_detail;
        }

        public void setC_addr_detail(String c_addr_detail) {
            this.c_addr_detail = c_addr_detail;
        }

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

        public String getI_star1() {
            return i_star1;
        }

        public void setI_star1(String i_star1) {
            this.i_star1 = i_star1;
        }

        public String getI_star2() {
            return i_star2;
        }

        public void setI_star2(String i_star2) {
            this.i_star2 = i_star2;
        }

        public String getPublish_job_nums() {
            return publish_job_nums;
        }

        public void setPublish_job_nums(String publish_job_nums) {
            this.publish_job_nums = publish_job_nums;
        }

        public List<Advantage> getAdvantage() {
            return advantage;
        }

        public void setAdvantage(List<Advantage> advantage) {
            this.advantage = advantage;
        }

        public static class Advantage {
            private String name;
            private String des;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }
        }
    }
}
