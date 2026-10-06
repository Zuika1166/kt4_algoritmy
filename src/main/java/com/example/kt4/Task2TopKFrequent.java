package com.example.kt4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task2TopKFrequent {
    private Task2TopKFrequent() {
    }

    public static int[] topKFrequent(int[] numbers, int k) {
        Map<Integer, Integer> frequencies = new HashMap<>();

        for (int number : numbers) {
            frequencies.merge(number, 1, Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> entries =
            new ArrayList<>(frequencies.entrySet());

        entries.sort((first, second) -> {
            int byFrequency = Integer.compare(
                second.getValue(),
                first.getValue()
            );

            if (byFrequency != 0) {
                return byFrequency;
            }

            return Integer.compare(first.getKey(), second.getKey());
        });

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int[] result = topKFrequent(numbers, k);
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < result.length; i++) {
            if (i > 0) {
                output.append(' ');
            }

            output.append(result[i]);
        }

        System.out.println(output);
    }
}
