package net.itkmitl.room.libs.phatsanphon.entity;

import net.itkmitl.room.enums.EnumUserRole;
import net.itkmitl.room.libs.peeranat.query.FewQuery;
import net.itkmitl.room.libs.phatsanphon.date.DateTime;

public class User extends Entity {
    private int id;
    private String email;

    private String passwordHash;
    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private boolean isActive;
    private DateTime createdOn;
    private EnumUserRole role;

    public User(FewQuery query) throws Exception {
        super(query);
    }

    public User()  throws Exception{

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
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

    public DateTime getCreatedOn() {
        return createdOn;
    }

    private void setCreatedOn(long createdOn) {
        this.createdOn = new DateTime(createdOn * 1000);
    }

    @Override
    public void processQuery(FewQuery query) {
        this.setId(query.getValue("id").asInt());
        this.setEmail(query.getValue("email").asString());
        this.setPasswordHash(query.getValue("password_hash").asString());
        this.setFirstname(query.getValue("firstname").asString());
        this.setLastname(query.getValue("lastname").asString());
        this.setTelephoneNumber(query.getValue("tel_num").asString());
        this.setActive(query.getValue("is_active").asBoolean());
        this.setCreatedOn(query.getValue("created_on").asLong());
        this.setRole(EnumUserRole.searchRoleByLevel(query.getValue("role").asInt()));
    }
}
