package com.test.entity;

import java.util.Date;

public class Auth {

    public Auth() {
    }

    private String role;

    private String menu;

    private Long id;

    private int age;

    private Boolean flag;

    private Date date;


    public Auth(String role, String menu) {
        this.role = role;
        this.menu = menu;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "role='" + role + '\'' +
                ", menu='" + menu + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", flag=" + flag +
                ", date=" + date +
                '}';
    }
}
