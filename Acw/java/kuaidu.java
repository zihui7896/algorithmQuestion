package Acw.java;

import java.io.*;

public class kuaidu{
    public static void main (String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String[] sg = bf.readLine().split(" ");
        int n = Integer.parseInt(bf.readLine());
        pw.print("JJ" + sg.toString() + "" + n);
        pw.flush();
    }
}
//import java.util.Scanner;
//import java.io.*;
//public class Main {
//    public static final int N = 1000010;
//    public static int[] arr = new int[N], q = new int[N];
//    public static int hh = 0, tt = -1;
//
//    public static void main (String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter wt = new PrintWriter(new OutputStreamWriter(System.out));
//        String[] st = bf.readLine().split(" ");
//        int n = Integer.parseInt(st[0]);
//        int k = Integer.parseInt(st[1]);
//        String[] str = bf.readLine().split(" ");
//        for(int i = 0 ; i < n ; i ++ ) arr[i] = Integer.parseInt(str[i]);
//
//        for (int i = 0; i < n; i ++) {
//            if (hh <= tt && i - k + 1 > q[hh]) hh ++;
//            while (hh <= tt && arr[q[tt]] >= arr[i]) tt --;
//            q[ ++ tt] = i;
//            if (i >= k - 1) wt.print(arr[q[hh]] + " " );
//        }
//        wt.println();
//        hh = 0;
//        tt = -1;
//        for (int i = 0; i < n; i ++) {
//            if (hh <= tt && i - k + 1 > q[hh]) hh ++;
//            while (hh <= tt && arr[q[tt]] <= arr[i]) tt --;
//            q[ ++ tt] = i;
//            if (i >= k - 1) wt.print(arr[q[hh]] + " " );
//        }
//        wt.flush();//记得刷新流
//    }
//}
//import java.util.*;
//import java.io.*;
//
//public class Main
//{
//    static final int N = (int)1e6 + 10;
//    static int[] a = new int[N], q = new int[N];
//    static int hh = 0, tt = -1;
//    static Scanner in = new Scanner(System.in);
//    static PrintWriter out = new PrintWriter(System.out);
//
//    public static void main(String[] args)
//    {
//        int n = in.nextInt(), m = in.nextInt();
//        for (int i = 0; i < n; i++) a[i] = in.nextInt();
//        for (int i = 0; i < n; i++)
//        {
//            if (hh <= tt && i - q[hh] + 1 > m) hh++;
//            while (hh <= tt && a[q[tt]] >= a[i]) tt--;
//            q[++tt] = i;
//            if (i >= m - 1)
//                out.print(a[q[hh]] + " ");
//        }
//        out.print("\n");
//
//        hh = 0;
//        tt = -1;
//        for (int i = 0; i < n; i++)
//        {
//            if (hh <= tt && i - q[hh] + 1 > m) hh++;
//            while (hh <= tt && a[q[tt]] <= a[i]) tt--;
//            q[++tt] = i;
//            if (i >= m - 1)
//                out.print(a[q[hh]] + " ");
//        }
//        out.flush();
//    }
//
//}
