package com.onlinestore.cart;

import com.onlinestore.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_id_sequence"
    )
    private Long id;

    @ManyToMany
    private List<Product> products;

}
