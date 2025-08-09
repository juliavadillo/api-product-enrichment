package com.example.products.demo.payloads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EnrichedProductResponse {

    private String name;
    private List<String> features;
    private String description;
}
