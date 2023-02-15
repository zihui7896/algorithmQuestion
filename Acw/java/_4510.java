package Acw.java;


import java.util.Scanner;

public class _4510 {
    public static final int N = 1010, M = 55;
    public static pair[] arr = new pair[N];
    public static int[][] b = new int[M][M];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), l = sc.nextInt(), s = sc.nextInt();

        for (int i = 0; i < n; i ++) {
            int x = sc.nextInt(), y = sc.nextInt();
            arr[i] = new pair(x, y);
        }
        int sumone = 0;
        for (int i = s; i >= 0; i --) {
            for (int j = 0; j <= s; j ++) {
                b[i][j] = sc.nextInt();
                sumone += b[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < n; i ++) {
            int sx = arr[i].x, sy = arr[i].y;
            if (sx + s > l || sy + s > l) continue;
            int cnt = 0;
            for (int j = 0; j < n; j ++) {
                int x = arr[j].x, y = arr[j].y;
                if (x >= sx && x - sx <= s && y >= sy && y - sy <= s) {
                    if (b[x - sx][y - sy] != 1) cnt = Integer.MIN_VALUE;
                    else cnt ++;
                }
            }
            if (cnt == sumone) res ++;
        }

        System.out.println(res);
    }
}
class pair {
    int x;
    int y;
    public pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

