package com.dtos;

public abstract class User {
    protected String username;
    protected String password;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String sex;
    protected int age;
    protected String role;

    public abstract String login(String password);

    public abstract void register(String firstName, String lastname, String username, String password, String email,
            String sex, String phoneNumber, int age, String role);

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return this.role;
    }

    public String getEmail() {
        return this.email;
    }

    protected String passwordMatch(String existing, String userPassword) {
        String passwordWithAge = new StringBuilder(existing.replaceAll("[**]", "")).toString();
        int age = String.valueOf(this.age).length();
        int passwordLength = passwordWithAge.length();
        int range = passwordLength - age;
        String password = new StringBuilder(passwordWithAge.substring(0, range)).reverse().toString();
        if (userPassword.equals(password)) {
            return this.username;
        }
        throw new IllegalArgumentException("Incorrect username or password");
    }
}