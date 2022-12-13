package com.madapps.yogastudio.useractivity.addmission;

public class AddmissionModel {
    String fullname;
    String age;
    String username;
    String formonth;
    String batchno;

    public AddmissionModel() {
    }

    public AddmissionModel(String fullname, String age, String username, String formonth, String batchno) {
        this.fullname = fullname;
        this.age = age;
        this.username = username;
        this.formonth = formonth;
        this.batchno = batchno;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFormonth() {
        return formonth;
    }

    public void setFormonth(String formonth) {
        this.formonth = formonth;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
}
