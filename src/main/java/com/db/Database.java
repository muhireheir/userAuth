package com.db;

import java.util.LinkedHashMap;
import java.util.Map;
import com.dtos.*;

public class Database {

    static Map<String, User> database = new LinkedHashMap<String, User>();

    public static void insert(String id, User row) {

        database.put(id, row);
    }

    public static User fetch(String name) {

        return database.get(name);

    }

}