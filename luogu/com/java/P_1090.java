package luogu.com.java;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class P_1090 {
    public static final int N = 10010;

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Queue<Integer> qu = new PriorityQueue<>();
        int res = 0;
        for (int i = 0; i < n; i ++) {
            qu.add(sc.nextInt());
        }
        while (qu.size() > 1) {
            int a = qu.poll();
            int b = qu.poll();
            res += a + b;
            qu.add(a + b);
        }
        System.out.print(res);
    }
}
