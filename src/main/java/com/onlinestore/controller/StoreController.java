package com.onlinestore.controller;

import com.onlinestore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    private ProductService productService;

    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "catalog";
    }

}
