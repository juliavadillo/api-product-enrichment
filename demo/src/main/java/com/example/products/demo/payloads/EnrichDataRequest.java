package com.example.products.demo.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EnrichDataRequest {

    @NotBlank (message="The product's name is mandatory")
    private String name;

    @NotNull
    private List<String> features;
}
