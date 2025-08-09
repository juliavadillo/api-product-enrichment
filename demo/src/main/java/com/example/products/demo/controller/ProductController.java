package com.example.products.demo.controller;

import com.example.products.demo.payloads.EnrichDataRequest;
import com.example.products.demo.payloads.EnrichedProductResponse;
import com.example.products.demo.service.EnrichDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private EnrichDataService enrichDataService;

    @PostMapping
    public ResponseEntity<EnrichedProductResponse> enrichProductData(@Valid @RequestBody EnrichDataRequest enrichDataRequest) {

        return ResponseEntity.ok(enrichDataService.enrichData(enrichDataRequest));
    }

    @GetMapping
    public ResponseEntity<List<EnrichedProductResponse>> getEnrichedProductsData(){
        return ResponseEntity.ok(enrichDataService.getAllEnrichedProductsData());
    }
}
