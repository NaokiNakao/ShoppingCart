package com.onlinestore.service;

import com.onlinestore.entity.Cart;
import com.onlinestore.entity.Product;
import com.onlinestore.entity.User;
import com.onlinestore.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartByProductAndUser(Product product, User user) {
        return cartRepository.findByProductAndUser(product, user);
    }
}
