package acw.pat.l_2020;

import java.util.Scanner;

public class L1_2020_8 {
    public static int[][] arr = new int[4][4];

    public static void  main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] yell = new int[]{10000, 36, 720, 360, 80, 252, 108, 72, 54, 180, 72, 180, 119, 36, 306, 1080, 144, 1800, 3600 };
        int sum = 0, x1 =  0, y2 = 0;
        for (int i = 1; i <= 3; i ++) {
            for (int j = 1; j <= 3; j ++) {
                arr[i][j] = sc.nextInt();
                sum += arr[i][j];
                if (arr[i][j] == 0) {
                    x1 = i;
                    y2 = j;
                }
            }
        }
        if (x1 != 0 ) arr[x1][y2] = 45 - sum;
        int res = 0;
        for (int i = 0; i < 3; i ++) {
            int x = sc.nextInt(), y = sc.nextInt();
            System.out.println(arr[x][y]);
        }
        int tar = sc.nextInt();
        if (tar <= 3) {
            res += arr[tar][1] + arr[tar][2] + arr[tar][3];
        } else if (tar > 3 && tar <= 6) {
            res += arr[1][tar - 3] + arr[2][tar - 3] + arr[3][tar - 3];
        } else if (tar == 7) {
            res += arr[1][1] + arr[2][2] + arr[3][3];
        } else {
            res += arr[1][3] + arr[2][2] + arr[3][1];
        }
        System.out.println(yell[res - 6]);
    }
}
