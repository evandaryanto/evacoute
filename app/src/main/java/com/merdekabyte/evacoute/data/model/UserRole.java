package com.merdekabyte.evacoute.data.model;

import com.merdekabyte.evacoute.data.model.exception.UnknownUserRoleException;

import java.util.ArrayList;

public class UserRole implements Comparable<UserRole>{
    public static UserRole GOVERMENT = new UserRole("government");
    public static UserRole FOUNDATION = new UserRole("foundation");
    public static UserRole PUBLIC = new UserRole("public");

    private String userRole;

    public UserRole (String userRole)
    {
        ArrayList<String> roles = new ArrayList<String>(){{
            add("government");
            add("foundation");
            add("public");
        }};
        if (!roles.contains(userRole)) throw new UnknownUserRoleException(userRole);
        this.userRole = userRole;
    }


    public String getRole() {
        return userRole;
    }

    @Override
    public int compareTo(UserRole userRole) {
        return userRole.getRole().compareTo(this.getRole());
    }
}
