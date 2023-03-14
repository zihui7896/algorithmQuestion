package acw.com.java.usaco;

import java.util.Scanner;
import java.util.Arrays;

public class Ac_1344 {
    public static final int N = 15;
    public static char[][] a = new char[N][N];
    public static char[][] b = new char[N][N];
    public static int n;
    public static void mirror(char[][] arr) {
        for (int i = 0; i < n; i ++) {
            for (int j = 0, k = n - 1; j < k; j ++, k --) {
                char temp = arr[i][j];
                arr[i][j] = arr[i][k];
                arr[i][k] = temp;
            }
        }
    }
    public static void rotate(char[][] arr) {
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < i; j ++){
                char temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        mirror(arr);
    }
    public static int check(char[][] a, char[][] b) {

        char[][] c = new char[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                c[i][j] = a[i][j];
            }
        }
        /*
            注意数组的clone 不能 直接 char[][] c = a;
            这样克隆的是地址 c 变 a 也变
            此时是把 a 的地址给了 c，俩者指向同一块内存，
            所以，当其中一个数组的某个元素改变时，另一个数组的相对应元素也会发生改变。
              对于基本类型的数组，Arrays.copyOf与 Arrays.arraycopy俩种拷贝方式，
              他们与clone拷贝方式一样，必须使用for循环进行拷贝，不然拷贝的只是地址，并没有拷贝到值。
        */
        for (int i = 1; i <= 3; i ++) {
            rotate(c);
            if (checkEquals(c, b)) return i;
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                c[i][j] = a[i][j];
            }
        }
        mirror(c);

        if (checkEquals(c, b)) return 4;
        for (int i = 1; i <= 3; i ++) {
            rotate(c);
            if (checkEquals(c, b)) return 5;
        }
        if (checkEquals(a, b)) return 6;
        return 7;
    }
    public static boolean checkEquals(char[][] arr, char[][] brr) {
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (arr[i][j] != brr[i][j]) return false;
            }
        }
        return true;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        for (int i = 0; i < n; i ++ ) {
            String s = sc.next();
            for (int j = 0; j < s.length(); j ++) {
                a[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < n; i ++ ) {
            String s = sc.next();
            for (int j = 0; j < s.length(); j ++) {
                b[i][j] = s.charAt(j);
            }
        }
        System.out.print(check(a, b));
    }
}