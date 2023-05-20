package com.onlinestore.controller;

import com.onlinestore.entity.User;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;
import com.onlinestore.singleton.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StoreController {

    private final ProductService productService;
    private final UserService userService;

    public StoreController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "catalog";
    }

    @GetMapping({"/", "login"})
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String storeLogin(String username, String password, Model model) {
        AuthenticationManager authManager = AuthenticationManager.getInstance();
        User requestUser = userService.getUserByUsername(username);

        boolean authenticated = authManager.authenticate(requestUser, password);

        if (authenticated) {
            return "redirect:/catalog";
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
            return "login";
        }
    }

}
