package acw.com.java.weeklyRace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Ac_4868 {

    public static long n, x;
    public static  int ans = 1000;
    public static void dfs(long x, int d) {
        boolean[] st = new boolean[10];
        Arrays.fill(st, false);
        int cnt = 0;
        for (long y = x; y > 0; y /= 10) {
            cnt ++;
            st[(int)(y % 10)] = true;
        }
        if (n - cnt + d >= ans) return;
        if (cnt == n) {
            ans = d;
            return;
        }
        for (int i = 9; i >= 2; i --) {
            if (st[i]) {
                dfs(x * i, d + 1);
            }
        }
    }


    public static void main(String[] args) {
        AReader sc = new AReader();
        n = sc.nextLong();
        x = sc.nextLong();
        dfs(x, 0);
        if (ans == 1000) ans = -1;
        System.out.print(ans);
    }

    static
    class AReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");
        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException ex) {
                return null;
            }
        }
        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }
        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }
        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

//        public BigInteger nextBigInt() {
//            return new BigInteger(next());
//        }
        // 若需要nextDouble等方法，请自行调用Double.parseDouble包装
    }

}
