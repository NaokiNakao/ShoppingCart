package com.onlinestore.controller;

import com.onlinestore.service.ShoppingCartService;
import org.springframework.stereotype.Controller;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


}
