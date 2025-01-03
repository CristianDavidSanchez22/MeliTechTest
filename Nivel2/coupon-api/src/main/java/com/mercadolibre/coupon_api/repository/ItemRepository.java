package com.mercadolibre.coupon_api.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mercadolibre.coupon_api.model.Item;

@FeignClient(name = "item-api", url = "https://api.mercadolibre.com")
public interface ItemRepository {

    @GetMapping("/items/{itemId}")
    Item getItem(@PathVariable String itemId);

    default Map<String, Float> getPrices(List<String> itemIds) {
        return itemIds.stream()
                .collect(Collectors.toMap(id -> id, id -> getItem(id).getPrice()));
    }
}
