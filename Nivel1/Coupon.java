import java.util.*;

public class Coupon {
    public static void main(String[] args) {
        Map<String, Float> items = new HashMap<>();
        items.put("MLA1", 100f);
        items.put("MLA2", 210f);
        items.put("MLA3", 220f);
        items.put("MLA4", 80f);
        items.put("MLA5", 90f);
        //items.put("MLA6", 10f);
        float maxAmount = 500f;

        Coupon main = new Coupon();
        List<String> result = main.calculate(items, maxAmount);
        System.out.println("Combinación óptima: " + result);
    }

    public List<String> calculate(Map<String, Float> items, Float amount) {
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
        int w = amount.intValue();
        for (int i = n; i > 0; i--) {
            if (taken[i][w]) {
                result.add(itemList.get(i - 1).getKey());
                w -= itemList.get(i - 1).getValue();
            }
        }

        return result;
    }
}
