package com.mercadolibre.coupon_api.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnapsackSolverTest {

    @Test
    public void testSolveWithSufficientFunds() {
        Map<String, Float> items = new HashMap<>();
        items.put("item1", 30.0f);
        items.put("item2", 50.0f);
        items.put("item3", 60.0f);

        float amount = 80.0f;

        List<String> result = KnapsackSolver.solve(items, amount);

        // Verifica que los elementos seleccionados sean los esperados
        assertEquals(List.of("item1", "item2"), result);
    }

    @Test
    public void testSolveWithExactFunds() {
        Map<String, Float> items = new HashMap<>();
        items.put("item1", 30.0f);
        items.put("item2", 50.0f);

        float amount = 80.0f;

        List<String> result = KnapsackSolver.solve(items, amount);

        // Verifica que los elementos seleccionados sean los esperados
        assertEquals(List.of("item1", "item2"), result);
    }

    @Test
    public void testSolveWithInsufficientFunds() {
        Map<String, Float> items = new HashMap<>();
        items.put("item1", 30.0f);
        items.put("item2", 50.0f);

        float amount = 20.0f;

        List<String> result = KnapsackSolver.solve(items, amount);

        // Verifica que no se seleccionen elementos
        assertEquals(List.of(), result);
    }
}
