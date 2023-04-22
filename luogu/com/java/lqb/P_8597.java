package luogu.com.java.lqb;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P_8597 {
    public static char[] a;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();
        int n = a.length;
        for (int i = 0; i < n - 1; i ++) {
            if (a[i] != b[i]) {
                trun(i);
                trun(i  + 1);
                res ++;
            }
        }
        System.out.print(res);
    }
    public static  void trun(int i) {
        if (a[i] == '*') a[i] = 'o';
        else a[i] = '*';
    }
}
