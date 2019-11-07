package com.example.foodorder;

public class LoginModel {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
