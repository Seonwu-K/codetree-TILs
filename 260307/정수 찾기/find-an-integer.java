import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = sc.nextInt();
        }
        // Please write your code here.
        // 둘 다 해시셋에 담아서 값이 같은지 비교한다?
        // 한 쪽만 담아서 contains로 비교해도 된다.
        Set<Integer> ASet = new HashSet<>();
        
        for (int a : A) {
            ASet.add(a);
        }

        for (int b : B) {
            if(ASet.contains(b)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

        

    }
}