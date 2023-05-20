package com.onlinestore.service;

import com.onlinestore.entity.ShoppingCart;
import com.onlinestore.repository.ShoppingCartRepository;
import com.onlinestore.singleton.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart getShoppingCartByUser(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndProcessedFalse(userId);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(LoginUser.getInstance().getUserData());
            shoppingCartRepository.save(shoppingCart);
        }

        return shoppingCart;
    }

}
