package com.example.products.demo.controller;

import com.example.products.demo.payloads.EnrichDataRequest;
import com.example.products.demo.payloads.EnrichedProductResponse;
import com.example.products.demo.service.EnrichDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import utils.PayloadUtils;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)

class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EnrichDataService enrichDataService;

    @Test

    void postProduct_whenValidInput_shouldReturn200andEnrichedJson() throws Exception {

        EnrichDataRequest request = EnrichDataRequest.builder().name("Mouse").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();
        String expectedDescription1 = "This Mouse is ergonomic, bluetooth and rechargeable — perfect for modern users.";

       Mockito.when(enrichDataService.enrichData(request)).thenReturn(PayloadUtils.buildResponse(request, expectedDescription1));

         mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void postProduct_whenInvalidInput_shouldReturn400() throws Exception {
        EnrichDataRequest request = EnrichDataRequest.builder().name("").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();

             mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest()).andReturn();
             
    }
    
    @Test
    void getProducts_ShouldReturn200AndProductList() throws Exception {

        Mockito.when(enrichDataService.getAllEnrichedProductsData()).thenReturn(getResponseList());

        mockMvc.perform(get("/products")
                        .contentType("application/json"))
                .andExpect(status().isOk());

    }

    private List<EnrichedProductResponse> getResponseList() {

        EnrichDataRequest request = EnrichDataRequest.builder().name("Mouse").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();
        String expectedDescription1 = "This Mouse is ergonomic, bluetooth and rechargeable — perfect for modern users.";

        EnrichDataRequest request2 = EnrichDataRequest.builder().name("Keyboard").features(Arrays.asList("ergonomic", "bluetooth", "rechargeable" )).build();
        String expectedDescription2 = "This Keyboard is ergonomic, bluetooth and rechargeable — perfect for modern users.";

        return Arrays.asList(PayloadUtils.buildResponse(request, expectedDescription1),PayloadUtils.buildResponse(request2, expectedDescription2));
    }
}