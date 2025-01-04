package com.mercadolibre.coupon_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.mercadolibre.coupon_api.exception.InsufficientFundsException;
import com.mercadolibre.coupon_api.repository.CachedItemRepository;
import com.mercadolibre.coupon_api.util.KnapsackSolver;
import com.mercadolibre.coupon_api.model.CouponResponse;

@Service
public class CouponService {

    private final CachedItemRepository cachedItemRepository;

    public CouponService(CachedItemRepository cachedItemRepository) {
        this.cachedItemRepository = cachedItemRepository;
    }

    public CouponResponse calculateOptimalItems(List<String> itemIds, float amount) {
        Map<String, Float> items = cachedItemRepository.getPrices(itemIds);

        // Implementación del problema de la mochila
        List<String> optimalItems = KnapsackSolver.solve(items, amount);
        float totalSpent = optimalItems.stream()
                .map(items::get)
                .reduce(0f, Float::sum);

        if (optimalItems.isEmpty()) {
            throw new InsufficientFundsException("Insufficient funds to purchase items.");
        }

        return new CouponResponse(optimalItems, totalSpent);
    }
}

