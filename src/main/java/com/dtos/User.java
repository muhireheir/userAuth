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

    public abstract Boolean login(String password);

    public abstract void register(String firstName, String lastname, String username, String password, String email,
            String sex, String phoneNumber, int age, String role);

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return this.role;
    }

    protected boolean passwordMatch(String userPassword) {
        String age = new StringBuilder(this.age).toString();
        String decodedPassword = new StringBuilder(userPassword.replaceAll("[**]", "").replace(age, "")).reverse()
                .toString();
        if (userPassword.equals(decodedPassword)) {
            return true;
        }
        return false;
    }
}