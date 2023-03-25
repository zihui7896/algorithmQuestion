package luogu.com.java;

import java.util.Scanner;

public class P_1044 {
    public static final int N = 20;
    public static int[][] dp = new int[N][N];

    public static int dfs(int n, int u) {
        if (dp[n][u] != 0) return dp[n][u];
        if (n == 0) return 1;

        if (u > 0) dp[n][u] += dfs(n, u - 1);
        dp[n][u] += dfs(n - 1, u + 1);
        return dp[n][u];

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(dfs(n, 0));

    }
}
