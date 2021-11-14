package com.app;

import com.dtos.*;

public class Admin extends User {

    private String encodePassword(String password, int age) {
        StringBuilder stringBuilder = new StringBuilder(password);
        return "**" + stringBuilder.reverse().toString() + age + "**";
    }

    @Override
    public Boolean login(String password) {
        Boolean passwordMatch = this.passwordMatch(password);
        if (passwordMatch) {
            return true;
        }
        return false;
    }

    @Override
    public void register(String firstName, String lastname, String username, String password, String email, String sex,
            String phoneNumber, int age, String role) {
        this.age = age;
        this.email = email;
        this.lastName = firstName;
        this.lastName = lastname;
        this.password = this.encodePassword(password, age);
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.sex = sex;
        this.username = username;
    }

}