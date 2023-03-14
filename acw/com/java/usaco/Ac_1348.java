package acw.com.java.usaco;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Ac_1348 {
    public static final int N = 5010;
    public static Pair[] arr = new Pair[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        for (int i = 0; i < m; i ++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new Pair(x, y);
        }
        Arrays.sort(arr, 0, m, new Comparator<Pair>(){
            @Override
            public int compare(Pair a , Pair b){
                return a.x - b.x;
            }
        });

        int res = 0;
        for (int i = 0; i < m && n > 0; i ++) {
            int cnt = Math.min(n, arr[i].y);
            n -= arr[i].y;
            res += cnt * arr[i].x;
        }
        System.out.print(res);
    }
}
class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
//import java.util.Scanner;
//        import java.util.Arrays;
//
//public class Main {
//    public static final int N = 5010;
//    public static int[][] arr = new int[N][2];
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(), m = sc.nextInt();
//        for (int i = 0; i < m; i ++) {
//            arr[i][0] = sc.nextInt();
//            arr[i][1] = sc.nextInt();
//        }
//        Arrays.sort(arr, 0, m, (a, b) -> a[0] - b[0]);
//
//        int res = 0;
//        for (int i = 0; i < m && n > 0; i ++) {
//            int cnt = 0;
//            if (n > arr[i][1]) {
//                cnt += arr[i][1] * arr[i][0];
//            } else {
//                cnt += n * arr[i][0];
//            }
//            n -= arr[i][1];
//            res += cnt;
//        }
//        System.out.print(res);
//    }
//}
