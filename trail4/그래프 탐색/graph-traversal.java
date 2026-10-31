import java.util.Scanner;
public class Main {
    static boolean[] visited;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 노드 개수
        int m = sc.nextInt(); // 간선 개수
        int[] u = new int[m]; // 간선 양 끝 정점
        int[] v = new int[m];
        for (int i = 0; i < m; i++) {
            u[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        // Please write your code here.
        visited = new boolean[n+1];

        dfs(1,u,v);

        System.out.println(count);
    }

    static void dfs(int current, int[] u, int[] v) {
        visited[current] = true; // idx가 곧 node 번호
        
        for (int i = 0; i < u.length; i++) {
            int next = -1;

            if(u[i] == current) {
                next = v[i];
            } else if(v[i] == current) {
                next = u[i];
            }

            if (next != -1 && !visited[next]){
                count++;
                dfs(next, u, v);
            }
        }
    }
}