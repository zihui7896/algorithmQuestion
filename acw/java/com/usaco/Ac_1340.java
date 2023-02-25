package acw.java.com.usaco;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Ac_1340 {
    public static final int N = 15;
    public static String[] name = new String[N];

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < t; i ++) name[i] = sc.next();

        for (int i = 0; i < t; i ++) {
            String s = sc.next();
            int sum = sc.nextInt(), cnt = sc.nextInt();
            if (cnt > 0) {
                map.put(s, map.getOrDefault(s, 0) - sum / cnt * cnt);
                for (int j = 0; j < cnt; j ++) {
                    String givename = sc.next();
                    map.put(givename, map.getOrDefault(givename, 0) + sum / cnt);
                }
            }
        }
        for (int i = 0; i < t; i ++) {
            System.out.println(name[i] + " " + map.getOrDefault(name[i], 0));
        }

    }
}
//import java.util.Scanner;
//        import java.util.Map;
//        import java.util.HashMap;
//        import java.util.LinkedHashMap;
//
//public class Main {
//    public static final int N = 15;
//
//    public static void main (String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int t = sc.nextInt();
//        Map<String, Integer> map = new LinkedHashMap<>();
//
//        for (int i = 0; i < t; i ++) map.put(sc.next(), 0);
//
//        for (int i = 0; i < t; i ++) {
//            String s = sc.next();
//            int sum = sc.nextInt(), cnt = sc.nextInt();
//            if (cnt > 0) {
//                map.put(s, map.get(s) - sum / cnt * cnt);
//                for (int j = 0; j < cnt; j ++) {
//                    String givename = sc.next();
//                    map.put(givename, map.get(givename) + sum / cnt);
//                }
//            }
//        }
//        for(var x : map.entrySet()){
//            System.out.println(x.getKey() + " " + x.getValue());
//        }
//
//    }
//}