package com.onlinestore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @SequenceGenerator(
            name = "item_id_sequence",
            sequenceName = "item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "qty", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public Item() {
    }

    public Item(Long id, Product product, Integer quantity, ShoppingCart shoppingCart, Order order) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void updateQuantity(int addedQuantity) {
        quantity += addedQuantity;
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", shoppingCart=" + shoppingCart +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(product, item.product) && Objects.equals(quantity, item.quantity) && Objects.equals(shoppingCart, item.shoppingCart) && Objects.equals(order, item.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, shoppingCart, order);
    }

}
