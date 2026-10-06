package com.example.kt4;

import java.util.TreeMap;

public final class Task6SlidingMedian {
    private Task6SlidingMedian() {
    }

    public static long[] slidingMediansTwice(
        int[] numbers,
        int windowSize
    ) {
        MedianWindow window = new MedianWindow();

        for (int i = 0; i < windowSize; i++) {
            window.add(numbers[i]);
        }

        long[] result = new long[numbers.length - windowSize + 1];
        result[0] = window.medianTwice();

        for (int right = windowSize; right < numbers.length; right++) {
            window.remove(numbers[right - windowSize]);
            window.add(numbers[right]);
            result[right - windowSize + 1] = window.medianTwice();
        }

        return result;
    }

    private static final class MedianWindow {
        private final TreeMap<Integer, Integer> lower = new TreeMap<>();
        private final TreeMap<Integer, Integer> upper = new TreeMap<>();
        private int lowerSize;
        private int upperSize;

        void add(int value) {
            if (lowerSize == 0 || value <= lower.lastKey()) {
                addOne(lower, value);
                lowerSize++;
            } else {
                addOne(upper, value);
                upperSize++;
            }

            rebalance();
        }

        void remove(int value) {
            if (lower.containsKey(value)) {
                removeOne(lower, value);
                lowerSize--;
            } else {
                removeOne(upper, value);
                upperSize--;
            }

            rebalance();
        }

        long medianTwice() {
            if (lowerSize > upperSize) {
                return 2L * lower.lastKey();
            }

            return (long) lower.lastKey() + upper.firstKey();
        }

        private void rebalance() {
            while (lowerSize > upperSize + 1) {
                int value = lower.lastKey();
                removeOne(lower, value);
                lowerSize--;
                addOne(upper, value);
                upperSize++;
            }

            while (lowerSize < upperSize) {
                int value = upper.firstKey();
                removeOne(upper, value);
                upperSize--;
                addOne(lower, value);
                lowerSize++;
            }
        }

        private static void addOne(
            TreeMap<Integer, Integer> map,
            int value
        ) {
            map.merge(value, 1, Integer::sum);
        }

        private static void removeOne(
            TreeMap<Integer, Integer> map,
            int value
        ) {
            int count = map.get(value);

            if (count == 1) {
                map.remove(value);
            } else {
                map.put(value, count - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(
            MedianFormatter.join(
                slidingMediansTwice(numbers, k)
            )
        );
    }
}
