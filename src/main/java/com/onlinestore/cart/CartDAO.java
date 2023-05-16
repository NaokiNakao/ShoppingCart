package com.onlinestore.cart;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class CartDAO {

    String url = "jdbc:postgresql://localhost/online_store_db?user=postgres&password=password&ssl=false";
    Connection conn = DriverManager.getConnection(url);

    public CartDAO() throws SQLException {
    }

    @PostConstruct
    void createQtyColumn() throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("ALTER TABLE cart_products ADD COLUMN qty integer");
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
