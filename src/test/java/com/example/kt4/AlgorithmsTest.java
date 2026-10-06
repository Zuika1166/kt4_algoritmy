package com.example.kt4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmsTest {
    @Test
    void levelSumsWorkForSeveralLevels() {
        assertArrayEquals(
            new long[]{1, 5, 9},
            Task1LevelSums.levelSums(
                new long[]{1, 2, 3, 4, 5},
                new int[]{1, 3, -1, -1, -1},
                new int[]{2, 4, -1, -1, -1}
            )
        );
    }

    @Test
    void levelSumsUseLongValues() {
        assertArrayEquals(
            new long[]{
                1_000_000_000L,
                2_000_000_000L,
                4_000_000_000L
            },
            Task1LevelSums.levelSums(
                new long[]{
                    1_000_000_000L,
                    1_000_000_000L,
                    1_000_000_000L,
                    1_000_000_000L,
                    1_000_000_000L,
                    1_000_000_000L,
                    1_000_000_000L
                },
                new int[]{1, 3, 5, -1, -1, -1, -1},
                new int[]{2, 4, 6, -1, -1, -1, -1}
            )
        );
    }

    @Test
    void topKFrequentUsesValueAsTieBreaker() {
        assertArrayEquals(
            new int[]{1, 2, 4},
            Task2TopKFrequent.topKFrequent(
                new int[]{4, 4, 1, 1, 2, 2, 3},
                3
            )
        );
    }

    @Test
    void topKFrequentHandlesNegativeNumbers() {
        assertArrayEquals(
            new int[]{-2, 5},
            Task2TopKFrequent.topKFrequent(
                new int[]{5, -2, -2, 5, -2, 7},
                2
            )
        );
    }

    @Test
    void runningMedianHandlesOddAndEvenCounts() {
        assertArrayEquals(
            new long[]{10, 7, 10, 9},
            Task3RunningMedian.runningMediansTwice(
                new int[]{5, 2, 10, 4}
            )
        );
    }

    @Test
    void runningMedianHandlesNegativeHalf() {
        long[] result = Task3RunningMedian.runningMediansTwice(
            new int[]{-1, 0}
        );

        assertArrayEquals(new long[]{-2, -1}, result);
        assertEquals("-1.0 -0.5", MedianFormatter.join(result));
    }

    @Test
    void maxPathSumCanPassThroughBothChildren() {
        assertEquals(
            42,
            Task4MaxPathSum.maxPathSum(
                new long[]{-10, 9, 20, 15, 7},
                new int[]{1, -1, 3, -1, -1},
                new int[]{2, -1, 4, -1, -1}
            )
        );
    }

    @Test
    void maxPathSumHandlesAllNegativeValues() {
        assertEquals(
            -2,
            Task4MaxPathSum.maxPathSum(
                new long[]{-3, -2, -5},
                new int[]{1, -1, -1},
                new int[]{2, -1, -1}
            )
        );
    }

    @Test
    void maxPathSumHandlesDeepTreeWithoutRecursion() {
        int n = 50_000;
        long[] values = new long[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = 1;
            left[i] = i + 1 < n ? i + 1 : -1;
            right[i] = -1;
        }

        assertEquals(
            n,
            Task4MaxPathSum.maxPathSum(values, left, right)
        );
    }

    @Test
    void kthPairSumFindsCorrectPosition() {
        assertEquals(
            11,
            Task5KthPairSum.kthSmallestPairSum(
                new long[]{1, 7, 11},
                new long[]{2, 4, 6},
                5
            )
        );
    }

    @Test
    void kthPairSumCountsEqualSumsSeparately() {
        assertEquals(
            2,
            Task5KthPairSum.kthSmallestPairSum(
                new long[]{1, 1},
                new long[]{1, 1},
                4
            )
        );
    }

    @Test
    void kthPairSumHandlesNegativeValues() {
        assertEquals(
            -2,
            Task5KthPairSum.kthSmallestPairSum(
                new long[]{-5, 0},
                new long[]{-2, 3},
                3
            )
        );
    }

    @Test
    void slidingMedianHandlesOddWindow() {
        assertArrayEquals(
            new long[]{2, -2, -2, 6, 10, 12},
            Task6SlidingMedian.slidingMediansTwice(
                new int[]{1, 3, -1, -3, 5, 3, 6, 7},
                3
            )
        );
    }

    @Test
    void slidingMedianHandlesEvenWindow() {
        long[] result = Task6SlidingMedian.slidingMediansTwice(
            new int[]{1, 2, 3, 4, 5},
            4
        );

        assertArrayEquals(new long[]{5, 7}, result);
        assertEquals("2.5 3.5", MedianFormatter.join(result));
    }

    @Test
    void slidingMedianHandlesDuplicates() {
        assertArrayEquals(
            new long[]{4, 4},
            Task6SlidingMedian.slidingMediansTwice(
                new int[]{2, 2, 2},
                2
            )
        );
    }
}
