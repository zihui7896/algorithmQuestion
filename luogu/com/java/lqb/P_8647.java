package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8647 {
    public static final int N = 100010;
    public static int[] h = new int[N];
    public static int[] w = new int[N];
    public static int n, k;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            h[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        int l = 1, r = 100010;
        while (l < r) {
            int mid = l + r + 1>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.print(l);
    }
    public static boolean check(int x) {
        int cnt = 0;
        for (int i = 0; i < n; i ++) {
            cnt += (h[i] / x) * (w[i] / x);
        }
        return cnt >= k;
    }
}
