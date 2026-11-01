
import java.util.*;

public class Main {
    static int n;
    static int[][] grid;

    // 폭탄이 놓인 위치
    static List<int[]> bombs = new ArrayList<>();

    // 각 폭탄에 선택한 종류 (0, 1, 2)
    static int[] types;

    static int maxCount = 0;

    // 0번: 세로 / 1번: 십자 / 2번: X자
    static int[][] dx = {
        {0, -2, -1, 1, 2},
        {0, -1, 1, 0, 0},
        {0, -1, -1, 1, 1}
    };

    static int[][] dy = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, -1, 1},
        {0, -1, 1, -1, 1}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();

                if (grid[i][j] == 1) {
                    bombs.add(new int[]{i, j});
                }
            }
        }

        types = new int[bombs.size()];

        dfs(0);

        System.out.println(maxCount);
    }

    // 각 폭탄의 종류를 선택
    static void dfs(int depth) {
        // 모든 폭탄 종류를 결정했다면
        if (depth == bombs.size()) {
            int count = calculate();
            maxCount = Math.max(maxCount, count);
            return;
        }

        // 현재 폭탄에 3가지 모양 중 하나 선택
        for (int type = 0; type < 3; type++) {
            types[depth] = type;
            dfs(depth + 1);
        }
    }

    // 현재 조합에서 초토화된 칸 계산
    static int calculate() {
        boolean[][] destroyed = new boolean[n][n];

        // 모든 폭탄을 순회
        for (int i = 0; i < bombs.size(); i++) {
            int[] bomb = bombs.get(i);

            int x = bomb[0];
            int y = bomb[1];
            int type = types[i];

            // 해당 폭탄의 영향 범위 5칸 확인
            for (int d = 0; d < 5; d++) {
                int nx = x + dx[type][d];
                int ny = y + dy[type][d];

                // 격자 범위를 벗어나면 무시
                if (nx < 0 || nx >= n ||
                    ny < 0 || ny >= n) {
                    continue;
                }

                destroyed[nx][ny] = true;
            }
        }

        // 초토화된 칸의 개수 계산
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (destroyed[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
