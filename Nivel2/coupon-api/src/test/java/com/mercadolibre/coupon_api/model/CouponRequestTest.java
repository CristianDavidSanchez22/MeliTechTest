package com.mercadolibre.coupon_api.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouponRequestTest {

    @Test
    public void testAllArgsConstructor() {
        Set<String> itemIds = new HashSet<>();
        itemIds.add("item1");
        itemIds.add("item2");
        float amount = 100.0f;

        CouponRequest request = new CouponRequest(itemIds, amount);

        assertEquals(itemIds, request.getItemIds());
        assertEquals(amount, request.getAmount());
    }

    @Test
    public void testSettersAndGetters() {
        Set<String> itemIds = new HashSet<>();
        itemIds.add("item1");
        itemIds.add("item2");
        float amount = 100.0f;

        CouponRequest request = new CouponRequest();
        request.setItemIds(itemIds);
        request.setAmount(amount);

        assertEquals(itemIds, request.getItemIds());
        assertEquals(amount, request.getAmount());
    }
}