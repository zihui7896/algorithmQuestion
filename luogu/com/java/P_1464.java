package luogu.com.java;

import java.util.Scanner;

public class P_1464 {
    public static final int N = 25;
    public static int[][][] dp = new int[N][N][N];

    public static int dfs(long a, long b, long c) {
        if (a <= 0 || b <= 0 || c <= 0) return 1;
        if (a > 20 || b > 20 || c > 20) {
            return dfs(20, 20, 20);
        }
        if (dp[(int)a][(int)b][(int)c] != 0) return dp[(int)a][(int)b][(int)c];
        else if (a < b && b < c)  {
            dp[(int)a][(int)b][(int)c] = dfs(a, b, c - 1) + dfs(a, b - 1, c - 1) - dfs(a, b - 1, c);
        } else {
            dp[(int)a][(int)b][(int)c] = dfs(a - 1, b, c) + dfs(a - 1, b - 1, c) + dfs(a - 1, b, c - 1) - dfs(a - 1, b - 1, c - 1);
        }
        return dp[(int)a][(int)b][(int)c];
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                long a = sc.nextLong(), b =  sc.nextLong(), c = sc.nextLong();
                if (a == -1 && b == -1 && c == -1) break;

                System.out.print("w(" + a + ", " + b + ", " + c + ") = ");
                System.out.println(dfs(a, b, c));
            }

    }

}
