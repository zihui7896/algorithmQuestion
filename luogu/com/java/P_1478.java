package luogu.com.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P_1478 {
    public static final int N = 50010;
    public static int[][] arr = new int[N][2];

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), s = sc.nextInt();
        int a = sc.nextInt(), b = sc.nextInt();
        int h = a + b;
        for (int i = 0; i < n; i ++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, 0, n, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int cnt = 0;
        for (int i = 0; i < n; i ++) {
            if (h >= arr[i][0]) {
                if (s >= arr[i][1]) {
                    cnt ++;
                    s -= arr[i][1];
                } else break;
            }
        }
        System.out.print(cnt);
    }
}
