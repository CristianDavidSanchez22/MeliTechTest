package com.mercadolibre.coupon_api.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds to purchase items.");
    }
    public InsufficientFundsException(String message) {
        super(message);
    }
    
}
