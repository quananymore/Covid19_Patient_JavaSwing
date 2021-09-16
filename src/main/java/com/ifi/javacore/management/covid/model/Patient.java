package com.ifi.javacore.management.covid.model;

import java.util.Date;

public class Patient {
    private long id;
    private String name;
    private int age;
    private String gender;
    private String status;
    private int injectionTimes;
    private String image;
    private Date detectDate;

    public Patient(long id, String name, int age, String gender, String status, int injectionTimes, String image, Date detectDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.injectionTimes = injectionTimes;
        this.image = image;
        this.detectDate = detectDate;
    }

    public Patient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInjectionTimes() {
        return injectionTimes;
    }

    public void setInjectionTimes(int injectionTimes) {
        this.injectionTimes = injectionTimes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDetectDate() {
        return detectDate;
    }

    public void setDetectDate(Date detectDate) {
        this.detectDate = detectDate;
    }
}
