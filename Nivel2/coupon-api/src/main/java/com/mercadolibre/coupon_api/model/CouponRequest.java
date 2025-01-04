package com.mercadolibre.coupon_api.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponRequest {
    private List<String> itemIds;
    private float amount;
}



