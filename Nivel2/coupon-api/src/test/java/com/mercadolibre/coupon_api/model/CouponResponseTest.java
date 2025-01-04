package com.mercadolibre.coupon_api.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouponResponseTest {

    @Test
    public void testNoArgsConstructor() {
        CouponResponse response = new CouponResponse();
        assertEquals(null, response.getItems());
        assertEquals(0.0f, response.getTotalSpent());
    }

    @Test
    public void testAllArgsConstructor() {
        List<String> items = Arrays.asList("item1", "item2");
        float totalSpent = 100.0f;

        CouponResponse response = new CouponResponse(items, totalSpent);

        assertEquals(items, response.getItems());
        assertEquals(totalSpent, response.getTotalSpent());
    }

    @Test
    public void testSettersAndGetters() {
        List<String> items = Arrays.asList("item1", "item2");
        float totalSpent = 100.0f;

        CouponResponse response = new CouponResponse();
        response.setItems(items);
        response.setTotalSpent(totalSpent);

        assertEquals(items, response.getItems());
        assertEquals(totalSpent, response.getTotalSpent());
    }
}