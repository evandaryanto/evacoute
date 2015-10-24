package com.merdekabyte.evacoute.data.repository;
import com.merdekabyte.evacoute.data.model.User;
import com.merdekabyte.evacoute.data.model.UserRole;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    public static String className = "user";

    public List<User> resolveAll() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        List<ParseObject> parseObjects = query.find();
        List<User> users = new ArrayList<User>();
        for (ParseObject object : parseObjects) {
            users.add(new User(object));
        }
        return users;
    }

    public User resolveById(String objectId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        ParseObject parseObject = query.get(objectId);
        return new User(parseObject);
    }

    public List<User> resolvePublicUser() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        List<ParseObject> parseObjects = query.whereEqualTo("role", UserRole.PUBLIC.getRole()).find();
        List<User> users = new ArrayList<User>();
        for (ParseObject object : parseObjects) {
            users.add(new User(object));
        }
        return users;
    }

    public List<User> resolveNonPublicUser() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        List<ParseObject> parseObjects = query.whereNotEqualTo("role", UserRole.PUBLIC.getRole()).find();
        List<User> users = new ArrayList<User>();
        for (ParseObject object : parseObjects) {
            users.add(new User(object));
        }
        return users;
    }
}
