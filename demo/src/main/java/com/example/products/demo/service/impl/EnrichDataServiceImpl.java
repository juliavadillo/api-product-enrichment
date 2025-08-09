package com.example.products.demo.service.impl;

import com.example.products.demo.payloads.EnrichDataRequest;
import com.example.products.demo.payloads.EnrichedProductResponse;
import com.example.products.demo.repository.ProductRepository;
import com.example.products.demo.service.EnrichDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnrichDataServiceImpl implements EnrichDataService {


    private ProductRepository productRepository;

    @Override
    public EnrichedProductResponse enrichData(EnrichDataRequest enrichDataRequest) {
        EnrichedProductResponse enrichedProduct = new EnrichedProductResponse(enrichDataRequest.getName(), enrichDataRequest.getFeatures(), setProductDescription(enrichDataRequest));
       productRepository.save(enrichedProduct);
        return enrichedProduct;
    }
    @Override
    public List<EnrichedProductResponse> getAllEnrichedProductsData(){
        return productRepository.findAll();
    }

    private String setProductDescription(EnrichDataRequest enrichDataRequest) {
        List<String> features = enrichDataRequest.getFeatures();
        String name = enrichDataRequest.getName();
        if (features == null || features.isEmpty()) {
            return "The " + name + " is a great product.";
        }
        String featureList = String.join(", ", features);
        if (features.size() > 1) {
            int lastComma = featureList.lastIndexOf(", ");
            if (lastComma != -1) {
                featureList = featureList.substring(0, lastComma) + " and" + featureList.substring(lastComma + 1);
            }
        }

        return "This " + name + " is " + featureList + " — perfect for modern users.";

    }
}
