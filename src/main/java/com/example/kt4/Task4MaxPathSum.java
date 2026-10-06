package com.example.kt4;

public final class Task4MaxPathSum {
    private Task4MaxPathSum() {
    }

    public static long maxPathSum(
        long[] values,
        int[] left,
        int[] right
    ) {
        int n = values.length;
        int[] stack = new int[n];
        int[] order = new int[n];
        int stackSize = 0;
        int orderSize = 0;

        stack[stackSize++] = 0;

        while (stackSize > 0) {
            int node = stack[--stackSize];
            order[orderSize++] = node;

            if (left[node] != -1) {
                stack[stackSize++] = left[node];
            }

            if (right[node] != -1) {
                stack[stackSize++] = right[node];
            }
        }

        long[] downward = new long[n];
        long best = Long.MIN_VALUE;

        for (int i = orderSize - 1; i >= 0; i--) {
            int node = order[i];

            long leftGain = left[node] == -1
                ? 0
                : Math.max(0L, downward[left[node]]);

            long rightGain = right[node] == -1
                ? 0
                : Math.max(0L, downward[right[node]]);

            long throughNode = values[node] + leftGain + rightGain;

            if (throughNode > best) {
                best = throughNode;
            }

            downward[node] =
                values[node] + Math.max(leftGain, rightGain);
        }

        return best;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        long[] values = new long[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextLong();
            left[i] = scanner.nextInt();
            right[i] = scanner.nextInt();
        }

        System.out.println(maxPathSum(values, left, right));
    }
}
