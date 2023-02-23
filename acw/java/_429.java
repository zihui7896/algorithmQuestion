package acw.java;// 429. 奖学金
import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class _429 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i ++) {
            arr[i][0] = i + 1;
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
            arr[i][3] = sc.nextInt();
        }
        
        Arrays.sort(arr, 0, n, new Comparator<int[]>(){
            @Override
            public int compare(int[] a , int[] b){
                int s1 = a[1] + a[2] + a[3];
                int s2 = b[1] + b[2] + b[3];
                if(s1 != s2) return s2 - s1;           
                if(a[1] != b[1]) return b[1] - a[1];   
                return a[0] - b[0];                   
            }
        });

        for (int i = 0; i < 5; i ++) {
            System.out.println(arr[i][0] + " " + (arr[i][1] + arr[i][2] + arr[i][3]));
        }
    }
}
