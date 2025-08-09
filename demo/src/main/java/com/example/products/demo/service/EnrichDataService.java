package com.example.products.demo.service;

import com.example.products.demo.payloads.EnrichDataRequest;
import com.example.products.demo.payloads.EnrichedProductResponse;

import java.util.List;

public interface EnrichDataService {

    EnrichedProductResponse enrichData (EnrichDataRequest enrichDataRequest);

    List<EnrichedProductResponse> getAllEnrichedProductsData();
}
