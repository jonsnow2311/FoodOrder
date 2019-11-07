package com.example.foodorder;

public class SignUpModel {

    private String name;

    private String password;

    private String phone;

    private String email;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public SignUpModel(String name,  String email , String phone , String password) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
