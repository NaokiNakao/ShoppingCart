package com.onlinestore.singleton;

import com.onlinestore.entity.Role;
import com.onlinestore.entity.User;

public class LoginUser {

    private static LoginUser instance;
    private User userData;

    private LoginUser() {
        this.userData = new User();
    }

    public static LoginUser getInstance() {
        if (instance == null) {
            instance = new LoginUser();
        }
        return instance;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    public boolean isAdmin() {
        boolean isAdmin = false;

        if (userData.getRole() == Role.ADMIN) {
            isAdmin = true;
        }

        return isAdmin;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "userData=" + userData +
                '}';
    }
}
