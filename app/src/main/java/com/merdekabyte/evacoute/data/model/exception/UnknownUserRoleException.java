package com.merdekabyte.evacoute.data.model.exception;

public class UnknownUserRoleException extends RuntimeException {

    public UnknownUserRoleException() {
        super("Unknown user role");
    }

    public UnknownUserRoleException(String role) {
        super("Unknown user role : " + role);
    }
}
