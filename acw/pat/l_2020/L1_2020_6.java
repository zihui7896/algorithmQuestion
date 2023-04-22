package acw.pat.l_2020;

import java.util.Scanner;

public class L1_2020_6 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0, cnt = 0, res = 0;
        boolean st = false;
        while (true) {
            String s = sc.nextLine();
            if (s.equals(".")) break;
            sum ++;
            if (s.indexOf("chi1 huo3 guo1") >= 0) {
                cnt ++;
                if (!st) {
                    res  = sum;
                    st = true;
                }
            }
        }
        System.out.println(sum);
        if (cnt != 0) {
            System.out.print(res + " " + cnt);
        } else {
            System.out.print("-_-#");
        }
    }
}