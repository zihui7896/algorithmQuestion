package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8799 {
    public static final int N = 200010;
    public static int[] arr = new int[N];
    public static int[] st = new int[N], st1 = new int[N];

    public static  void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), t = sc.nextInt();
        int max = 0;
        for (int i = 1; i <= n; i ++) {
            arr[i] = sc.nextInt();
            st[arr[i]] ++;
            max = Math.max(max, arr[i]);
        }
        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j * arr[i] <= max; j ++) {
                if (st[j * arr[i]] > 0)
                    st1[j] ++;
            }
        }

        while (t -- != 0) {
            int x = sc.nextInt();
            if (st1[x] > 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
