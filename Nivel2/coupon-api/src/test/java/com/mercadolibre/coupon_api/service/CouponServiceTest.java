package com.mercadolibre.coupon_api.service;

import com.mercadolibre.coupon_api.model.CouponResponse;
import com.mercadolibre.coupon_api.exception.InsufficientFundsException;
import com.mercadolibre.coupon_api.repository.CachedItemRepository;
import com.mercadolibre.coupon_api.util.KnapsackSolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mockStatic;

public class CouponServiceTest {

    @Mock
    private CachedItemRepository cachedItemRepository;

    @InjectMocks
    private CouponService couponService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateOptimalItemsSuccess() {
        List<String> itemIds = List.of("item1", "item2");
        float amount = 100.0f;

        Map<String, Float> items = new HashMap<>();
        items.put("item1", 30.0f);
        items.put("item2", 50.0f);

        when(cachedItemRepository.getPrices(itemIds)).thenReturn(items);

        try (MockedStatic<KnapsackSolver> knapsackSolverMockedStatic = mockStatic(KnapsackSolver.class)) {
            knapsackSolverMockedStatic.when(() -> KnapsackSolver.solve(items, amount)).thenReturn(List.of("item1", "item2"));

            CouponResponse response = couponService.calculateOptimalItems(itemIds, amount);

            assertEquals(List.of("item1", "item2"), response.getItems());
            assertEquals(80.0f, response.getTotalSpent());
        }
    }

    @Test
    public void testCalculateOptimalItemsInsufficientFunds() {
        List<String> itemIds = List.of("item1", "item2");
        float amount = 10.0f;

        Map<String, Float> items = new HashMap<>();
        items.put("item1", 30.0f);
        items.put("item2", 50.0f);

        when(cachedItemRepository.getPrices(itemIds)).thenReturn(items);

        try (MockedStatic<KnapsackSolver> knapsackSolverMockedStatic = mockStatic(KnapsackSolver.class)) {
            knapsackSolverMockedStatic.when(() -> KnapsackSolver.solve(items, amount)).thenReturn(List.of());

            assertThrows(InsufficientFundsException.class, () -> {
                couponService.calculateOptimalItems(itemIds, amount);
            });
        }
    }
}