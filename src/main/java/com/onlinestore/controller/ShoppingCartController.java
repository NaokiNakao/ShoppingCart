package com.onlinestore.controller;

import com.onlinestore.entity.Item;
import com.onlinestore.entity.Product;
import com.onlinestore.entity.ShoppingCart;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.ShoppingCartService;
import com.onlinestore.singleton.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") long productId, @ModelAttribute("product") Product product, @RequestParam("qty") int quantity) {
        // Retrieve the selected product by ID
        Product selectedProduct = productService.getProductById(productId);

        // Get the current user's shopping cart
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUser(LoginUser.getInstance().getUserData());

        // Check if the item already exists in the shopping cart
        Optional<Item> existingItem = shoppingCart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().equals(selectedProduct))
                .findFirst();

        if (existingItem.isPresent()) {
            // Item already exists in the shopping cart, update the quantity
            Item item = existingItem.get();
            item.updateQuantity(quantity);
        } else {
            // Create a new item and add it to the shopping cart
            Item newItem = new Item();
            newItem.setProduct(selectedProduct);
            newItem.setQuantity(quantity);
            newItem.setShoppingCart(shoppingCart);
            shoppingCart.addItem(newItem);
        }

        shoppingCartService.saveShoppingCart(shoppingCart);

        // Redirect to the catalog page with a success message
        return "redirect:/catalog?success";
    }
}
