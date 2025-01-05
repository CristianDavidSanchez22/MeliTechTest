package com.mercadolibre.coupon_api.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.mercadolibre.coupon_api.service.CouponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.mercadolibre.coupon_api.model.CouponResponse;
import java.util.ArrayList;
import java.util.List;
import com.mercadolibre.coupon_api.model.CouponRequest;
import com.mercadolibre.coupon_api.exception.InsufficientFundsException;

@RestController
@RequestMapping("/coupon")
@Tag(name = "Coupon API", description = "API to calculate optimal items for a given coupon amount")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }
    @GetMapping
    @Operation(summary = "Healthcheck", description = "health check")
    public String hello() {
        return "Hello, Coupon API!";
    }
    @PostMapping
    @Operation(summary = "Calculate optimal items", description = "Calculate the optimal items to purchase with the given amount")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully calculated optimal items",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = CouponResponse.class))),
        @ApiResponse(responseCode = "404", description = "Insufficient funds to purchase any items",
            content = @Content(mediaType = "application/json"))
    })
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

