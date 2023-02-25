package acw.java.com.usaco;

import java.util.Scanner;

public class Ac_1345 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int cnt = 0;
        while (sc.hasNext()) {
            String n = sc.next();
            String res = "";
            for (char c : n.toCharArray()) {
                if (c == 'Q' || c == 'Z') {
                    res = "";
                    break;
                }
                res = res + get(c);
            }
            if (s.equals(res)) {
                System.out.println(n);
                cnt ++;
            }
        }
        if (cnt == 0) {
            System.out.println("NONE");
        }
    }
    public static int get(char c) {
        if (c <= 'O') return (c - 'A') / 3 + 2;
        if (c <= 'S') return 7;
        if (c <= 'V') return 8;
        return 9;
    }
}
