package com.onlinestore.controller;

import com.onlinestore.entity.Cart;
import com.onlinestore.entity.Product;
import com.onlinestore.entity.User;
import com.onlinestore.service.CartService;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;
import com.onlinestore.singleton.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private UserService userService;

    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/catalog")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "catalog";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getCartItemsByUser(LoginUser.getInstance().getUserData()));
        return "cart";
    }

    @PostMapping("/catalog/{id}")
    public String addToCart(@PathVariable("id") long productId, @ModelAttribute("product") Product product, @RequestParam("qty") int quantity) throws Exception {
        Product selectedProduct =  productService.getProductById(productId);
        User currentUser = userService.getUserById(LoginUser.getInstance().getUserData().getId());
        Cart cart = cartService.getCartByProductAndUser(selectedProduct, currentUser);

        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + quantity);
            cartService.saveCart(cart);
        } else {
            cart = new Cart(currentUser, selectedProduct, quantity);
            cartService.saveCart(cart);
        }

        return "redirect:/catalog?success";
    }

}
