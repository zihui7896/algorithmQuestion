package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8753 {
    public static int n;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int res = 0;
        for (int i = 1; i < n; i ++) {
            if (check(i)) res ++;
        }
        System.out.print(res);
    }
    public static boolean check(int x) {
        long a = (long)x * x;
        return (a % n) < (n / 2.0);
    }
}
