package com.supergeek.pollup.models;

/**
 * Created by Junejas on 4/19/2017.
 */

public class SignupModel {
    String email;
    String password;
    String gender;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public SignupModel(String email, String password, String gender, String name, int age) {

        this.email = email;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.age = age;
    }

    String name;
    int age;
}
