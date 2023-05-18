package com.onlinestore.service;

import com.onlinestore.dao.CartDAO;
import com.onlinestore.entity.Cart;
import com.onlinestore.entity.Product;
import com.onlinestore.entity.User;
import com.onlinestore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;
    private CartDAO cartDAO;

    public CartService(CartRepository cartRepository, CartDAO cartDAO) {
        this.cartRepository = cartRepository;
        this.cartDAO = cartDAO;
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

    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    public BigDecimal getTotalPriceByUserId(Long userId) {
        return cartDAO.getTotalPriceByUserId(userId);
    }
}
