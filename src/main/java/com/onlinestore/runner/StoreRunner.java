package com.onlinestore.runner;

import com.onlinestore.entity.Product;
import com.onlinestore.entity.User;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;
import com.onlinestore.singleton.LoginUser;
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
            User user = new User("Naoki", "Nakao", "naokinakao", "password");
            LoginUser.getInstance().setUserData(userService.saveUser(user));

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
