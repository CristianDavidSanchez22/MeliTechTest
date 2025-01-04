package com.mercadolibre.coupon_api.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.mercadolibre.coupon_api.service.CouponService;
import java.util.ArrayList;
import java.util.List;
import com.mercadolibre.coupon_api.model.CouponRequest;
import com.mercadolibre.coupon_api.model.CouponResponse;
import com.mercadolibre.coupon_api.exception.InsufficientFundsException;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    @GetMapping
    public String hello() {
        return "Hello, Coupon API!";
    }

    @PostMapping
    public ResponseEntity<?> getOptimalItems(@RequestBody CouponRequest request) {
        try {
            List<String> uniqueItemIds = new ArrayList<>(request.getItemIds());
            CouponResponse response = couponService.calculateOptimalItems(uniqueItemIds, request.getAmount());
            return ResponseEntity.ok(response);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No items can be purchased with the given amount.");
        }
    }
}

