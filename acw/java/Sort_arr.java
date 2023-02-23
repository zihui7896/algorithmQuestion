package acw.java;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Sort_arr {
        public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int k = sc.nextInt();
        
        arr[] a = new arr[k];
        
        for (int i = 0; i < k; i ++) {
            String s = sc.next();
            int n = sc.nextInt();
            int m = sc.nextInt();
            int day = sc.nextInt();
            int num = i;
            a[i] = new arr(s, n, m, day, num);
        }
        Arrays.sort(a, 0, k, new Comparator<arr>(){
            @Override
            public int compare(arr s1, arr s2)
            {
                if (s1.n != s2.n) return s1.n - s2.n;
                if (s1.m != s2.m) return s1.m - s2.m;
                if (s1.day != s2.day) return s1.day - s2.day;
                return s2.num - s1.num;
            }
        });
        
        for (int i = 0; i < k; i ++) {
            System.out.println(a[i].s);
        }
    }
}
class arr {
    String s;
    int n;
    int m;
    int day;
    int num;
    public arr(String s, int n, int m, int day, int num) {
        this.s = s;
        this.n = n;
        this.m = m;
        this.day = day;
        this.num = num;
    }
}
