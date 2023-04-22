package acw.pat.l_2020;

import java.util.Scanner;
import java.util.Stack;

public class L2_2020_9 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Stack<Integer> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        for (int i = 0; i < n; i ++) {
            st1.push(sc.nextInt());
        }
        String sting = sc.nextLine();
        String s = sc.nextLine();
        for (int i = 0; i < s.length(); i += 2) {
            st2.push(s.charAt(i));
        }
        int res = 0;
        while (!st1.isEmpty() && !st2.isEmpty()) {
            int a = st1.pop(), b = st1.pop();
            char c = st2.pop();
            if (c == '/' && a == 0) {
                System.out.print("ERROR: " + b + "/" + a);
                return;
            } else {
                if (c == '+') {
                    res = a + b;
                } else if (c == '-') {
                    res = b - a;
                } else if (c == '*') {
                    res = a * b;
                } else {
                    res = b / a;
                }
                st1.push(res);
            }
        }
        System.out.print(res);
    }
}
