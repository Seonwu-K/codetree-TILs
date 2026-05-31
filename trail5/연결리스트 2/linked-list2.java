import java.io.*;
import java.util.*;

public class Main {
    static int[] prev;
    static int[] next;

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = fs.nextInt();
        int Q = fs.nextInt();

        prev = new int[N + 1];
        next = new int[N + 1];

        for (int q = 0; q < Q; q++) {
            int type = fs.nextInt();

            if (type == 1) {
                int i = fs.nextInt();
                remove(i);
            } else if (type == 2) {
                int i = fs.nextInt();
                int j = fs.nextInt();
                insertBefore(i, j);
            } else if (type == 3) {
                int i = fs.nextInt();
                int j = fs.nextInt();
                insertAfter(i, j);
            } else {
                int i = fs.nextInt();
                sb.append(prev[i]).append(' ').append(next[i]).append('\n');
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(next[i]);
            if (i < N) sb.append(' ');
        }

        System.out.print(sb);
    }

    static void remove(int i) {
        int p = prev[i];
        int n = next[i];

        if (p != 0) next[p] = n;
        if (n != 0) prev[n] = p;

        prev[i] = 0;
        next[i] = 0;
    }

    static void insertBefore(int i, int j) {
        int p = prev[i];

        prev[j] = p;
        next[j] = i;

        prev[i] = j;
        if (p != 0) next[p] = j;
    }

    static void insertAfter(int i, int j) {
        int n = next[i];

        next[j] = n;
        prev[j] = i;

        next[i] = j;
        if (n != 0) prev[n] = j;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int num = 0;
            while (c > ' ') {
                num = num * 10 + (c - '0');
                c = read();
            }
            return num;
        }
    }
}