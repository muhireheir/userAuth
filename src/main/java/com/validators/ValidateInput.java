package com.validators;

import com.db.*;
import com.dtos.*;

public class ValidateInput {

    public static void userExist(String username, String email) {
        User user = Database.fetch(username);
        if (user != null) {
            try {
                if (user.getEmail().equals(email)) {
                    throw new Exception("Email already exists");
                }
                throw new Exception("Username already exists");

            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
        return;
    }

    public static void role(String role) {

        if (isNotNull("role", role) && role.equals("Admin") || role.equals("Guest")) {
            return;
        } else {
            throw new IllegalArgumentException("Role is not valid");
        }
    }

    public static void isAlphabetic(String field, String input) {
        if (isNotNull(field, input) && input.matches("[a-zA-Z]+")) {
            return;
        } else {
            String message = String.format("%s is not valid it", field);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isAlphanumeric(String field, String input) {
        if (isNotNull(field, input) && input.matches("[a-zA-Z0-9]+")) {
            return;
        } else {
            String message = String.format("%s is not valid", field);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNumeric(String field, String input) {
        if (isNotNull(field, input) && input.matches("[0-9]+")) {
            return;
        } else {
            String message = String.format("%s is not valid", field);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEmail(String field, String input) {
        if (isNotNull(field, input) && input.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            return;
        } else {
            String message = String.format("%s is not a valid  email", field);
            throw new IllegalArgumentException(message);
        }
    }

    public static void isAlphabeticWithSpace(String field, String input) {
        if (isNotNull(field, input) && input.matches("[a-zA-Z ]+")) {
            return;
        } else {
            String message = String.format("%s is not valid", field);
            throw new IllegalArgumentException(message);
        }
    }

    public static String isPassword(String field, String input) {
        // password should have at least one digit

        if (isNotNull(field, input) && input.matches(".*[0-9].*")) {
            return input;
        } else {
            String message = String.format("%s should have at least one  digit", field);
            throw new IllegalArgumentException(message);
        }
    }

    public static Boolean isNotNull(String field, String input) {
        if (input != null) {
            return true;
        } else {
            return false;
        }
    }

}
