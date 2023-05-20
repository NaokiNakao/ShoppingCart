package com.onlinestore.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(Long id, Date purchaseDate, List<Item> orderItems, User user) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.orderItems = orderItems;
        this.user = user;
    }

    public Order(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getGrandTotal() {
        double grandTotal = 0;

        for (Item item : orderItems) {
            grandTotal += item.getTotalPrice().doubleValue();
            System.out.println(item.getTotalPrice().doubleValue());
        }

        return grandTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", orderItems=" + orderItems +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(purchaseDate, order.purchaseDate) && Objects.equals(orderItems, order.orderItems) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purchaseDate, orderItems, user);
    }

}
