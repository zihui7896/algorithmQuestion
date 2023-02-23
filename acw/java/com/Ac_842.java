package acw.java.com;

import java.util.Scanner;

public class Ac_842 {
    public static final int N = 10;
    public static boolean[] st = new boolean[N];
    public static int[] path = new int[N];

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        dfs(0, n);
    }
    public static void dfs(int u, int n) {
        if (u == n) {
            for (int i = 0; i < n; i ++) System.out.print(path[i] + " ");
            System.out.println();
        }
        for (int i = 1; i <= n; i ++) {
            if (!st[i]) {
                path[u] = i;
                st[i] = true;
                dfs(u + 1, n);
                st[i] = false;
            }
        }
    }
}
