package com.onlinestore.repository;

import com.onlinestore.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserAndProcessedFalse(Long userId);

}
