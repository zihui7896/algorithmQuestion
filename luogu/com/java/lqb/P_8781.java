package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8781 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 1; i <= n; i ++) {
            System.out.println(Math.max(n - i, i - 1) * 2);
        }
    }
}
