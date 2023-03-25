package luogu.com.java.lqb;


import java.util.Scanner;

public class P_8780 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong(), n = sc.nextLong();
        long res = 0;
        boolean st = true;
        if (n > (a * 5 + b * 2)) {
            res += 7 * (n / (a * 5 + b * 2));
        }
        n = n % (a * 5 + b * 2);
        while (n > 0) {
            if (st) {
                if (n > 5 * a) {
                    res += 5;
                    n -= 5 * a;
                }
                else {
                    res += (n % a == 0 ? n / a : n / a + 1);
                    n = 0;
                }
                st = false;
            }
            if (!st) {
                if (n > 2 * b) {
                    res += 2;
                    n -= 2 * b;
                } else {
                    res += (n % b == 0 ? n / b : n / b + 1);
                    n = 0;
                }
                st = true;
            }
        }
        System.out.print(res);
    }
}
