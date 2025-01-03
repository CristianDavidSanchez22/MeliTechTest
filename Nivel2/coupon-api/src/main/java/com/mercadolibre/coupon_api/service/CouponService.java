package com.mercadolibre.coupon_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.mercadolibre.coupon_api.exception.InsufficientFundsException;

import com.mercadolibre.coupon_api.model.CouponResponse;
import com.mercadolibre.coupon_api.repository.ItemRepository;

@Service
public class CouponService {

    private final ItemRepository itemRepository;

    public CouponService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public CouponResponse calculateOptimalItems(List<String> itemIds, float amount) {
        Map<String, Float> items = itemRepository.getPrices(itemIds);

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

