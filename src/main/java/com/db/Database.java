package com.db;

import java.util.LinkedHashMap;
import com.dtos.*;

public class Database {

    static LinkedHashMap<String, User> database = new LinkedHashMap<String, User>();

    public static void insert(String id, User row) {

        database.put(id, row);
    }

    public static User fetch(String name) {

        return database.get(name);

    }

    public static boolean userExists(String username) {
        if (database.containsKey(username)) {
            return true;
        }
        return false;
    }

}