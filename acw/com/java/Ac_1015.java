package acw.com.java;

import java.util.Scanner;

public class Ac_1015 {
    public static final int N = 110;
    public static int[][] arr = new int[N][N];
    public static int[][] dp = new int[N][N];

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t -- != 0) {
            int n = sc.nextInt(), m = sc.nextInt();

            for (int i = 1; i <= n; i ++) {
                for (int j = 1; j <= m; j ++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            dp[1][1] = arr[1][1];
            for (int i = 1; i <= n; i ++) {
                for (int j = 1; j <= m; j ++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
                }
            }
            System.out.println(dp[n][m]);
        }
    }
}