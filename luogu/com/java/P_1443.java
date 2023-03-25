package luogu.com.java;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class P_1443 {
    public static final int N = 410;
    public static int[][] arr = new int[N][N];
    public static int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] dy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    public static int n, m, x, y;
    public static Queue<pair> qu = new ArrayDeque<>();

    public static void bfs() {

        while (qu.size() != 0) {
            pair pairs = qu.poll();
            for (int i = 0; i < 8; i ++) {
                int a = pairs.x + dx[i], b = pairs.y + dy[i];
                if (a <= 0 || a > n || b <= 0 || b > m) continue;
                if (arr[a][b] != 0) continue;
                if (a == x && b == y) continue;
                arr[a][b] = pairs.t + 1;
                qu.add(new pair(a, b, arr[a][b]));
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        y = sc.nextInt();
        qu.add(new pair(x, y, 0));
        bfs();

        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= m; j ++) {
                if (arr[i][j] != 0) {
                    System.out.print(arr[i][j] + " ");
                } else if (x == i && y == j) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(-1 + " ");
                }
            }
            System.out.println();
        }
    }
}
class pair{
    int x;
    int y;
    int t;
    public pair(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}
