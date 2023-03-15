package luogu.com.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P_2240 {
    public static final int N = 110;
    public static double[][] arr = new double[N][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), t = sc.nextInt();
        for (int i = 0; i < n; i ++) {
            arr[i][0] = sc.nextDouble();
            arr[i][1] = sc.nextDouble();
            arr[i][2] = arr[i][1] / arr[i][0];
        }
        Arrays.sort(arr, 0, n, new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return o1[2] < o2[2] ? 1 : -1;
            }
        });
        for (int i = 0; i < n; i ++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        double res = 0;
        for (int i = 0; i < n; i ++) {
            if (t >= arr[i][0]) {
                res += arr[i][1];
                t -= arr[i][0];
            } else {
                res += t * arr[i][2];
                break;
            }
        }
        System.out.printf("%.2f",res);
    }
}
