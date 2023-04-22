//package acw.pat.l_2020;
//
//import java.math.BigInteger;
//import java.util.Scanner;
//
//public class JJJJJ {
//    public static void main (String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int x = sc.nextInt();
//        BigInteger s = new BigInteger("1");
//
//       while (!s.mod(new BigInteger(Integer.toString(x))).equals(BigInteger.ZERO)) {
//          s = new BigInteger(s.toString() + "1");
//       }
//        System.out.println(s.divide(new BigInteger(Integer.toString(x))) + " " + s.toString().length());
//    }
//}
//
//import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.util.StringTokenizer;
//
//public class Main {
//
//    static class Solution {
//
//        int gAns = Integer.MAX_VALUE;
//
//        long n;
//
//        int solve(long n, long x) {
//            this.n = n;
//            dfs(x, 0);
//            return gAns == Integer.MAX_VALUE ? -1 : gAns;
//        }
//
//        void dfs(long v, int m) {
//            if (m >= gAns) return;
//
//            long cv = v;
//            int s = 0, cnt = 0;
//            while (cv > 0) {
//                int t = (int)(cv % 10);
//                s |= (1 << t);
//                cnt++;
//                cv /= 10;
//            }
//            if (cnt >= n) {
//                gAns = Math.min(gAns, m);
//                return;
//            }
//
//            if (m + (n - cnt) >= gAns) return;
//
//            if (m + 1 >= gAns) return;
//
//            for (int i = 9; i >= 2; i--) {
//                if ((s & (1 << i)) != 0) {
//                    dfs(v * i, m + 1);
//                }
//            }
//        }
//
//    }
//
//    // *)
//    static int solve(long n, long x) {
//        return 0;
//    }
//
//    public static void main(String[] args) {
//        AReader sc = new AReader();
//        long n = sc.nextLong();
//        long x = sc.nextLong();
//        Solution solution = new Solution();
//        System.out.println(solution.solve(n, x));
//    }
//
//    static
//    class AReader {
//        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        private StringTokenizer tokenizer = new StringTokenizer("");
//        private String innerNextLine() {
//            try {
//                return reader.readLine();
//            } catch (IOException ex) {
//                return null;
//            }
//        }
//        public boolean hasNext() {
//            while (!tokenizer.hasMoreTokens()) {
//                String nextLine = innerNextLine();
//                if (nextLine == null) {
//                    return false;
//                }
//                tokenizer = new StringTokenizer(nextLine);
//            }
//            return true;
//        }
//        public String nextLine() {
//            tokenizer = new StringTokenizer("");
//            return innerNextLine();
//        }
//        public String next() {
//            hasNext();
//            return tokenizer.nextToken();
//        }
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
////        public BigInteger nextBigInt() {
////            return new BigInteger(next());
////        }
//        // 若需要nextDouble等方法，请自行调用Double.parseDouble包装
//    }
//
//}
///*