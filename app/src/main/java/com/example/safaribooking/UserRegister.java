package com.example.safaribooking;

public class UserRegister {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String userID;

    public UserRegister(String name, String email, String password, String phone, String userID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserID() {
        return userID;
    }
}
