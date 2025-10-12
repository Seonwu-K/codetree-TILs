import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String command = sc.next();
            int x = sc.nextInt();
            // Please write your code here.
            if(command.equals("find")){
                System.out.println(set.contains(x));
            } else if(command.equals("add")){
                set.add(x);
            } else{
                set.remove(x);
            }
        }
    }
}