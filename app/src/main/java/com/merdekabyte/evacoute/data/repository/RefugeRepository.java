package com.merdekabyte.evacoute.data.repository;

import com.merdekabyte.evacoute.data.model.Refuge;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

public class RefugeRepository {

    private static String className = "evacuation";

    public List<Refuge> resolveAll() throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        List<ParseObject> parseObjects = query.find();
        List<Refuge> refuges = new ArrayList<>();
        for (ParseObject object : parseObjects) {
            refuges.add(new Refuge(object));
        }
        return refuges;
    }

    public Refuge resolveById(String objectId) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(className);
        ParseObject parseObject = query.get(objectId);
        return new Refuge(parseObject);
    }

}
