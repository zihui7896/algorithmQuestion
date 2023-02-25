package acw.java.com.usaco;

import java.util.Scanner;

public class Ac_1346 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int b = sc.nextInt();
        for (int i = 1; i <= 300; i ++) {
            if (check(base(i * i, b))) {
                System.out.println(base(i, b) + " " + base(i * i, b));
            }
        }
    }
    public static boolean check(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i ++, j --) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
    public static String base(int n, int k) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            res.append(get(n % k));
            n /= k;
        }
        return res.reverse().toString();
    }
    public static char get(int m) {
        if (m <= 9) return (char)(m + '0');
        return (char)(m - 10 + 'A');
    }
}
