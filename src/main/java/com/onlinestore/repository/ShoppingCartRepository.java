package com.onlinestore.repository;

import com.onlinestore.entity.ShoppingCart;
import com.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserAndProcessedIsFalse(User user);

}
