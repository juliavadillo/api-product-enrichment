package com.example.products.demo.service.impl;

import com.example.products.demo.payloads.EnrichDataRequest;
import com.example.products.demo.payloads.EnrichedProductResponse;
import com.example.products.demo.repository.ProductRepository;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.PayloadUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnrichDataServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    EnrichDataServiceImpl enrichDataService;

    @Test
    void shouldEnrichDescriptionWithSuccess_whenValidInput(){
        EnrichDataRequest request = EnrichDataRequest.builder().name("Mouse").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();
        String expectedDescription = "This Mouse is ergonomic, bluetooth and rechargeable — perfect for modern users.";

        EnrichedProductResponse enrichedData = enrichDataService.enrichData(request);
        assertEquals(expectedDescription, enrichedData.getDescription());
    }

    @Test
    void shouldEnrichDescriptionWithGenericDescription_whenInputWithoutFeatures(){
        EnrichDataRequest request = EnrichDataRequest.builder().name("Mouse").features(new ArrayList<>()).build();
        String expectedDescription = "The Mouse is a great product.";

        EnrichedProductResponse enrichedData = enrichDataService.enrichData(request);
        assertEquals(expectedDescription, enrichedData.getDescription());
    }

    @Test
    void shouldReturnAllEnrichedProductWithSuccess(){
        EnrichDataRequest request1= EnrichDataRequest.builder().name("Mouse").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();
        EnrichDataRequest request2= EnrichDataRequest.builder().name("Keyboard").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();

        String expectedDescription1 = "This Mouse is ergonomic, bluetooth and rechargeable — perfect for modern users.";
        String expectedDescription2 = "This Keyboard is ergonomic, bluetooth and rechargeable — perfect for modern users.";

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(PayloadUtils.buildResponse(request1, expectedDescription1),PayloadUtils.buildResponse(request2, expectedDescription2)));

        enrichDataService.enrichData(request1);
        enrichDataService.enrichData(request2);

        List<EnrichedProductResponse> enrichedProductResponseList = enrichDataService.getAllEnrichedProductsData();

        assertEquals(2, enrichedProductResponseList.size());
        assertEquals(request1.getName(),enrichedProductResponseList.get(0).getName());
        assertEquals(request1.getFeatures(),enrichedProductResponseList.get(0).getFeatures());
        assertEquals(request2.getName(),enrichedProductResponseList.get(1).getName());
        assertEquals(request2.getFeatures(),enrichedProductResponseList.get(1).getFeatures());
        assertEquals(expectedDescription1, enrichedProductResponseList.get(0).getDescription());
        assertEquals(expectedDescription2, enrichedProductResponseList.get(1).getDescription());


    }

}