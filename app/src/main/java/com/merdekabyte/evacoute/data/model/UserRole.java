package com.merdekabyte.evacoute.data.model;

import com.merdekabyte.evacoute.data.model.exception.UnknownUserRoleException;

import java.util.ArrayList;

public class UserRole implements Comparable<UserRole>{
    public static UserRole GOVERMENT = new UserRole("goverment");
    public static UserRole FOUNDATION = new UserRole("foundation");
    public static UserRole PUBLIC = new UserRole("public");

    private String userRole;

    public UserRole (String role)
    {
        ArrayList<String> roles = new ArrayList<String>(){{
            add(UserRole.GOVERMENT.getRole());
            add(UserRole.FOUNDATION.getRole());
            add(UserRole.PUBLIC.getRole());
        }};
        if (!roles.contains(role)) throw new UnknownUserRoleException(role);
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
