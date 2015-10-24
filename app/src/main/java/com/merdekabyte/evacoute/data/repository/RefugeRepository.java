package com.merdekabyte.evacoute.data.repository;

import android.util.Log;

import com.merdekabyte.evacoute.data.model.Refuge;
import com.merdekabyte.evacoute.data.model.User;
import com.merdekabyte.evacoute.data.model.UserRole;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class RefugeRepository {

    public static String className = "refuge";

    private ParseQuery<ParseObject> query = ParseQuery.getQuery(className);

    public List<Refuge> resolveAll() throws ParseException {
        List<ParseObject> parseObjects = this.query.find();
        List<Refuge> refuges = new ArrayList<>();
        for (ParseObject object : parseObjects) {
            refuges.add(new Refuge(object));
        }
        return refuges;
    }

    public Refuge resolveById(String objectId) throws ParseException {
        ParseObject parseObject = this.query.get(objectId);
        return new Refuge(parseObject);
    }

    public List<Refuge> resolveByUsers(List<String> objectIds) throws ParseException {
        List<Refuge> refuges = resolveAll();
        List<Refuge> filteredRefuges = new ArrayList<>();
        for (Refuge refuge : refuges) {
            if(objectIds.contains(refuge.getOwnerObjectId())) filteredRefuges.add(refuge);
        }
        return filteredRefuges;
    }

}
