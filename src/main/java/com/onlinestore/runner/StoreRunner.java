package com.onlinestore.runner;

import com.onlinestore.entity.User;
import com.onlinestore.service.UserService;
import com.onlinestore.singleton.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StoreRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        try {
            User user = userService.getUserById(79L);
            LoginUser.getInstance().setUserData(user);
            System.out.println(LoginUser.getInstance().toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
