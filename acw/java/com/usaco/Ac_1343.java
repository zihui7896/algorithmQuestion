package acw.java.com.usaco;

import java.util.Arrays;
import java.util.Scanner;

public class Ac_1343 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i ++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, 0, n, (a, b) -> a[0] - b[0]);

        int res1 = 0, res2 = 0;
        int l = arr[0][0], r = arr[0][1];
        for (int i = 1; i < n; i ++) {
            if (arr[i][0] <= r) {
                r = Math.max(r, arr[i][1]);
            } else {
                res1 =Math.max(res1, r - l);
                res2 = Math.max(res2, arr[i][0] - r);
                l = arr[i][0];
                r = arr[i][1];
            }
        }
        res1 = Math.max(res1, r - l);
        System.out.print(res1 + " " + res2);
    }
}
