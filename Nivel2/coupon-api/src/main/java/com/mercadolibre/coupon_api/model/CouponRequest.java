package com.mercadolibre.coupon_api.model;

import java.util.List;
public class CouponRequest {
    private List<String> itemIds;
    private float amount;
    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    // Getters y setters
}



