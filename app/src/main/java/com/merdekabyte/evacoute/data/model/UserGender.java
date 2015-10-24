package com.merdekabyte.evacoute.data.model;

import com.merdekabyte.evacoute.data.model.exception.UnknownUserGenderException;

import java.util.ArrayList;

public class UserGender implements Comparable<UserGender>{
    public static UserGender MALE = new UserGender('M');
    public static UserGender FEMALE = new UserGender('F');

    private Character gender;

    public UserGender (Character gender)
    {
        ArrayList<Character> genders = new ArrayList<Character>(){{
            add(UserGender.FEMALE.getGender());
            add(UserGender.MALE.getGender());
        }};
        if (!genders.contains(gender)) throw new UnknownUserGenderException(gender);
        this.gender = gender;
    }

    public Character getGender() {
        return gender;
    }

    @Override
    public int compareTo(UserGender gender) {
        return gender.getGender().compareTo(this.getGender());
    }
}
