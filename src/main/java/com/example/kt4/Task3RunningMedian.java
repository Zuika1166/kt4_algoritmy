package com.example.kt4;

import java.util.Collections;
import java.util.PriorityQueue;

public final class Task3RunningMedian {
    private Task3RunningMedian() {
    }

    public static long[] runningMediansTwice(int[] numbers) {
        PriorityQueue<Integer> lower =
            new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> upper = new PriorityQueue<>();
        long[] result = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int value = numbers[i];

            if (lower.isEmpty() || value <= lower.peek()) {
                lower.add(value);
            } else {
                upper.add(value);
            }

            if (lower.size() > upper.size() + 1) {
                upper.add(lower.poll());
            } else if (upper.size() > lower.size()) {
                lower.add(upper.poll());
            }

            if (lower.size() == upper.size()) {
                result[i] = (long) lower.peek() + upper.peek();
            } else {
                result[i] = 2L * lower.peek();
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(
            MedianFormatter.join(runningMediansTwice(numbers))
        );
    }
}
