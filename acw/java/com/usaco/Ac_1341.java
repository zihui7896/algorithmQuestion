package acw.java.com.usaco;

import java.util.Scanner;

public class Ac_1341 {
    public static int[] month = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static int[] res = new int[7];
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int year = 1900, cnt = 0;
        while (n -- != 0) {

            for (int i = 1; i <= 12; i ++) {
                res[(cnt + 12) % 7] ++;

                if (i == 2) {
                    if (year % 100 != 0 && year % 4 == 0 || year % 400 == 0) {
                        cnt ++;
                    }
                }
                cnt += month[i];
            }
            year ++;
        }
        for (int i = 5, j = 0; j < 7; i ++, j ++) {
            System.out.print(res[i % 7] + " ");
        }
    }
}