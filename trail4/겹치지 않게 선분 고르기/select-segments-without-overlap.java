
import java.util.*;

public class Main {
    static int n;
    static int[][] segments;
    static boolean[] selected;
    static int maxCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        segments = new int[n][2];
        selected = new boolean[n];

        for (int i = 0; i < n; i++) {
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }

        dfs(0, 0);

        System.out.println(maxCount);
    }

    static void dfs(int idx, int count) {
        // 모든 선분의 선택 여부 결정 완료
        if (idx == n) {
            maxCount = Math.max(maxCount, count);
            return;
        }

        // 1. 현재 선분을 선택하는 경우
        if (canSelect(idx)) {
            selected[idx] = true;

            dfs(idx + 1, count + 1);

            // 백트래킹: 선택 상태 복구
            selected[idx] = false;
        }

        // 2. 현재 선분을 선택하지 않는 경우
        dfs(idx + 1, count);
    }

    static boolean canSelect(int idx) {
        int start = segments[idx][0];
        int end = segments[idx][1];

        for (int i = 0; i < idx; i++) {
            if (!selected[i]) continue;

            int otherStart = segments[i][0];
            int otherEnd = segments[i][1];

            // 서로 겹치는지 확인
            if (start <= otherEnd && otherStart <= end) {
                return false;
            }
        }

        return true;
    }
}
