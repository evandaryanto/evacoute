package com.merdekabyte.evacoute.data.model;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.parse.ParseObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Refuge extends ParseModel {

    protected String name;
    protected String description;
    protected Double geoLatitude;
    protected Double geoLongitude;
    protected String location;
    protected String locationDetail;
    protected String contact;
    protected String ownerObjectId;

    private String imageBucket = "http://evacoute.s3-ap-southeast-1.amazonaws.com/";

    // Constructors

    public Refuge(String objectId, String name, String description, Double geoLatitude,
                  Double geoLongitude, String location, String locationDetail, String contact,
                  String ownerObjectId, Date createdAt, Date updatedAt) {
        super(objectId, createdAt, updatedAt);
        this.name = name;
        this.description = description;
        this.geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
        this.location = location;
        this.locationDetail = locationDetail;
        this.contact = contact;
        this.ownerObjectId = ownerObjectId;
    }

    public Refuge(ParseObject parseObject) {
        super(parseObject);
        this.name = parseObject.getString("name");
        this.description = parseObject.getString("description");
        this.geoLatitude = parseObject.getDouble("geoLatitude");
        this.geoLongitude = parseObject.getDouble("geoLongitude");
        this.location = parseObject.getString("location");
        this.locationDetail = parseObject.getString("locationDetail");
        this.contact = parseObject.getString("contact");
        this.ownerObjectId = parseObject.getParseObject("ownerObjectId").getObjectId();
    }

//    public static Refuge defaultObject = new Refuge("", "", "", 0.0, 0.0, "", "", "", "", new Date(), new Date());

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(Double geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public Double getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(Double geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getOwnerObjectId() {
        return ownerObjectId;
    }

    public void setOwnerObjectId(String ownerObjectId) {
        this.ownerObjectId = ownerObjectId;
    }

    // additional getter

    public String getImageUrl() {
        return this.imageBucket + this.objectId + ".jpg";
    }

    // toJson

    public String toJson() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(this);
    }
}
