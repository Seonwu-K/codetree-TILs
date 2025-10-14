import java.util.Scanner;
import java.util.HashSet;
// import java.util.StringBuilder;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = sc.nextInt();
        }
        // Please write your code here.
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int a1 : arr1)
            set1.add(a1);        
        // 수열 2의 원소가 수열 1에 존재하면 1, 없으면 0
        String answer = "";
        for (int a2 : arr2) {
            if(set1.contains(a2)){
                answer = new StringBuilder(answer).append("1 ").toString();
            } else {
                answer = new StringBuilder(answer).append("0 ").toString();
            }
        }

        System.out.println(answer);
    }
}