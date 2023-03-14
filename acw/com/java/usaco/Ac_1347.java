package acw.com.java.usaco;

import java.util.Scanner;

public class Ac_1347 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), s = sc.nextInt();

        for (int i = s + 1; n > 0; i ++) {
            int cnt = 0;
            for (int j = 2; j <= 10 && cnt < 2; j ++) {
                if (check(base(i, j))) {
                    cnt ++;
                }
            }
            if (cnt == 2) {
                System.out.println(i);
                n --;
            }
        }
    }
    public static String base (int m, int k) {
        StringBuilder res = new StringBuilder();
        while (m != 0) {
            res.append(m % k);
            m /= k;
        }
        return res.reverse().toString();
    }
    public static boolean check (String s) {
        for (int i = 0, j = s.length() - 1; i < j; i ++, j --) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
