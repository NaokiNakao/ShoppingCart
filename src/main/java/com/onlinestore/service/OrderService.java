package com.onlinestore.service;

import com.onlinestore.entity.Item;
import com.onlinestore.entity.Order;
import com.onlinestore.entity.ShoppingCart;
import com.onlinestore.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void addOrderItemsFromShoppingCart(ShoppingCart shoppingCart, Order order) {
        for (Item item : shoppingCart.getCartItems()) {
            item.setOrder(order);
        }
    }
}
