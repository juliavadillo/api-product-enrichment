package utils;

import com.example.products.demo.payloads.EnrichDataRequest;
import com.example.products.demo.payloads.EnrichedProductResponse;

public class PayloadUtils {

    public static EnrichedProductResponse buildResponse(EnrichDataRequest request1, String expectedDescription1) {
        return EnrichedProductResponse.builder().name(request1.getName())
                .features(request1.getFeatures())
                .description(expectedDescription1).build();
    }
}
