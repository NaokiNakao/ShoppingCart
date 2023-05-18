package com.onlinestore.service;

import com.onlinestore.entity.Cart;
import com.onlinestore.entity.Product;
import com.onlinestore.entity.User;
import com.onlinestore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Cart> getCartItemsByUser(User user) {
        return cartRepository.findItemsByUser(user);
    }
}
