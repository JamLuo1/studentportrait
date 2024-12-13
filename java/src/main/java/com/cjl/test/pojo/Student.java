package com.cjl.test.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Student {
    private String id;
    private String name;
    @JsonIgnore
    private String pwd;
    private String school;
    private String sex;
    private int age;
    private String pic;
    public Student() {
    }

    public Student(String id, String pwd, String name, String school, String sex, int age, String pic) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.school = school;
        this.sex = sex;
        this.age = age;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
