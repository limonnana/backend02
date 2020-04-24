package com.limonnana.backend02.controller;


import com.google.gson.Gson;
import com.limonnana.backend02.entity.Product;
import com.limonnana.backend02.entity.User;
import com.limonnana.backend02.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
@RequestMapping("/secure/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value="/findAll")
    public List findAll(@RequestHeader Map<String, String> m) {

        List<Product> l = productRepository.findAll();

        return l;
    }

    @PostMapping(value="/create")
    public Product create(@RequestBody Product product) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(product));
        return productRepository.save(product);
    }

}
