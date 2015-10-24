package com.merdekabyte.evacoute.data.model.exception;

public class UnknownUserGenderException extends RuntimeException {

    public UnknownUserGenderException() {
        super("Unknown user gender");
    }

    public UnknownUserGenderException(Character gender) {
        super("Unknown user gender : " + gender);
    }
}
