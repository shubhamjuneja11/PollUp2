package com.supergeek.pollup.models;

/**
 * Created by Junejas on 4/19/2017.
 */

public class PostDetailModel {
    int id,age,option;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public PostDetailModel(int id, int age, int option, String gender) {

        this.id = id;
        this.age = age;
        this.option = option;
        this.gender = gender;
    }

    String gender;
}
