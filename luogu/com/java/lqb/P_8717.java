package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8717 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxv = 0, minv = 110;
        double res = 0;
        int n = sc.nextInt();
        int t = n;
        while (t -- != 0) {
            int x = sc.nextInt();
            if (x > maxv) maxv = x;
            if (x < minv) minv = x;
            res += x;
        }
        System.out.println(maxv);
        System.out.println(minv);
        System.out.printf("%.2f", (res / n + 0.005));
    }
}
