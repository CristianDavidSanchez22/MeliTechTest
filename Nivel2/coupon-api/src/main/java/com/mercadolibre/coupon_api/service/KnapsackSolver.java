package com.mercadolibre.coupon_api.service;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;


public class KnapsackSolver {
    public static List<String> solve(Map<String, Float> items, float amount) {
        // Implementación del problema de la mochila
        List<Map.Entry<String, Float>> itemList = new ArrayList<>(items.entrySet());
        int n = itemList.size();
        float[][] dp = new float[n + 1][(int) (amount + 1)];

        // Rastrear las selecciones
        boolean[][] taken = new boolean[n + 1][(int) (amount + 1)];

        // Construcción de la tabla DP
        for (int i = 1; i <= n; i++) {
            float value = itemList.get(i - 1).getValue();

            for (int w = 0; w <= amount; w++) {
                if (value <= w) {
                    if (dp[i - 1][w] < dp[i - 1][(int) (w - value)] + value) {
                        dp[i][w] = dp[i - 1][(int) (w - value)] + value;
                        taken[i][w] = true;
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Recuperar la combinación óptima
        List<String> result = new ArrayList<>();
        int w = (int) amount;
        for (int i = n; i > 0; i--) {
            if (taken[i][w]) {
                result.add(itemList.get(i - 1).getKey());
                w -= itemList.get(i - 1).getValue();
            }
        }

        return result;
    }
}
