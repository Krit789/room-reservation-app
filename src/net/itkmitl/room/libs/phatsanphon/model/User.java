package net.itkmitl.room.libs.phatsanphon.model;

import net.itkmitl.room.enums.*;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;

import java.sql.Date;

public class User {

    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private boolean isActive;
    private Date createdOn;
    private EnumUserRole role;

    public User(FewQuery query) {
        processObject(query);
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

    public EnumUserRole getRole() {
        return role;
    }

    public boolean isStaff() {
        //Because staff level is then more 10
        return role.getLevel() >= 10;
    }

    public void setRole(EnumUserRole role) {
        this.role = role;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    private void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    private void setCreatedOn(String rawCreatedOn) {
        this.createdOn = new DateTime(rawCreatedOn).getDateTime();
    }

    private void processObject(FewQuery query) {
        this.setId(query.getValue("id").asInt());
        this.setEmail(query.getValue("email").asString());
        this.setFirstname(query.getValue("firstname").asString());
        this.setLastname(query.getValue("lastname").asString());
        this.setTelephoneNumber(query.getValue("tel_num").asString());
        this.setActive(query.getValue("is_active").asBoolean());
        this.setCreatedOn(query.getValue("created_on").asString());
        this.setRole(EnumUserRole.searchRoleByLevel(query.getValue("role").asInt()));
    }
}
