package net.itkmitl.room.portal.admin.models;

import net.itkmitl.room.libs.phatsanphon.entity.User;

public class UserComboBoxModel {
    private final User user;

    public UserComboBoxModel(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("%d: %s %s", user.getId(), user.getFirstname(), user.getLastname());
    }

    public User getUser() {
        return user;
    }
}
