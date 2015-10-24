package com.merdekabyte.evacoute.data.model;

import com.parse.ParseObject;

import java.util.Date;

public class ParseModel {
    protected String objectId;
    protected Date createdAt;
    protected Date updatedAt;

    // Constructors

    public ParseModel(String objectId, Date createdAt, Date updatedAt) {
        this.objectId = objectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ParseModel(ParseObject parseObject) {
        this.objectId = parseObject.getObjectId();
        this.createdAt = parseObject.getCreatedAt();
        this.updatedAt = parseObject.getUpdatedAt();
    }

    // getters and setters

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
