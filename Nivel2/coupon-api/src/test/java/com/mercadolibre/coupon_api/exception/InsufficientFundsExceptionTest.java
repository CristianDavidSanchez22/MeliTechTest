package com.mercadolibre.coupon_api.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsufficientFundsExceptionTest {

    @Test
    public void testDefaultConstructor() {
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> {
            throw new InsufficientFundsException();
        });
        assertEquals("Insufficient funds to purchase items.", exception.getMessage());
    }

    @Test
    public void testMessageConstructor() {
        String customMessage = "Custom insufficient funds message.";
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> {
            throw new InsufficientFundsException(customMessage);
        });
        assertEquals(customMessage, exception.getMessage());
    }
}