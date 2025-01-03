package com.mercadolibre.coupon_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class CouponApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponApiApplication.class, args);
	}

}
