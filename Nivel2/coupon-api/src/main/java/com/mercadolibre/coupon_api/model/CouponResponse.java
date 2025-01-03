package com.mercadolibre.coupon_api.model;

import java.util.List;

public class CouponResponse {
    private List<String> items;
    private float totalSpent;

    public CouponResponse(List<String> items, float totalSpent) {
        this.items = items;
        this.totalSpent = totalSpent;
    }

    // Getters y setters
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public float getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(float totalSpent) {
        this.totalSpent = totalSpent;
    }
}