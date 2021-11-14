package com.utils;

public class Password {

    public static String encodePassword(String password, int age) {
        // reverse the string
        StringBuilder sb = new StringBuilder(password);
        String encodedPassword = "**" + sb.reverse().toString() + age + "**";
        return encodedPassword;
    }
}
