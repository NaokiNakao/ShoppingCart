package com.onlinestore.runner;

import com.onlinestore.entity.Product;
import com.onlinestore.entity.Role;
import com.onlinestore.entity.User;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StoreRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        try {
            User user1 = new User("Naoki", "Nakao", "naokinakao", "password");
            user1.setRole(Role.ADMIN);
            User user2 = new User("Juan", "Perez", "juanperez", "password");
            User user3 = new User("Pedro", "Torres", "pedrotorres", "password");
            User user4 = new User("Melany", "Baez", "melanybaez", "1234");

            userService.saveUser(user1);
            userService.saveUser(user2);
            userService.saveUser(user3);
            userService.saveUser(user4);

            Product product1 = new Product("Reloj", BigDecimal.valueOf(254.95));
            Product product2 = new Product("TV", BigDecimal.valueOf(25000));
            Product product3 = new Product("Lavadora", BigDecimal.valueOf(17000));

            productService.saveProduct(product1);
            productService.saveProduct(product2);
            productService.saveProduct(product3);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
