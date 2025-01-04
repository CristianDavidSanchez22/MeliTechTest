package com.mercadolibre.coupon_api.controller;

import com.mercadolibre.coupon_api.model.CouponRequest;
import com.mercadolibre.coupon_api.model.CouponResponse;
import com.mercadolibre.coupon_api.service.CouponService;
import com.mercadolibre.coupon_api.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CouponControllerTest {

    @Mock
    private CouponService couponService;

    @InjectMocks
    private CouponController couponController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOptimalItemsSuccess() {
        Set<String> itemIds = new HashSet<>();
        itemIds.add("item1");
        itemIds.add("item2");
        CouponRequest request = new CouponRequest(itemIds, 100.0f);

        List<String> optimalItems = List.of("item1", "item2");
        CouponResponse response = new CouponResponse(optimalItems, 90.0f);

        when(couponService.calculateOptimalItems(List.copyOf(itemIds), request.getAmount())).thenReturn(response);

        ResponseEntity<?> result = couponController.getOptimalItems(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetOptimalItemsInsufficientFunds() {
        Set<String> itemIds = new HashSet<>();
        itemIds.add("item1");
        itemIds.add("item2");
        CouponRequest request = new CouponRequest(itemIds, 10.0f);

        when(couponService.calculateOptimalItems(List.copyOf(itemIds), request.getAmount())).thenThrow(new InsufficientFundsException());

        ResponseEntity<?> result = couponController.getOptimalItems(request);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("No items can be purchased with the given amount.", result.getBody());
    }
}