package acw.com.java.usaco;

import java.util.Scanner;

public class Ac_1339 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        String n = sc.next();
        int a = 1, b = 1;
        for (int i = 0; i < s.length(); i ++) {
            a *= (int)(s.charAt(i) - 'A' + 1);
        }
        for (int i = 0; i < n.length(); i ++) {
            b *= (int)(n.charAt(i) - 'A' + 1);
        }
        if ((a % 47) == (b % 47)) {
            System.out.print("GO");
        } else {
            System.out.print("STAY");
        }
    }
}
