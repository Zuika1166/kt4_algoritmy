package com.example.kt4;

public final class Task1LevelSums {
    private Task1LevelSums() {
    }

    public static long[] levelSums(long[] values, int[] left, int[] right) {
        int n = values.length;
        long[] sums = new long[n];
        int[] queue = new int[n];
        int head = 0;
        int tail = 0;
        int levels = 0;

        queue[tail++] = 0;

        while (head < tail) {
            int levelSize = tail - head;
            long sum = 0;

            for (int i = 0; i < levelSize; i++) {
                int node = queue[head++];
                sum += values[node];

                if (left[node] != -1) {
                    queue[tail++] = left[node];
                }

                if (right[node] != -1) {
                    queue[tail++] = right[node];
                }
            }

            sums[levels++] = sum;
        }

        long[] result = new long[levels];
        System.arraycopy(sums, 0, result, 0, levels);
        return result;
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

        long[] result = levelSums(values, left, right);
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
