package com.mercadolibre.coupon_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponResponse {
    private List<String> items;
    private float totalSpent;
}