package com.supergeek.pollup.models;

/**
 * Created by Junejas on 4/19/2017.
 */

public class SignInModel {
    String email,password;

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

    public SignInModel(String email, String password) {

        this.email = email;
        this.password = password;
    }
}
