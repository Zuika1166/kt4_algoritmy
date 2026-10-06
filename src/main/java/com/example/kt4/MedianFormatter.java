package com.example.kt4;

final class MedianFormatter {
    private MedianFormatter() {
    }

    static String formatTwice(long twiceMedian) {
        if ((twiceMedian & 1L) == 0L) {
            return twiceMedian / 2 + ".0";
        }

        long absolute = Math.abs(twiceMedian);
        String prefix = twiceMedian < 0 ? "-" : "";
        return prefix + absolute / 2 + ".5";
    }

    static String join(long[] twiceMedians) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < twiceMedians.length; i++) {
            if (i > 0) {
                output.append(' ');
            }

            output.append(formatTwice(twiceMedians[i]));
        }

        return output.toString();
    }
}
