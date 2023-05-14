package com.onlinestore.security;

import com.onlinestore.user.User;
import com.onlinestore.user.UserDTO;
import com.onlinestore.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDTO userDTO, Model model){
        model.addAttribute("user", userDTO);
        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    /*@PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model){
        User existingUser = userService.getUserByEmail(userDTO.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDTO);
            return "/register";
        }

        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }*/

}
