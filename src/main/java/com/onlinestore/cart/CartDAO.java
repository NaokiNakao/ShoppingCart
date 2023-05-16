package com.onlinestore.cart;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartDAO {

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    void addQtyColumn() {
        String sql = "ALTER TABLE cart_products ADD COLUMN IF NOT EXISTS qty integer";
        jdbcTemplate.execute(sql);
    }

}
