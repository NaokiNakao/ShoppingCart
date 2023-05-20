package com.onlinestore.singleton;

import com.onlinestore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

    private static AuthenticationManager instance;

    public AuthenticationManager() {
    }

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean authenticate(User authenticatedUser, String password) {
        if (authenticatedUser != null && authenticatedUser.getPassword().equals(password)) {
            LoginUser.getInstance().setUserData(authenticatedUser);
            return true;
        }

        return false;
    }

}
