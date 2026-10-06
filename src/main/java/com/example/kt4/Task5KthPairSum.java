package com.example.kt4;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class Task5KthPairSum {
    private Task5KthPairSum() {
    }

    private record State(
        int firstIndex,
        int secondIndex,
        long sum
    ) {
    }

    public static long kthSmallestPairSum(
        long[] first,
        long[] second,
        int k
    ) {
        if (first.length > second.length) {
            return kthSmallestPairSum(second, first, k);
        }

        PriorityQueue<State> queue = new PriorityQueue<>(
            Comparator.comparingLong(State::sum)
                .thenComparingInt(State::firstIndex)
                .thenComparingInt(State::secondIndex)
        );

        int rows = Math.min(first.length, k);

        for (int i = 0; i < rows; i++) {
            queue.add(
                new State(
                    i,
                    0,
                    first[i] + second[0]
                )
            );
        }

        long answer = 0;

        for (int count = 0; count < k; count++) {
            State current = queue.poll();
            answer = current.sum();

            int nextSecond = current.secondIndex() + 1;

            if (nextSecond < second.length) {
                queue.add(
                    new State(
                        current.firstIndex(),
                        nextSecond,
                        first[current.firstIndex()] + second[nextSecond]
                    )
                );
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        long[] first = new long[n];
        long[] second = new long[m];

        for (int i = 0; i < n; i++) {
            first[i] = scanner.nextLong();
        }

        for (int i = 0; i < m; i++) {
            second[i] = scanner.nextLong();
        }

        System.out.println(kthSmallestPairSum(first, second, k));
    }
}
