package com.mercadolibre.coupon_api.model;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request to calculate optimal items for a given coupon amount")
public class CouponRequest {
    @Schema(description = "List of item IDs to consider for the calculation", example = "[\"item1\", \"item2\"]")
    private Set<String> itemIds;
    @Schema(description = "Amount of the coupon", example = "100.0")
    private float amount;
}



