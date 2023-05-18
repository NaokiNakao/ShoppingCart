package com.onlinestore.configuration;

import com.onlinestore.entity.User;
import com.onlinestore.service.UserService;
import com.onlinestore.singleton.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StoreConfiguration implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = null, user2 = null;

        try {
            user1 = userService.saveUser(new User("Naoki", "Nakao", "naokinakao", "password"));
        }
        catch (Exception e) {

        }

        try {
            user2 = userService.saveUser(new User("Juan", "Perez", "juanperez", "password"));
        }
        catch (Exception e) {

        }

        LoginUser.getInstance().setUserData(user2);
        System.out.println(LoginUser.getInstance().getUserData().toString());
    }
}
