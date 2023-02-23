package acw.java;
import java.util.Scanner;

public class _4729 {
    public static void main (String[] args) {
        Scanner sc = new Scanner (System.in);

        int k = sc.nextInt();
        while (k -- != 0) {
            long n = sc.nextLong(), e = sc.nextLong(), d = sc.nextLong();
            long m = n - e * d + 2;
            long dt = m * m - 4 * n;
            long r = (long)Math.sqrt((dt));
            if (dt < 0 || r * r != dt || (m - r) % 2 != 0) {
                System.out.println("NO");
            } else {
                System.out.println((m - r) / 2 + " " + (m + r) / 2);
            }

        }
    }
}