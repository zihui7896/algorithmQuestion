package luogu.com.java.lqb;

import java.util.Scanner;

public class P_8711 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        while (n > 0) {
            System.out.print(n + " ");
            n /= 2;
        }
    }
}
