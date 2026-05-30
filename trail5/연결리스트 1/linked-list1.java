import java.util.Scanner;

public class Main {
    static class Node {
        String value;
        Node prev;
        Node next;

        Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sInit = sc.nextLine();
        int n = sc.nextInt();

        Node cur = new Node(sInit);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int command = sc.nextInt();

            if (command == 1) {
                String value = sc.next();
                Node newNode = new Node(value);

                newNode.prev = cur.prev;
                newNode.next = cur;

                if (cur.prev != null) {
                    cur.prev.next = newNode;
                }

                cur.prev = newNode;

            } else if (command == 2) {
                String value = sc.next();
                Node newNode = new Node(value);

                newNode.next = cur.next;
                newNode.prev = cur;

                if (cur.next != null) {
                    cur.next.prev = newNode;
                }

                cur.next = newNode;

            } else if (command == 3) {
                if (cur.prev != null) {
                    cur = cur.prev;
                }

            } else if (command == 4) {
                if (cur.next != null) {
                    cur = cur.next;
                }
            }

            sb.append(cur.prev == null ? "(Null)" : cur.prev.value)
              .append(" ")
              .append(cur.value)
              .append(" ")
              .append(cur.next == null ? "(Null)" : cur.next.value)
              .append("\n");
        }

        System.out.print(sb);
    }
}