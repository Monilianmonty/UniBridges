package com.Uni.Model.Entity;

import java.sql.Timestamp;

public class Message {

    private int id;
    private String email;
    private String message;
    private Timestamp timestamp;

    public Message(int id, String email, String message, Timestamp timestamp) {
        this.id = id;
        this.email = email;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
