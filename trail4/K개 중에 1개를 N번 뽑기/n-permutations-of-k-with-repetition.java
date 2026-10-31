import java.util.*;

public class Main {
    static public StringBuilder sb = new StringBuilder();
    static int[] selected;
    static int n, k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        // Please write your code here.
        
        selected = new int[n];

        choose(0);
        
        System.out.println(sb);
    }
    static void choose(int depth) {
        if(depth == n) {
            for (int num : selected) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        for (int i = 1; i <= k; i++) {
            selected[depth] = i;
            choose(depth + 1);
        }
    }
}