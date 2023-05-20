package com.onlinestore.controller;

import com.onlinestore.entity.Order;
import com.onlinestore.entity.ShoppingCart;
import com.onlinestore.service.OrderService;
import com.onlinestore.service.ShoppingCartService;
import com.onlinestore.singleton.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/orders")
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/cart/checkout")
    public String processShoppingCart() {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUser(LoginUser.getInstance().getUserData());
        Order order = orderService.saveOrder(new Order(shoppingCart.getUser()));
        orderService.addOrderItemsFromShoppingCart(shoppingCart, order);
        shoppingCart.checkout();
        shoppingCartService.saveShoppingCart(shoppingCart);

        return "redirect:/cart?success";
    }
}
