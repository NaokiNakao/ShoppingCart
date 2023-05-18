package com.onlinestore.repository;

import com.onlinestore.entity.Cart;
import com.onlinestore.entity.Product;
import com.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByProductAndUser(Product product, User user);

    List<Cart> findItemsByUser(User user);
}
