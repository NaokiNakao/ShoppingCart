package com.onlinestore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @SequenceGenerator(
            name = "shopping_cart_id_sequence",
            sequenceName = "shopping_cart_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shopping_cart_id_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> cartItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "processed")
    private boolean processed;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, List<Item> cartItems, boolean processed) {
        this.id = id;
        this.cartItems = cartItems;
        this.processed = processed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Item> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void checkOut() {
        processed = false;
    }

    public void addItem(Item item) {
        cartItems.add(item);
    }

    public void deleteItemById(Long deletedItemId) {
        boolean found = false;
        Iterator<Item> iterator = cartItems.iterator();

        while (iterator.hasNext() && !found) {
            Item item = iterator.next();
            if (item.getId().equals(deletedItemId)) {
                iterator.remove();
                found = true;
            }
        }
    }

    public BigDecimal getGrandTotal() {
        BigDecimal grandTotal = BigDecimal.valueOf(0);

        for (Item item : cartItems) {
            grandTotal.add(item.getTotalPrice());
        }

        return grandTotal;
    }

    public void clearCart() {
        cartItems.clear();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", user=" + user +
                ", processed=" + processed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return processed == that.processed && Objects.equals(id, that.id) && Objects.equals(cartItems, that.cartItems) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartItems, user, processed);
    }
}
