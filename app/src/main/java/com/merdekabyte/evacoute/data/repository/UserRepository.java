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

    public static List<User> cachedAll;
    public static List<User> cachedPublic;
    public static List<User> cachedNonPublic;

    public List<User> resolveAll() throws ParseException {

        if (cachedAll != null)
            return cachedAll;
        else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
            List<ParseObject> parseObjects = query.find();
            List<User> users = new ArrayList<User>();
            for (ParseObject object : parseObjects) {
                users.add(new User(object));
            }
            UserRepository.cachedAll = users;
            return users;
        }

    }

    public User resolveById(String objectId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        ParseObject parseObject = query.get(objectId);
        return new User(parseObject);
    }

    public List<User> resolvePublicUser() throws ParseException {
        if (cachedPublic != null)
            return cachedPublic;
        else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
            List<ParseObject> parseObjects = query.whereEqualTo("role", UserRole.PUBLIC.getRole()).find();
            List<User> users = new ArrayList<User>();
            for (ParseObject object : parseObjects) {
                users.add(new User(object));
            }
            UserRepository.cachedPublic = users;
            return users;
        }
    }

    public List<User> resolveNonPublicUser() throws ParseException {
        if (cachedNonPublic != null)
            return cachedNonPublic;
        else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
            List<ParseObject> parseObjects = query.whereNotEqualTo("role", UserRole.PUBLIC.getRole()).find();
            List<User> users = new ArrayList<User>();
            for (ParseObject object : parseObjects) {
                users.add(new User(object));
            }
            UserRepository.cachedNonPublic = users;
            return users;
        }
    }
}
