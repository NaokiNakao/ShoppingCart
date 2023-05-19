package com.onlinestore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class CartDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getTotalPriceByUserId(Long userId) {
        String sql = "SELECT SUM(p.price * c.qty) AS total " +
                "FROM cart c " +
                "JOIN product p ON c.product_id = p.id " +
                "WHERE c.user_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, BigDecimal.class);
    }

    public void clearCartByUserId(Long userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

}
