package net.itkmitl.room.libs.phatsanphon.model;

import net.itkmitl.room.libs.peeranat.query.FewQuery;

import java.util.Date;

public class User {
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private boolean isActive;
    private Date createdOn;
    private int role;

    public User(FewQuery query) {
        this.setId(query.getValue("id").asInt());
        this.setEmail(query.getValue("email").asString());
        this.setFirstname(query.getValue("firstname").asString());
        this.setLastname(query.getValue("lastname").asString());
        this.setTelephoneNumber(query.getValue("tel_num").asString());
        this.setActive(query.getValue("is_active").asBoolean());
        this.setCreatedOn(
                new Date(query.getValue("created_on").asString())
        );
        this.setRole(query.getValue("role").asInt());
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}