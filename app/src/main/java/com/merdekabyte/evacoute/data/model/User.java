package com.merdekabyte.evacoute.data.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.parse.ParseObject;
import java.util.Date;

public class User extends ParseModel {

    protected String name;
    protected String identificationNumber;
    protected String password;
    protected String email;
    protected UserGender gender;
    protected String disease;
    protected String otherInformation;
    protected Date birthDate;
    protected UserRole role;
    protected String phoneNumber;

    // Constructors

    public User(String objectId, String name, String identificationNumber, String password,
                String email, UserGender gender, String disease, String otherInformation,
                Date birthDate, UserRole role, String phoneNumber, Date createdAt, Date updatedAt) {
        super(objectId, createdAt, updatedAt);
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.disease = disease;
        this.otherInformation = otherInformation;
        this.birthDate = birthDate;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public User(ParseObject parseObject) {
        super(parseObject);
        this.name = parseObject.getString("name");
        this.identificationNumber = parseObject.getString("identificationNumber");
        this.password = parseObject.getString("password");
        this.email = parseObject.getString("email");
        this.gender = new UserGender(parseObject.getString("gender"));
        this.disease = parseObject.getString("disease");
        this.otherInformation = parseObject.getString("otherInformation");
        this.birthDate = parseObject.getDate("birthDate");
        this.role = new UserRole(parseObject.getString("role"));
        this.phoneNumber = parseObject.getString("phoneNumber");
    }

//    public static User defaultObject = new User("", "", "", "", "", UserGender.MALE, "", "", new Date(), UserRole.PUBLIC, "", new Date(), new Date());

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // toJson

    public String toJson() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
    }
}
