package com.mercadolibre.coupon_api.model;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequest {
    private Set<String> itemIds;
    private float amount;
}



