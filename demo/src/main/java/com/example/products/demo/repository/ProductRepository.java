package com.example.products.demo.repository;

import com.example.products.demo.payloads.EnrichedProductResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<EnrichedProductResponse> products = new ArrayList<>();

    public void save(EnrichedProductResponse product) {
        products.add(product);
    }

    public List<EnrichedProductResponse> findAll() {
        return Collections.unmodifiableList(products);
    }
}
