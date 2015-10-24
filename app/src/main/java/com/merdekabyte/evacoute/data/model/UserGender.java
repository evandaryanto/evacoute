package com.merdekabyte.evacoute.data.model;

import com.merdekabyte.evacoute.data.model.exception.UnknownUserGenderException;

import java.util.ArrayList;

public class UserGender implements Comparable<UserGender>{
    public static UserGender MALE = new UserGender("M");
    public static UserGender FEMALE = new UserGender("F");

    private String gender;

    public UserGender (String gender)
    {
        ArrayList<String> genders = new ArrayList<String>(){{
            add("F");
            add("M");
        }};
        if (!genders.contains(gender)) throw new UnknownUserGenderException(gender);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public int compareTo(UserGender gender) {
        return gender.getGender().compareTo(this.getGender());
    }
}
