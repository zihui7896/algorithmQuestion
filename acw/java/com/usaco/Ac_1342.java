package acw.java.com.usaco;

import java.util.Scanner;

public class Ac_1342 {
    public static int get(char s) {
        if (s == 'b') return 1;
        return 2;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();
        char[] arr = new char[2 * s.length() + 10];
        for (int i = 0; i < n; i++) arr[n + i] = arr[i] = s.charAt(i);

        int res = 0;
        for (int i = 0; i < n; i++) {
            int l = i, r = n + i - 1;
            int cnt = 0, left = 0, right = 0;
            while (l <= r && (arr[l] == 'w' || (left | get(arr[l])) != 3)) {
                if (arr[l] != 'w') left |= get(arr[l]);
                l ++;
                cnt ++;
            }
            while (l <= r && (arr[r] == 'w' || (right | get(arr[r])) != 3)) {
                if (arr[r] != 'w') right |= get(arr[l]);
                r --;
                cnt ++;
            }
            res = Math.max(res, cnt);
        }
        System.out.print(res);
    }
}

