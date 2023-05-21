package com.onlinestore.controller;

import com.onlinestore.dto.UserDTO;
import com.onlinestore.entity.User;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;
import com.onlinestore.singleton.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO, Model model) {
        if (userDTO.getUsername() != null && userService.isUsernameTaken(userDTO.getUsername())) {
            model.addAttribute("error", "El nombre de usuario ya está en uso");
            return "register";
        }

        User newUser = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getPassword());
        userService.createUser(newUser);

        return "redirect:/login";
    }

}
