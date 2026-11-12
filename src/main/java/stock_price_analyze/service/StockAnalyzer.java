package stock_price_analyze.service;

import java.util.HashMap;
import java.util.List;

import stock_price_analyze.model.StockPrice;

import java.util.ArrayDeque;

import java.util.Arrays;
import java.util.Deque;

public class StockAnalyzer {
    public double findPriceByDate(List<StockPrice> stockPrices, String date) {
        // Hashmap
        HashMap<String, Double> map = new HashMap<>();
        for (StockPrice sp : stockPrices) {
            map.put(sp.getDate(), sp.getPrice());
        }
        if (!map.containsKey(date)) {
                throw new IllegalArgumentException("Date not found");
        }

        return map.get(date);
    }

    public int binarySearchDate(List<StockPrice> stockPrices, String targetDate){
        // Binary Search
        int left = 0;
        int right = stockPrices.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String currentDate = stockPrices.get(mid).getDate();
            if (currentDate.equals(targetDate)) {
                return mid;
            }
            else if (currentDate.compareTo(targetDate) < 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        throw new IllegalArgumentException("Date not found");
    }

    public double bestAverage(List<StockPrice> stockPrices, int k) {
        // Sliding Window
        if (k <= 0 || k > stockPrices.size()) {
            throw new IllegalArgumentException("Invalid value of k");
        }
        double windowSum = 0.0;
        for (int i = 0; i < k; i++) {
            windowSum += stockPrices.get(i).getPrice();
        }
        double bestSum = windowSum;
        for (int i = k; i < stockPrices.size(); i++) {
            windowSum += stockPrices.get(i).getPrice() - stockPrices.get(i - k).getPrice();
            bestSum = Math.max(bestSum, windowSum);
        }
        if (windowSum > bestSum) {
            bestSum = windowSum;
        }
        return bestSum / k;
    }

    public int[] nextHigherPrice(List<StockPrice> stockPrices) {
        // Monotonic Stack
        int n = stockPrices.size();
        int[] result = new int[n];

        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            double currentPrice = stockPrices.get(i).getPrice();
            while (!stack.isEmpty() && stockPrices.get(stack.peek()).getPrice() < currentPrice) {
                int previousIndex = stack.pop();
                result[previousIndex] = i;
            }
            stack.push(i);
        }
        return result;
    }
}
