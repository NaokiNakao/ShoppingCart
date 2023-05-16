package com.onlinestore.catalog;

import com.onlinestore.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {

    private ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "catalog";
    }

}
