package com.onlinestore.service;

import com.onlinestore.entity.ShoppingCart;
import com.onlinestore.entity.User;
import com.onlinestore.repository.ShoppingCartRepository;
import com.onlinestore.singleton.LoginUser;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartByUser(User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserAndProcessedIsFalse(user);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(LoginUser.getInstance().getUserData());
            shoppingCartRepository.save(shoppingCart);
        }

        return shoppingCart;
    }

}
