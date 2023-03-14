package acw.com.java;

import java.util.Scanner;

public class Ac_790{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double l = -10001, r = 10010;
        while (r - l > 1e-7) {
            double mid = (l + r) / 2;
            if (mid * mid * mid > a) {
                r = mid;
            } else {
                l = mid;
            }
        }
        System.out.printf("%.6f", l);
    }
}