package com.app;

import com.dtos.User;
import com.validators.ValidateInput;

public class Guest extends User {

    private String encodePassword(String password, int age) {
        int passwordLength = ValidateInput.isPassword("password", password).length();
        if (passwordLength != 5) {
            throw new IllegalArgumentException("Password must be  5 characters long");
        }
        StringBuilder stringBuilder = new StringBuilder(password);
        return "**" + stringBuilder.reverse().toString() + age + "**";
    }

    @Override
    public String login(String password) {
        try {
            String passwordMatch = this.passwordMatch(this.password, password);
            return passwordMatch;
        } catch (Exception e) {
            // TODO: handle exception
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void register(String firstName, String lastname, String username, String password, String email, String sex,
            String phoneNumber, int age, String role) {
        try {
            this.age = age;
            this.email = email;
            this.lastName = firstName;
            this.lastName = lastname;
            this.password = this.encodePassword(password, age);
            this.phoneNumber = phoneNumber;
            this.role = role;
            this.sex = sex;
            this.username = username;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

}
