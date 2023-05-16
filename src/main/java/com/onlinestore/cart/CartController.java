package com.onlinestore.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class CartController {

    @GetMapping("/cart")
    public String showCart() {
        return "cart";
    }

    @PostMapping("catalog{id}")
    public String addToCart(@RequestBody Cart cart, @PathVariable("id") Long id, @RequestParam("quantity") Integer quantity) {
        return "redirect:/catalog?success";
    }

}
