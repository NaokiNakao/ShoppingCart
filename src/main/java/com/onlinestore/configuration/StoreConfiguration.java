package com.onlinestore.configuration;

import com.onlinestore.entity.User;
import com.onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StoreConfiguration implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Naoki", "Nakao", "naokinakao", "password");

        try {
            userService.saveUser(user1);
        }
        catch (Exception e) {
            System.out.println("ERORRRRRRRRRRR!!!!!!!!");
        }
    }
}
