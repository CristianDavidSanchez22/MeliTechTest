package com.mercadolibre.coupon_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response with the optimal items to purchase and the total amount spent")
public class CouponResponse {
    @Schema(description = "List of item IDs to purchase", example = "[\"item1\", \"item2\"]")
    private List<String> items;
    @Schema(description = "Total amount spent to purchase the items", example = "80.0")
    private float totalSpent;
}