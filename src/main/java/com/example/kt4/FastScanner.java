package com.example.kt4;

import java.io.IOException;
import java.io.InputStream;

final class FastScanner {
    private final InputStream input;
    private final byte[] buffer = new byte[1 << 16];
    private int pointer;
    private int length;

    FastScanner(InputStream input) {
        this.input = input;
    }

    int nextInt() throws IOException {
        return (int) nextLong();
    }

    long nextLong() throws IOException {
        int c = skipWhitespace();
        boolean negative = false;

        if (c == '-') {
            negative = true;
            c = read();
        }

        long value = 0;

        while (c > ' ') {
            value = value * 10 + c - '0';
            c = read();
        }

        return negative ? -value : value;
    }

    private int skipWhitespace() throws IOException {
        int c;

        do {
            c = read();
        } while (c <= ' ' && c != -1);

        return c;
    }

    private int read() throws IOException {
        if (pointer >= length) {
            length = input.read(buffer);
            pointer = 0;

            if (length == -1) {
                return -1;
            }
        }

        return buffer[pointer++];
    }
}
