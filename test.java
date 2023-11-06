package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class test {
    public static void printCoinCounts(Map<String, Integer> coinCounts) {
        // 取得所有的 entry
        Map.Entry<String, Integer>[] entries = coinCounts.entrySet().toArray(new Map.Entry[0]);

        // 使用冒泡排序將 entry 依照 value 進行排序
        for (int i = 0; i < entries.length - 1; i++) {
            for (int j = 0; j < entries.length - 1 - i; j++) {
                if (entries[j].getValue() < entries[j + 1].getValue()) {
                    Map.Entry<String, Integer> temp = entries[j];
                    entries[j] = entries[j + 1];
                    entries[j + 1] = temp;
                }
            }
        }

        // 列印排序後的 entry
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println("找回" + entry.getKey() + " x " + entry.getValue());
        }
    }
    public static void main(String[] args) {
        Map<Integer, Map<String, Object>> products = new HashMap<>();
        products.put(1, Map.of("name", "可樂", "price", 20));
        products.put(2, Map.of("name", "雪碧", "price", 25));
        products.put(3, Map.of("name", "沙士", "price", 30));
        products.put(4, Map.of("name", "可樂果", "price", 15));
        products.put(5, Map.of("name", "蝦味先", "price", 10));

        System.out.println("商品名稱:");

        for (Map.Entry<Integer, Map<String, Object>> entry : products.entrySet()) {
            int product = entry.getKey();
            Map<String, Object> productInfo = entry.getValue();
            String name = (String) productInfo.get("name");
            int price = (int) productInfo.get("price");

            System.out.println(product + " - " + name + " - " + price);
        }

        Scanner scanner = new Scanner(System.in);

        int inputMoney;
        while (true) {
            try {
                System.out.print("請輸入投入金額:");
                inputMoney = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("請輸入一個有效的金額。");
            }
        }

        int selectedProduct;
        while (true) {
            try {
                System.out.print("請輸入介於1到5之間的數字：");
                selectedProduct = Integer.parseInt(scanner.nextLine());
                if (selectedProduct >= 1 && selectedProduct <= 5) {
                    break;
                } else {
                    System.out.println("輸入的數字不在範圍內，請重新輸入。");
                }
            } catch (NumberFormatException e) {
                System.out.println("請輸入一個有效的選項。");
            }
        }

        System.out.println("您投入的金額為: " + inputMoney);
        System.out.println("您輸入的數字為: " + selectedProduct);

        int[] coins = {50, 10, 5, 1};

        if (products.containsKey(selectedProduct)) {
            Map<String, Object> productInfo = products.get(selectedProduct);
            String productName = (String) productInfo.get("name");
            int productPrice = (int) productInfo.get("price");

            if (inputMoney >= productPrice) {
                int change = inputMoney - productPrice;
                System.out.println("您選擇了商品" + selectedProduct + " - " + productName + "，價格為" + productPrice + "，找零為NT" + change + "。");

                Map<String, Integer> coinCounts = new HashMap<>();
                for (int coin : coins) {
                    int count = change / coin;
                    if (count > 0) {
                        change -= count * coin;
                        coinCounts.put(coin + "元", count);
                    }
                }

                for (Map.Entry<String, Integer> entry : coinCounts.entrySet()) {
                    System.out.println("找回" + entry.getKey() + " x " + entry.getValue());
                }
            } else {
                System.out.println("您的金額不足以購買所選商品。");
            }
        } else {
            System.out.println("您選擇的商品不在商品列表中。");
        }
    }
}

