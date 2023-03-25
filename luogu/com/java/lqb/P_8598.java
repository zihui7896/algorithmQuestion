package luogu.com.java.lqb;

import java.util.Arrays;
import java.util.Scanner;

public class P_8598 {
    public static final int N = 10010;
    public static int[] arr = new int[N];
    public static int cnt;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String tec = sc.nextLine();
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            System.out.println(Arrays.toString(s));
            for (int j = 0; j < s.length; j ++) {
               arr[cnt ++] = Integer.parseInt(s[j]);
            }
        }
        Arrays.sort(arr, 0, cnt);
        int res = -1, res1 = -1;
        for (int i = 1; i < cnt; i ++) {
            if (arr[i] >= (arr[i - 1] + 2)) res1 = arr[i - 1] + 1;
            if (arr[i] == arr[i - 1]) res = arr[i];
        }
        System.out.print(res1 + " " + res);
    }
}
