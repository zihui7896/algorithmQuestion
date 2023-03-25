package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8752 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int res = 0;
        for (int i = 0; i < 5; i ++) {
            String s = sc.next();
            if (s.charAt(0) == s.charAt(2) && s.charAt(3) - s.charAt(1) == 1) res ++;
        }
        System.out.print(res);
    }
}
