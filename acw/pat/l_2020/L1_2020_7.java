package acw.pat.l_2020;

import java.util.Scanner;

public class L1_2020_7 {
    public static final int N = 110;
    public static String[] arr = new String[N];

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(), n = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            arr[i] = sc.next();
        }
        int st = (int)Math.pow(2, t);
        for (int i = 0; i < n; i ++) {
            int res = 1, temp = st;
            for (int j = 0; j < arr[i].length(); j ++) {
                char c = arr[i].charAt(j);
                if (c == 'n') res += temp / 2;
                temp /= 2;
            }
            System.out.println(res);
        }
    }
}
