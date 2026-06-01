import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int value, priority, size;
        Node left, right, parent;

        Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
            this.size = 1;
        }
    }

    static class FastScanner {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

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

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    static int seed = 123456789;

    static int rand() {
        seed ^= seed << 13;
        seed ^= seed >>> 17;
        seed ^= seed << 5;
        return seed;
    }

    static int size(Node node) {
        return node == null ? 0 : node.size;
    }

    static void update(Node node) {
        if (node == null) return;
        node.size = 1 + size(node.left) + size(node.right);
        if (node.left != null) node.left.parent = node;
        if (node.right != null) node.right.parent = node;
    }

    static Node merge(Node left, Node right) {
        if (left == null) {
            if (right != null) right.parent = null;
            return right;
        }
        if (right == null) {
            left.parent = null;
            return left;
        }

        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            update(left);
            left.parent = null;
            return left;
        } else {
            right.left = merge(left, right.left);
            update(right);
            right.parent = null;
            return right;
        }
    }

    static Node[] split(Node root, int k) {
        if (root == null) return new Node[]{null, null};

        if (size(root.left) >= k) {
            Node[] result = split(root.left, k);
            root.left = result[1];
            update(root);

            if (result[0] != null) result[0].parent = null;
            root.parent = null;
            return new Node[]{result[0], root};
        } else {
            Node[] result = split(root.right, k - size(root.left) - 1);
            root.right = result[0];
            update(root);

            if (result[1] != null) result[1].parent = null;
            root.parent = null;
            return new Node[]{root, result[1]};
        }
    }

    static int indexOf(Node node) {
        int index = size(node.left) + 1;

        while (node.parent != null) {
            if (node == node.parent.right) {
                index += size(node.parent.left) + 1;
            }
            node = node.parent;
        }

        return index;
    }

    static void inorder(Node root, StringBuilder sb) {
        if (root == null) return;
        inorder(root.left, sb);
        sb.append(root.value).append(' ');
        inorder(root.right, sb);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int Q = fs.nextInt();

        Node[] nodes = new Node[N + 1];
        Node root = null;

        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i, rand());
            root = merge(root, nodes[i]);
        }

        for (int i = 0; i < Q; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int c = fs.nextInt();
            int d = fs.nextInt();

            int l1 = indexOf(nodes[a]);
            int r1 = indexOf(nodes[b]);
            int l2 = indexOf(nodes[c]);
            int r2 = indexOf(nodes[d]);

            if (l1 > l2) {
                int tempL = l1, tempR = r1;
                l1 = l2;
                r1 = r2;
                l2 = tempL;
                r2 = tempR;
            }

            Node[] s1 = split(root, l1 - 1);
            Node left = s1[0];

            Node[] s2 = split(s1[1], r1 - l1 + 1);
            Node first = s2[0];

            Node[] s3 = split(s2[1], l2 - r1 - 1);
            Node middle = s3[0];

            Node[] s4 = split(s3[1], r2 - l2 + 1);
            Node second = s4[0];
            Node right = s4[1];

            root = merge(merge(merge(merge(left, second), middle), first), right);
        }

        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        System.out.println(sb.toString().trim());
    }
}