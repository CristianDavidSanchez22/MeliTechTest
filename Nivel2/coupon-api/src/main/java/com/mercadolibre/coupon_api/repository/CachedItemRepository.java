package com.mercadolibre.coupon_api.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.time.Duration;

@Repository
public class CachedItemRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ItemRepository itemRepository;

    public CachedItemRepository(RedisTemplate<String, Object> redisTemplate, ItemRepository itemRepository) {
        this.redisTemplate = redisTemplate;
        this.itemRepository = itemRepository;
    }

    public Map<String, Float> getPrices(List<String> itemIds) {
        Map<String, Float> prices = new HashMap<>();
        List<String> missingItems = new ArrayList<>();

        for (String itemId : itemIds) {
            Float price = (Float) redisTemplate.opsForValue().get(itemId);
            if (price != null) {
                prices.put(itemId, price);
            } else {
                missingItems.add(itemId);
            }
        }

        if (!missingItems.isEmpty()) {
            missingItems.forEach(itemId -> {
                Float price = itemRepository.getItem(itemId).getPrice();
                prices.put(itemId, price);
                redisTemplate.opsForValue().set(itemId, price, Duration.ofHours(1));
            });
        }

        return prices;
    }
}

