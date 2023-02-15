

## Leetcode

### 位运算

![](images\image-20220808173359864.png)

& 与 都为一才是一

| 或 都为零才是零

^ 异或 不进位加法

~ 取反 0变1 1 变0

"<<"  *2^n

">>" /2^n

##### 常见操作

**求n的第k位数字: n >> k & 1**

**返回n的最后一位1：lowbit(n) = n & -n**

**大写变小写、小写变大写：字符 ^= 32 （大写 ^= 32 相当于 +32，小写 ^= 32 相当于 -32）
大写变小写、小写变小写：字符 |= 32 （大写 |= 32 就相当于+32，小写 |= 32 不变）
大写变大写、小写变大写：字符 &= -33 （大写 ^= -33 不变，小写 ^= -33 相当于 -32）**

**指定位置的位运算
将X最右边的n位清零：x & (~0 << n)
获取x的第n位值：(x >> n) & 1
获取x的第n位的幂值：x & (1 << n)
仅将第n位置为1：x | (1 << n)
仅将第n位置为0：x & (~(1 << n))
将x最高位至第n位（含）清零：x & ((1 << n) - 1)
将第n位至第0位（含）清零：x & (~((1 << (n + 1)) - 1))**

**判断奇偶
(x & 1) == 1 ： (x % 2 == 1)
(x & 1) == 0 ： (x % 2 == 0)
x / 2 ： x >> 1
x &= (x - 1) ：把x最低位的二进制1给去掉
x & -x： 得到最低位的1
x & ~x：得到最低位的0**

##### Leetcode题目

###### [231. 2 的幂](https://leetcode.cn/problems/power-of-two/)

给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。

如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
    	return n > 0 && (n & (n - 1)) == 0;
    }
}
```

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (1 << 30) % n == 0;
    }
}
//无论2的多少次方都不可能小于等于0
// 1 << 30是最大的2的次方 看一下 % n == 0
// if == 0说明是2的幂次方， 否则不是，

```

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n; // 返回n的最后一位1：lowbit(n) = n & -n
    }
}
```

###### [762. 二进制表示中质数个计算置位](https://leetcode.cn/problems/prime-number-of-set-bits-in-binary-representation/)

给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。

计算置位位数 就是二进制表示中 1 的个数。

例如， 21 的二进制表示 10101 有 3 个计算置位。

暴力写法（模拟）

```java
class Solution {
    public int countPrimeSetBits(int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i ++) {
            int st = i, count = 0;
            while (st > 0) {		//Integer.bitCount(x)计算位1的个数
                if ((st & 1) == 1) {
                    count ++;
                }
                st >>= 1;
            }
            if (isprime(count)) {
                res ++;
            }
        }
        return res;
    }
    public boolean isprime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / i; i ++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
```

```java
//由于在10^6中所有的数位 1 的数是质数最大是 19
//10^6<2^20，因此二进制中 1 的个数不会超过 19，而不超过 19 的质数只有2,3,5,7,11,13,17,19
class Solution {
    public int countPrimeSetBits(int left, int right) {
        int res = 0;
        Set<Integer> set = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19)) ;
        for (int i = left; i <= right; i ++) {
            int st = i, count = 0;
            for (int k = i; k != 0; k >>= 1) {
                count += ((k & 1) == 1 ? 1 : 0);
            }
            if (set.contains(count)) {
                res ++;
            }
        }
        return res;
    }
}
/*java 中向 Set 添加多个元素
1. Set<Integer> set = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19)) ;
2.只是适用于List、Set、Map这三个接口的静态方法，其父类接口和子类实现并没有这类方法，比如 HashSet，ArrayList;
of方法返回值是一个不能改变的集合，不能再add或者put进去东西;
Set和Map接口调用of的时候不能有重复元素;
public class OfLianXi {
    public static void main(String[] args) {
       List<String> list = List.of("a", "b", "c", "d");
        System.out.println(list);

        Set<String> set = Set.of("a", "b", "c", "d");
        System.out.println(set);

        Map<String, Integer> map = Map.of("张三", 100, "李四", 120, "王五", 100);
        System.out.println(map);
    }
}
3.Integer[] x=new Integer[]{4,6,9,10};
Set<Integer> set = new HashSet<>() ;
Collections.addAll(set,x);
for(Integer ele:set){
    System.out.println(ele);
}

```

```java
/*我们可以用一个二进制数 \textit{mask}=665772=10100010100010101100  来存储这些质数，其中 mask 二进制的从低到高的第 i 位为 1 表示 i 是质数，为 0 表示 i 不是质数。
设整数 x 的二进制中 1 的个数为 c，若 mask 按位与 2^c 不为 0，则说明 c 是一个质数
*/
  class Solution {
    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int x = left; x <= right; ++x) {
            if (((1 << Integer.bitCount(x)) & 665772) != 0) {
                ++ans;
            }
        }
        return ans;
    }
}
```

###### [**136. 只出现一次的数字**](https://leetcode.cn/problems/single-number/)_

给定一个**非空**整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

```java
/* 两个相同的数 ^(异或) 为 0; 满足交换律, 结合律;
a ^ a = 0
0 ^ a = a
*/
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
```

###### [137. 只出现一次的数字 II](https://leetcode.cn/problems/single-number-ii/)

lc题解（https://leetcode.cn/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/）

给你一个整数数组 `nums` ，除某个元素仅出现 **一次** 外，其余每个元素都恰出现 **三次 。**请你找出并返回那个只出现了一次的元素。



```c++
//	考虑二进制每一位上出现 0 和 1 的次数，如果出现 1 的次数为 3k + 1，则证明答案中这一位是 1。
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for (int bit = 0; bit < 32; bit++) {
            int counter = 0;
            for (int i = 0; i < nums.size(); i++)
                counter += (nums[i] >> bit) & 1;
            ans += (counter % 3) << bit;
        }
        return ans;
    }
};
```



```java
/* ones = (ones ^ i) & ~twos;
   twos = (twos ^ i) & ~ones;
   初始       0 0 
   1 个 1	1 0
   2 个 1	0 1
   3 个 1	0 0 
   发现每 3 个 一循环
*/
class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i : nums) {
            ones = (ones ^ i) & ~twos;
            twos = (twos ^ i) & ~ones;
        }
        return ones;
    }
}
```

###### [260. 只出现一次的数字 III](https://leetcode.cn/problems/single-number-iii/)

给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。

进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

```Java
/* 先所有的数 ^ (异或) 一遍, 得到的一定是 那两个只出现一次数的异或和, 因为相同的数异或和为 0
又因为异或和不为 0， 一定存在有一位(假设为k) 是 1， 另一个的那位为 0；
所以可以把第 K 位 是 1 的分成一堆, 是 0 的分成一堆, 再分别 取异或和;
*/
class Solution {
    public int[] singleNumber(int[] nums) {
        int s = 0;
        for (int i : nums) {
            s ^= i;
        }
        int k = 0;
        while (((s >> k) & 1) == 0) k ++;
        int s1 = 0;
        for (int x : nums) {
            if (((x >> k) & 1) == 1) {
                s1 ^= x;
            }
        }
        return new int[]{s1, s1 ^ s};
    }
}
```

###### [371. 两整数之和](https://leetcode.cn/problems/sum-of-two-integers/)

给你两个整数 `a` 和 `b` ，**不使用** 运算符 `+` 和 `-` ，计算并返回两整数之和。

```java
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;		// 有哪些进位
            a = a ^ b;		// 不进位和
            b = carry;
        }
        return a;
    }
}
/*
递归版本
class Solution {
    public int getSum(int a, int b) {
        if(b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }
}
*/
```



###### [476. 数字的补数](https://leetcode.cn/problems/number-complement/)

对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。

例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
给你一个整数 num ，输出它的补数。

```java
class Solution {
    public int findComplement(int num) {
        int res = 0, t = 0;
        while (num != 0) {
            res += (num & 1) == 1 ? 0 : 1 << t;		// 看 num 每一位是 0 还是 1, 如果是 1 取反应该是 0，反之是 1;
            num >>= 1;
            t ++;
        }
        return res;
    }
}
```

```javascript
// 先找出num的位数，然后异或上同等位数的最大值
var findComplement = function (num) {
    var tmp = num, c = 1;
    while (tmp) {
        c <<= 1;
        tmp >>= 1;
    }
    return num ^ c - 1;
};
```

![image-20220809125635357](C:\Users\33359\AppData\Roaming\Typora\typora-user-images\image-20220809125635357.png)

```java
class Solution {
    public int findComplement(int num) {
        int highbit = 0;
        for (int i = 1; i <= 30; ++i) {
            if (num >= 1 << i) {
                highbit = i;
            } else {
                break;
            }            
        }
        int mask = highbit == 30 ? 0x7fffffff : (1 << (highbit + 1)) - 1;
        return num ^ mask;
    }
}

```

###### [201. 数字范围按位与](https://leetcode.cn/problems/bitwise-and-of-numbers-range/)、

给你两个整数 `left` 和 `right` ，表示区间 `[left, right]` ，返回此区间内所有数字 **按位与** 的结果（包含 `left` 、`right` 端点）。

lc题解https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/solution/shu-zu-zhong-liang-ge-shu-de-zui-da-yi-h-n9m9/

```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int res = 0;

        for (int i = 0; (1L << i) <= left; i ++) {
            if (((left >> i) & 1) == 1) {
                if ((left & ~((1 << i) - 1L)) + (1 << i) > right) {
                    res += 1 << i;
                }
            }
        }
        return res;
    }
}
```



```java
class Solution {
    // 字典树的根节点
    Trie root = new Trie();
    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 30;

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int x = 0;
        for (int i = 1; i < n; ++i) {
            // 将 nums[i-1] 放入字典树，此时 nums[0 .. i-1] 都在字典树中
            add(nums[i - 1]);
            // 将 nums[i] 看作 ai，找出最大的 x 更新答案
            x = Math.max(x, check(nums[i]));
        }
        return x;
    }

    public void add(int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; --k) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            }
            else {
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    public int check(int num) {
        Trie cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                // a_i 的第 k 个二进制位为 0，应当往表示 1 的子节点 right 走
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // a_i 的第 k 个二进制位为 1，应当往表示 0 的子节点 left 走
                if (cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }
        }
        return x;
    }
}

class Trie {
    // 左子树指向表示 0 的子节点
    Trie left = null;
    // 右子树指向表示 1 的子节点
    Trie right = null;
}

```



###### [421. 数组中两个数的最大异或值](https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/)

给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

进阶：你可以在 O(n) 的时间解决这个问题吗？

https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/solution/shu-zu-zhong-liang-ge-shu-de-zui-da-yi-h-n9m9/

时间复杂度： O(n)
思想：将每个数字的二进制位，从高位到低位存储到前缀树中，也就是说前缀树中仅有0和1这两个数字。

根据数学知识可以知道：2^i > 2^i−1+2^i−2+…+2^1+2^0
可以发现：异或只要两位不相同就是1，如果高位有一位是1，那么数就会大于这一位是0且低位全是1的情况。这就是从高位开始遍历的贪心思想。
如果某一位二进制位是0，但是前缀树的遍历过程中没有1的分支，则被迫走0的分支，反过来同理
树的高度由二进制位最多的数字决定，所以分支非0即1，不会有某一个数的二进制位先走到底的情况

```c++
class Solution {
public:
    typedef long long LL;

    int findMaximumXOR(vector<int>& nums) {
        unordered_set<LL> edge;
        int res = 0;
        for (auto &x : nums)
        {
            LL pre = 0, pre_op = 0;
            int xorr = 0;
            for (int i = 30; i >= 0; i--)
            {
                int next = x >> i & 1;
                edge.insert(pre + next * (1LL << 32) + i * (1LL << 33));
                if (edge.count(pre_op + !next * (1LL << 32) + i * (1LL << 33)))
                {
                    xorr = xorr * 2 + 1;
                    pre_op = pre_op * 2 + !next;
                }
                else
                {
                    xorr <<= 1;
                    pre_op = pre_op * 2 + next;
                }
                pre = pre * 2 + next;
            }
            res = max(res, xorr);
        }
        return res;
    }
};
```

```java
// 先遍历一遍所有整数，把所有二进制位建树
//再遍历一遍所有整数，尽可能将每个整数的尽可能相反的二进制位从树中找到，并且记录下每一轮循环的最大异或值
class Solution {
    class Node{
        Node[] son = new Node[2];
    }
    Node root = new Node();
    void insert(int x){
        Node p = root;
        for(int i = 30; i >= 0; i--){
            int t = (x >> i) & 1;
            if(p.son[t] == null){
                p.son[t] = new Node();
            }
            p = p.son[t];
        }
    }

    int search(int x){
        Node p = root;
        int xor = 0;
        for(int i = 30; i >= 0; i--){
            int t = (x >> i) & 1;
            if(t == 0 && p.son[1] != null){
                xor += (1 << i);
                p = p.son[1];
            }else if(t == 1 && p.son[0] != null){
                xor += (1 << i);
                p = p.son[0];
            }else{
                p = p.son[t];
            }
        }
        return xor;
    }

    public int findMaximumXOR(int[] nums) {
        if(nums.length == 0) return 0;
        for(int x: nums) insert(x);
        int res = 0;
        for(int x: nums) res = Math.max(res, search(x));
        return res;
    }
}
```



###### [477. 汉明距离总和](https://leetcode.cn/problems/total-hamming-distance/)

两个整数的 [汉明距离](https://baike.baidu.com/item/汉明距离/475174?fr=aladdin) 指的是这两个数字的二进制数对应位不同的数量。

给你一个整数数组 `nums`，请你计算并返回 `nums` 中任意两个数之间 **汉明距离的总和** 

输入：nums = [4,14,2]
输出：6
解释：在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
所以答案为：
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6

```java
/*
汉明距离 只用判断二进制表示 有多少位不同 
对于每一位来说, 只有 0 和 1, 发现对于这一位不同就是 0 的个数 乘以 1 的个数
*/
class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i <= 30; i ++) {
            int ones = 0;
            for (int x : nums) {
                if ((x >> i & 1) == 0) {
                    ones ++;
                }
            }
            res += ones * (nums.length - ones);
        }
        return res;
    }
}
```

### 字符串



### 动态规划

###### [368. 最大整除子集](https://leetcode.cn/problems/largest-divisible-subset/)

给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
answer[i] % answer[j] == 0 ，或
answer[j] % answer[i] == 0
如果存在多个有效解子集，返回其中任何一个均可

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int n = nums.length;
        int[] dp = new int[n];
        int k = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i ++) {
            dp[i] = 1;
            for (int j = 0; j < i; j ++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[k] < dp[i]) {
                k = i;
            }
        }
        while (true) {
            res.add(nums[k]);
            if (dp[k] == 1) break;
            for (int i = 0; i < k; i ++) {
                if (dp[i] + 1 == dp[k] && nums[k] % nums[i] == 0) {
                    k = i;
                    break;
                }
            }
        }
        return res;
    }
}
```

###### [62. 不同路径](https://leetcode.cn/problems/unique-paths/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < n; i ++) dp[0][i] = 1;
        for (int i = 0; i < m; i ++) dp[i][0] = 1;

        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

```java
class Solution {
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
```



### dfs / bfs

###### [78. 子集](https://leetcode.cn/problems/subsets/)

给你一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。

```java
class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < (1 << n); i ++) {
            t.clear();
            for (int j = 0; j < n; j ++) {
                if ((i & (1 << j)) != 0) {
                    t.add(nums[j]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

}
```

```java
class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return ans;
    }
    public void dfs(int u, int[] arr) {
        if (u == arr.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        t.add(arr[u]);
        dfs(u + 1, arr);
        t.remove(t.size() - 1);
        dfs(u + 1, arr);
    }

}
```

###### [111. 二叉树的最小深度](https://leetcode.cn/problems/minimum-depth-of-binary-tree/)

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

**说明：**叶子节点是指没有子节点的节点。

```java
class Solution {		// dfs
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (root.left == null || root.right == null) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

}
```

###### [279. 完全平方数](https://leetcode.cn/problems/perfect-squares/)

给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

```

```



### 贪心

###### [781. 森林中的兔子](https://leetcode.cn/problems/rabbits-in-forest/)

森林中有未知数量的兔子。提问其中若干只兔子 "还有多少只兔子与你（指被提问的兔子）颜色相同?" ，将答案收集到一个整数数组 answers 中，其中 answers[i] 是第 i 只兔子的回答。

给你数组 answers ，返回森林中兔子的最少数量。

```java
/*
两只相同颜色的兔子看到的其他同色兔子数必然是相同的。反之，若两只兔子看到的其他同色兔子数不同，那么这两只兔子颜色也不同。
因此，将 answers 中值相同的元素分为一组，对于每一组，计算出兔子的最少数量，然后将所有组的计算结果累加，就是最终的答案。

一般地，如果有 x 只兔子都回答 y，则至少有 x / (y + 1) 上取整  种不同的颜色，且每种颜色有 y+1 只兔子，因此兔子数至少为 x / (y + 1) 上取整 * (y + 1);
x / (y + 1) 上取整 ==> (x + y) / (y + 1)
*/
class Solution {
    public int numRabbits(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int x : answers) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int y = entry.getKey(), x = entry.getValue();
            res += (x + y) / (y + 1) * (y + 1);
        }
        return res;
    }
}
```

```java
class Solution {
    public int numRabbits(int[] answers) {
//m[i]>0   先前已经记录到有回答i的兔子,这次遇到只需容量减1
//m[i]==0  第一次遇到回答i的兔子或者上一次遇到回答i的兔子时创建颜色的容量已经用完. 创建新的颜色,容量为i,并将这一波兔子数量加到结果中
        int[] m = new int[1000];
        int result = 0;
        for (int i : answers) {
            if (m[i] > 0) {
                m[i]--;
            } else {
                m[i] = i;
                result += i + 1;
            }
        }
        return result;
    }
}
```



### 链表

###### [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)

给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dum = new ListNode();
        dum.next = head;
        ListNode p = dum;
        while (p != null && p.next != null && p.next.next != null) {
            ListNode a = p.next, b = a.next;
            p.next = b;
            a.next = b.next;
            b.next = a;
            p = a;
        }
        return dum.next;
    }
}
```

###### [141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)

给你一个链表的头节点 head ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。

如果链表中存在环 ，则返回 true 。 否则，返回 false 。

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode one = dummy, two = dummy;
        while (two != null && two.next != null) {
            one = one.next;
            two = two.next.next;
            if (one == two) return true;
        }
        return false;
    }
}
/*
使用双指针技巧中的快慢指针，每当慢指针 slow 前进一步，快指针 fast 就前进两步。

如果 fast 最终遇到空指针，说明链表中没有环；如果 fast 最终和 slow 相遇，那肯定是 fast 超过了 slow 一圈，说明链表中含有环。
*/
```



###### [237. 删除链表中的节点](https://leetcode.cn/problems/delete-node-in-a-linked-list/)

请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。

题目数据保证需要删除的节点 不是末尾节点 。

```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;		//将这个结点的值变成下一个结点的值，然后跳过下一个结点
        node.next = node.next.next;
    }
} 
```



### 数据结构

### 二分

###### [1011. 在 D 天内送达包裹的能力](https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/)

传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。

传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。

返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。

```java
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int sum = Arrays.stream(weights).sum();
        int l = 0, r = sum;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid, days, weights)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    public boolean check(int x, int target, int[] arr) {
        int res = 1;
        for (int i = 0, s = 0; i < arr.length; i ++) {
            if (arr[i] > x) return false;
            if (arr[i] + s > x) {
                s = 0;
                res ++;
            }
            s += arr[i];
        }
        return res <= target;
    }
}
```

有 N 根绳子，第 i 根绳子长度为 Li，现在需要 M 根等长的绳子，你可以对 N 根绳子进行任意裁剪（不能拼接），请你帮忙计算出这 M 根绳子最长的长度是多少

```java
import java.util.Scanner;

public class Main{
    public static final int N = 100010;
    public static int[] arr = new int[N];
    
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        double l = 0, r = 0;
        for (int i = 0; i < n; i ++) {
            arr[i] = sc.nextInt();
            r += arr[i];
        }
        
        while (r - l > 1e-6) {
            double mid = (l + r) / 2;
            if (check(mid, m)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.printf("%.2f", l);
    }
    public static boolean check(double x, int m) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i ++) {
            cnt += arr[i] / x;
        }
        return cnt >= m;
    }
}
```

### 数学

###### [62. 不同路径](https://leetcode.cn/problems/unique-paths/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

```java
class Solution {
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {		// C(n + m - 2) (n - 1)
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
```

###### [268. 丢失的数字](https://leetcode.cn/problems/missing-number/)

给定一个包含 `[0, n]` 中 `n` 个数的数组 `nums` ，找出 `[0, n]` 这个范围内没有出现在数组中的那个数。

```java
class Solution {
    public int missingNumber(int[] nums) {		// 位运算 相同的数 ^ == 0
        int xor = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;
    }
}
```

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length, sum = 0;
        for (int i = 0; i < n; i ++) {
            sum += nums[i];
        }
        return (n * (n + 1) / 2) - sum;
    }
}
```

###### [453. 最小操作次数使数组元素相等](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/)

给你一个长度为 `n` 的整数数组，每次操作将会使 `n - 1` 个元素增加 `1` 。返回让数组所有元素相等的最小操作次数。

```java
class Solution {
    public int minMoves(int[] nums) {
        int minv = Integer.MAX_VALUE;
        for (int x : nums) {		// minv = Arrays.stream(nums).min().getAsInt();
            minv = Math.min(minv, x); 
        }
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            res += nums[i] - minv;
        }
        return res;
    }
}
```

###### [462. 最少移动次数使数组元素相等 II](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/)

给你一个长度为 `n` 的整数数组 `nums` ，返回使所有数组元素相等需要的最少移动数。

在一步操作中，你可以使数组中的一个元素加 `1` 或者减 `1` 。

```java
class Solution {		// 货仓选址 ： 选其中一个 地址 到 其他地址 的和最小
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);		// 可以用 快速选择算法 排序 时间复杂度 O(n);
        int target = nums[nums.length / 2];
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            res += Math.abs(nums[i] - target);
        }
        return res;
    }
}
```

###### [319. 灯泡开关](https://leetcode.cn/problems/bulb-switcher/)

![image-20220818091526454](C:\Users\33359\AppData\Roaming\Typora\typora-user-images\image-20220818091526454.png)

```java
class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n + 0.5);
    }
}
```

###### [343. 整数拆分](https://leetcode.cn/problems/integer-break/)

给定一个正整数 `n` ，将其拆分为 `k` 个 **正整数** 的和（ `k >= 2` ），并使这些整数的乘积最大化。

返回 *你可以获得的最大乘积* 

首先把一个正整数 NN 拆分成若干正整数只有有限种拆法，所以存在最大乘积。
假设 N=n1+n2+…+nk，并且 n1×n2×…×nk 是最大乘积。

显然1不会出现在其中； //  1 对乘积结果没有如何变化，但和变小
**如果对于某 i 有 ni≥5，那么把 ni 拆分成 3+(ni−3)，我们有 3(ni−3)=3ni−9>ni；**
**如果 ni=4，拆成 2+2乘积不变，所以不妨假设没有4；**
**如果有三个以上的2，那么 3×3>2×2×2，所以替换成3乘积更大；**
综上，选用尽量多的3，直到剩下2或者4时，用2。

时间复杂度分析：当 n 比较大时，n 会被拆分成 ⌈n/3⌉ 个数，我们需要计算这么多次减法和乘法，所以时间复杂度是 O(n)。

```java
class Solution {
    public int integerBreak(int n) {
        int res = 1;
        if (n <= 3) return 1 * (n - 1);
        if (n % 3 == 1) {
            res *= 4;
            n -= 4;
        }
        if (n % 3 == 2) {
            res *= 2;
            n -= 2;
        }
        while (n > 0) {
            res *=  3;
            n -= 3;
        }
        return res;
    }
}
/*
class Solution {
    public int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), 3 * dp[i - 3]));
        }
        return dp[n];
    }
}
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}
*/
```



### 模拟

###### [263. 丑数](https://leetcode.cn/problems/ugly-number/)

丑数 就是只包含质因数 2、3 和 5 的正整数。

给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。

```java
class Solution { // O(lgn)
    public boolean isUgly(int n) {
        int[] arr = new int[]{2, 3, 5};
        for (int x : arr) {
            while (n > 0 && n % x == 0) n /= x;
        }
        return n == 1;
    }
}
```

###### [67. 二进制求和](https://leetcode.cn/problems/add-binary/)

给你两个二进制字符串，返回它们的和（用二进制表示）。

输入为 非空 字符串且只包含数字 1 和 0。

```java
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder("");
        StringBuilder sa = new StringBuilder("");
        StringBuilder sb = new StringBuilder("");
        sa = sa.append(a).reverse();
        sb = sb.append(b).reverse();
        int carry = 0;
        for (int i = 0; i < sa.length() || i < sb.length(); i ++) {
            int va = i >= sa.length() ? 0 : sa.charAt(i) - '0';
            int vb = i >= sb.length() ? 0 : sb.charAt(i) - '0';
            int s = va + vb + carry;
            carry = s / 2;
            s %= 2;
            res.append(s);
        }
        if (carry == 1) res.append('1');
        return res.reverse().toString(); 
        /*	return Integer.toBinaryString(
            	Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        	);
        */
    }
}
```

###### [504. 七进制数](https://leetcode.cn/problems/base-7/)

给定一个整数 `num`，将其转化为 **7 进制**，并以字符串形式输出。

```java
//	return Integer.toString(num,7);
class Solution {
    public String convertToBase7(int num) {
        StringBuilder res = new StringBuilder();
        boolean st = false;
        if (num < 0) {
            num *= -1;
            st = true;
        } else if (num == 0) {
            return "0";
        }
        while (num > 0) {
            res.append(num % 7);
            num /= 7;
        }
        if (st) res.append('-');
        return res.reverse().toString();
    }
}
```

###### [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix[0].length, m = matrix.length;
        List<Integer> res = new ArrayList<Integer>();
        boolean[][] st = new boolean[m][n];
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        int x = 0, y = 0, d = 1;
        for (int k = 0; k < n * m; k ++) {
            res.add(matrix[x][y]);
            st[x][y] = true;
            int a = x + dx[d], b = y + dy[d];
            if (a < 0 || a >= m || b < 0 || b >= n || st[a][b]) {
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
        return res;
    }
}
```

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
/*
可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。

定义矩阵的第 k 层是到最近边界距离为 k 的所有顶点。例如，下图矩阵最外层元素都是第 1 层，次外层元素都是第 2 层，剩下的元素都是第 3 层。
[[1, 1, 1, 1, 1, 1, 1],
 [1, 2, 2, 2, 2, 2, 1],
 [1, 2, 3, 3, 3, 2, 1],
 [1, 2, 2, 2, 2, 2, 1],
 [1, 1, 1, 1, 1, 1, 1]]

*/
```

![image-20220816203745298](C:\Users\33359\AppData\Roaming\Typora\typora-user-images\image-20220816203745298.png)

![image-20220816203814839](C:\Users\33359\AppData\Roaming\Typora\typora-user-images\image-20220816203814839.png)

###### [299. 猜数字游戏](https://leetcode.cn/problems/bulls-and-cows/)

你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：

猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls"，公牛），
有多少位属于数字猜对了但是位置不对（称为 "Cows"，奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。

提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。

请注意秘密数字和朋友猜测的数字都可能含有重复数字。

```java
class Solution {
    public String getHint(String secret, String guess) {
        int a = 0, b = 0;
        int[] ga = new int[10], gb = new int[10];
        for (int i = 0; i < guess.length(); i ++) {
            int x = secret.charAt(i) - '0', y = guess.charAt(i) - '0';
            if (x == y) a ++;
            ga[x] ++;
            gb[y] ++;
        }
        for (int i = 0; i < 10; i ++) {
            b += Math.(ga[i], gb[i]);
        }
        b -= a;
        return a + "A" + b + "B";
    }
}
```

###### [481. 神奇字符串](https://leetcode.cn/problems/magical-string/)

神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：

神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。

给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。

```java
class Solution {
    public int magicalString(int n) {
        StringBuilder s = new StringBuilder();
        s.append("122");
        for (int i = 2, k = 1; i < n; i ++, k = 3 - k) {
            for (int j = 0; j < s.charAt(i) - '0'; j ++) {
                s.append(k);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i ++) {
            if (s.charAt(i) == '1') res ++;
        }
        return res;
    }
}
```

###### [71. 简化路径](https://leetcode.cn/problems/simplify-path/)

给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。

请注意，返回的 规范路径 必须遵循下述格式：

始终以斜杠 '/' 开头。
两个目录名之间必须只有一个斜杠 '/' 。
最后一个目录名（如果存在）不能 以 '/' 结尾。
此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
返回简化后得到的 规范路径 。

```c++
/*
path = "/home/", => "/home"；
path = "/a/./b/../../c/", => "/c"；
边界情况：
你有没有考虑 path = "/../"？这时你需要返回 "/"
有些路径可能包含多个连续的'/'，例如 "/home//foo/"。在这种情况下，你需要忽略多余的斜杠，返回 "/home/foo"。
我们可以把整个路径看作是一个动态的“进入某个子目录”或“返回上级目录”的过程。

所以我们可以模拟这个过程，用 res 记录当前的路径位置：

如果遇到 ".."，则返回上级目录；
如果遇到 "."或多余的斜杠，则不做任何处理：
其它情况，表示进入某个子目录，我们在 res 后面补上新路径即可；
时间复杂度分析：整个路径 path 我们只遍历一遍，且 res 添加和删除的路径均是 path 的真子集，其总长度不大于 path 的长度，所以时间复杂度是 O(n)。
*/
class Solution {
public:
    string simplifyPath(string path) {
        if (path.back() != '/') path += '/';
        string res, s;
        for (auto &c : path)
        {
            if (res.empty()) res += c;
            else if (c == '/')
            {
                if (s == "..")
                {
                    if (res.size() > 1)
                    {
                        res.pop_back();
                        while (res.back() != '/') res.pop_back();
                    }
                }
                else if (s != "" && s != ".")
                {
                    res += s + '/';
                }
                s = "";
            }
            else
            {
                s += c;
            }
        }
        if (res.size() > 1) res.pop_back();
        return res;
    }
};
```

###### [12. 整数转罗马数字](https://leetcode.cn/problems/integer-to-roman/)

![image-20220817111028915](C:\Users\33359\AppData\Roaming\Typora\typora-user-images\image-20220817111028915.png)

```java
class Solution {
    public String intToRoman(int num) {
        int[] arr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] s = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String res = new String();
        for (int i = 0; i < 13; i ++) {
            while (num >= arr[i]) {
                num -= arr[i];
                res += s[i];
            }
        }
        return res;
    }
}
```



## 由数据范围反推算法复杂度以及算法内容

一般ACM或者笔试题的时间限制是1秒或2秒。
在这种情况下，C++代码中的操作次数控制在 107∼108107∼108 为最佳。

下面给出在不同数据范围下，代码的时间复杂度和算法该如何选择：

```c++
n≤30, 指数级别, dfs+剪枝，状态压缩dp

n≤100 => O(n3)，floyd，dp，高斯消元

n≤1000 => O(n2)，O(n2logn)O(n2logn)，dp，二分，朴素版Dijkstra、朴素版Prim、Bellman-Ford

n≤10000 => O(n∗n√)，块状链表、分块、莫队

n≤100000 => O(nlogn) => 各种sort，线段树、树状数组、set/map、heap、拓扑排序、dijkstra+heap、prim+heap、Kruskal、spfa、求凸包、求半平面交、二分、CDQ分治、整体二分、后缀数组、树链剖分、动态树

n≤1000000 => O(n), 以及常数较小的 O(nlogn) 算法 => 单调队列、 hash、双指针扫描、并查集，kmp、AC自动机，常数比较小的 O(nlogn)O(nlogn) 的做法：sort、树状数组、heap、dijkstra、spfa

n≤10000000 => O(n)，双指针扫描、kmp、AC自动机、线性筛素数

n≤10^9 => O(n√)，判断质数

n≤10^18 => O(logn)，最大公约数，快速幂，数位DP

n≤10^1000 => O((logn)2)O((logn)2)，高精度加减乘除

n≤10^100000 => O(logk×loglogk)，k表示位数O(logk×loglogk)，k表示位数，高精度加减、FFT/NTT
```



## 算法模板

#### 快速排序算法模板 —— 模板题  785. 快速排序

```c++
void quick_sort(int q[], int l, int r)
{
    if (l >= r) return;

    int i = l - 1, j = r + 1, x = q[l + r >> 1];
    while (i < j)
    {
        do i ++ ; while (q[i] < x);
        do j -- ; while (q[j] > x);
        if (i < j) swap(q[i], q[j]);
    }
    quick_sort(q, l, j), quick_sort(q, j + 1, r);

}
```

#### 归并排序算法模板 —— 模板题  787. 归并排序

```c++
void merge_sort(int q[], int l, int r)
{
    if (l >= r) return;

    int mid = l + r >> 1;
    merge_sort(q, l, mid);
    merge_sort(q, mid + 1, r);
    
    int k = 0, i = l, j = mid + 1;
    while (i <= mid && j <= r)
        if (q[i] <= q[j]) tmp[k ++ ] = q[i ++ ];
        else tmp[k ++ ] = q[j ++ ];
    
    while (i <= mid) tmp[k ++ ] = q[i ++ ];
    while (j <= r) tmp[k ++ ] = q[j ++ ];
    
    for (i = l, j = 0; i <= r; i ++, j ++ ) q[i] = tmp[j];

}
```

#### 整数二分算法模板 —— 模板题  789. 数的范围

```c++
bool check(int x) {/* ... */} // 检查x是否满足某种性质

// 区间[l, r]被划分成[l, mid]和[mid + 1, r]时使用：
int bsearch_1(int l, int r)
{
    while (l < r)
    {
        int mid = l + r >> 1;
        if (check(mid)) r = mid;    // check()判断mid是否满足性质
        else l = mid + 1;
    }
    return l;
}
// 区间[l, r]被划分成[l, mid - 1]和[mid, r]时使用：
int bsearch_2(int l, int r)
{
    while (l < r)
    {
        int mid = l + r + 1 >> 1;
        if (check(mid)) l = mid;
        else r = mid - 1;
    }
    return l;
}
```

#### 浮点数二分算法模板 —— 模板题  790. 数的三次方根

```c++
bool check(double x) {/* ... */} // 检查x是否满足某种性质

double bsearch_3(double l, double r)
{
    const double eps = 1e-6;   // eps 表示精度，取决于题目对精度的要求
    while (r - l > eps)
    {
        double mid = (l + r) / 2;
        if (check(mid)) r = mid;
        else l = mid;
    }
    return l;
}
```

#### 高精度加法 —— 模板题791. 高精度加法

```c++
// C = A + B, A >= 0, B >= 0
vector<int> add(vector<int> &A, vector<int> &B)
{
    if (A.size() < B.size()) return add(B, A);

    vector<int> C;
    int t = 0;
    for (int i = 0; i < A.size(); i ++ )
    {
        t += A[i];
        if (i < B.size()) t += B[i];
        C.push_back(t % 10);
        t /= 10;
    }
    
    if (t) C.push_back(t);
    return C;

}
```

#### 高精度减法 —— 模板题 792. 高精度减法

```c++
// C = A - B, 满足A >= B, A >= 0, B >= 0
vector<int> sub(vector<int> &A, vector<int> &B)
{
    vector<int> C;
    for (int i = 0, t = 0; i < A.size(); i ++ )
    {
        t = A[i] - t;
        if (i < B.size()) t -= B[i];
        C.push_back((t + 10) % 10);
        if (t < 0) t = 1;
        else t = 0;
    }

    while (C.size() > 1 && C.back() == 0) C.pop_back();
    return C;

}
```



#### 高精度乘低精度 —— 模板题 793. 高精度乘法

```c++
// C = A * b, A >= 0, b >= 0
vector<int> mul(vector<int> &A, int b)
{
    vector<int> C;

    int t = 0;
    for (int i = 0; i < A.size() || t; i ++ )
    {
        if (i < A.size()) t += A[i] * b;
        C.push_back(t % 10);
        t /= 10;
    }
    
    while (C.size() > 1 && C.back() == 0) C.pop_back();
    
    return C;

}
```

#### 高精度除以低精度 —— 模板题794. 高精度除法

```c++
// A / b = C ... r, A >= 0, b > 0
vector<int> div(vector<int> &A, int b, int &r)
{
    vector<int> C;
    r = 0;
    for (int i = A.size() - 1; i >= 0; i -- )
    {
        r = r * 10 + A[i];
        C.push_back(r / b);
        r %= b;
    }
    reverse(C.begin(), C.end());
    while (C.size() > 1 && C.back() == 0) C.pop_back();
    return C;
}
```

#### 前缀和

```c++
一维前缀和 —— 模板题 AcWing 795. 前缀和

S[i] = a[1] + a[2] + ... a[i]
a[l] + ... + a[r] = S[r] - S[l - 1]

二维前缀和 —— 模板题 AcWing 796. 子矩阵的和

S[i, j] = 第i行j列格子左上部分所有元素的和
以(x1, y1)为左上角，(x2, y2)为右下角的子矩阵的和为：
S[x2, y2] - S[x1 - 1, y2] - S[x2, y1 - 1] + S[x1 - 1, y1 - 1]
```

#### 差分

```c++
一维差分 —— 模板题 AcWing 797. 差分
给区间[l, r]中的每个数加上c：B[l] += c, B[r + 1] -= c
二维差分 —— 模板题 AcWing 798. 差分矩阵
给以(x1, y1)为左上角，(x2, y2)为右下角的子矩阵中的所有元素加上c：
S[x1, y1] += c, S[x2 + 1, y1] -= c, S[x1, y2 + 1] -= c, S[x2 + 1, y2 + 1] += c
```

#### 位运算 —— 模板题 801. 二进制中1的个数

```c++
求n的第k位数字: n >> k & 1
返回n的最后一位1：lowbc++it(n) = n & -n
```



#### 双指针算法 —— 模板题 799. 最长连续不重复子序列,

#### 800. 数组元素的目标和

```c++
for (int i = 0, j = 0; i < n; i ++ )
{
    while (j < i && check(i, j)) j ++ ;

    // 具体问题的逻辑

}
/*常见问题分类：
    (1) 对于一个序列，用两个指针维护一段区间
    (2) 对于两个序列，维护某种次序，比如归并排序中合并两个有序列的操作*/
```

#### 离散化 —— 模板题 802. 区间和

```c++
vector<int> alls; // 存储所有待离散化的值
sort(alls.begin(), alls.end()); // 将所有值排序
alls.erase(unique(alls.begin(), alls.end()), alls.end());   // 去掉重复元素// 二分求出x对应的离散化的值
int find(int x) // 找到第一个大于等于x的位置
{
    int l = 0, r = alls.size() - 1;
    while (l < r)
    {
        int mid = l + r >> 1;
        if (alls[mid] >= x) r = mid;
        else l = mid + 1;
    }
    return r + 1; // 映射到1, 2, ...n
}
```

#### 区间合并 —— 模板题  803. 区间合并

```c++
// 将所有存在交集的区间合并
void merge(vector<PII> &segs)
{
    vector<PII> res;

    sort(segs.begin(), segs.end());
    
    int st = -2e9, ed = -2e9;
    for (auto seg : segs)
        if (ed < seg.first)
        {
            if (st != -2e9) res.push_back({st, ed});
            st = seg.first, ed = seg.second;
        }
        else ed = max(ed, seg.second);
    
    if (st != -2e9) res.push_back({st, ed});
    
    segs = res;

}
```

## 数据结构

#### 单链表 —— 模板题 826. 单链表

```c++
// head存储链表头，e[]存储节点的值，ne[]存储节点的next指针，idx表示当前用到了哪个节点
int head, e[N], ne[N], idx;

// 初始化
void init()
{
    head = -1;
    idx = 0;
}

// 在链表头插入一个数a
void insert(int a)
{
    e[idx] = a, ne[idx] = head, head = idx ++ ;
}

// 将头结点删除，需要保证头结点存在
void remove()
{
    head = ne[head];
}
```

#### 双链表 —— 模板题  827. 双链表

```c++
// e[]表示节点的值，l[]表示节点的左指针，r[]表示节点的右指针，idx表示当前用到了哪个节点
int e[N], l[N], r[N], idx;

// 初始化
void init()
{
    //0是左端点，1是右端点
    r[0] = 1, l[1] = 0;
    idx = 2;
}

// 在节点a的右边插入一个数x
void insert(int a, int x)
{
    e[idx] = x;
    l[idx] = a, r[idx] = r[a];
    l[r[a]] = idx, r[a] = idx ++ ;
}

// 删除节点a
void remove(int a)
{
    l[r[a]] = l[a];
    r[l[a]] = r[a];
}
```

#### 栈 —— 模板题  828. 模拟栈

```c++
// tt表示栈顶
int stk[N], tt = 0;

// 向栈顶插入一个数
stk[ ++ tt] = x;

// 从栈顶弹出一个数
tt -- ;

// 栈顶的值
stk[tt];

// 判断栈是否为空
if (tt > 0)
{

}
```

#### 队列 —— 模板题 829. 模拟队列

1. 普通队列：

  ```c++
1. // hh 表示队头，tt表示队尾
   int q[N], hh = 0, tt = -1;

// 向队尾插入一个数
q[ ++ tt] = x;

// 从队头弹出一个数
hh ++ ;

// 队头的值
q[hh];

// 判断队列是否为空
if (hh <= tt)
{

}
  ```

  

2. 循环队列

  ```c+c+
2. // hh 表示队头，tt表示队尾的后一个位置
   int q[N], hh = 0, tt = 0;

// 向队尾插入一个数
q[tt ++ ] = x;
if (tt == N) tt = 0;

// 从队头弹出一个数
hh ++ ;
if (hh == N) hh = 0;

// 队头的值
q[hh];

// 判断队列是否为空
if (hh != tt)
{

}
  ```

  

#### 单调栈 —— 模板题 830. 单调栈

```c++
//常见模型：找出每个数左边离它最近的比它大/小的数
int tt = 0;
for (int i = 1; i <= n; i ++ )
{
    while (tt && check(stk[tt], i)) tt -- ;
    stk[ ++ tt] = i;
}
```

#### 单调队列 —— 模板题154. 滑动窗口

```c++
//常见模型：找出滑动窗口中的最大值/最小值
int hh = 0, tt = -1;
for (int i = 0; i < n; i ++ )
{
    while (hh <= tt && check_out(q[hh])) hh ++ ;  // 判断队头是否滑出窗口
    while (hh <= tt && check(q[tt], i)) tt -- ;
    q[ ++ tt] = i;
}
```

#### KMP —— 模板题 831. KMP字符串

```cc
// s[]是长文本，p[]是模式串，n是s的长度，m是p的长度
求模式串的Next数组：
for (int i = 2, j = 0; i <= m; i ++ )
{
    while (j && p[i] != p[j + 1]) j = ne[j];
    if (p[i] == p[j + 1]) j ++ ;
    ne[i] = j;
}

// 匹配
for (int i = 1, j = 0; i <= n; i ++ )
{
    while (j && s[i] != p[j + 1]) j = ne[j];
    if (s[i] == p[j + 1]) j ++ ;
    if (j == m)
    {
        j = ne[j];
        // 匹配成功后的逻辑
    }
}
```

#### Trie树 —— 模板题835. Trie字符串统计

```c++
int son[N][26], cnt[N], idx;
// 0号点既是根节点，又是空节点
// son[][]存储树中每个节点的子节点
// cnt[]存储以每个节点结尾的单词数量

// 插入一个字符串
void insert(char *str)
{
    int p = 0;
    for (int i = 0; str[i]; i ++ )
    {
        int u = str[i] - 'a';
        if (!son[p][u]) son[p][u] = ++ idx;
        p = son[p][u];
    }
    cnt[p] ++ ;
}

// 查询字符串出现的次数
int query(char *str)
{
    int p = 0;
    for (int i = 0; str[i]; i ++ )
    {
        int u = str[i] - 'a';
        if (!son[p][u]) return 0;
        p = son[p][u];
    }
    return cnt[p];
}
```

#### 并查集 —— 模板题 836. 合并集合， 837. 连通块中点的数量

(1)朴素并查集：c++

```c++
int p[N]; //存储每个点的祖宗节点

// 返回x的祖宗节点
int find(int x)
{
    if (p[x] != x) p[x] = find(p[x]);
    return p[x];
}

// 初始化，假定节点编号是1~n
for (int i = 1; i <= n; i ++ ) p[i] = i;

// 合并a和b所在的两个集合：
p[find(a)] = find(b);
```


(2)维护size的并查集：

```c++
int p[N], size[N];
//p[]存储每个点的祖宗节点, size[]只有祖宗节点的有意义，表示祖宗节点所在集合中的点的数量

// 返回x的祖宗节点
int find(int x)
{
    if (p[x] != x) p[x] = find(p[x]);
    return p[x];
}

// 初始化，假定节点编号是1~n
for (int i = 1; i <= n; i ++ )
{
    p[i] = i;
    size[i] = 1;
}

// 合并a和b所在的两个集合：
size[find(b)] += size[find(a)];
p[find(a)] = find(b);
```


(3)维护到祖宗节点距离的并查集：

```c++
int p[N], d[N];
//p[]存储每个点的祖宗节点, d[x]存储x到p[x]的距离

// 返回x的祖宗节点
int find(int x)
{
    if (p[x] != x)
    {
        int u = find(p[x]);
        d[x] += d[p[x]];
        p[x] = u;
    }
    return p[x];
}

// 初始化，假定节点编号是1~n
for (int i = 1; i <= n; i ++ )
{
    p[i] = i;
    d[i] = 0;
}

// 合并a和b所在的两个集合：
p[find(a)] = find(b);
d[find(a)] = distance; // 根据具体问题，初始化find(a)的偏移量
```

#### 堆 —— 模板题 838. 堆排序,  839. 模拟堆

```c++
// h[N]存储堆中的值, h[1]是堆顶，x的左儿子是2x, 右儿子是2x + 1
// ph[k]存储第k个插入的点在堆中的位置
// hp[k]存储堆中下标是k的点是第几个插入的
int h[N], ph[N], hp[N], size;

// 交换两个点，及其映射关系
void heap_swap(int a, int b)
{
    swap(ph[hp[a]],ph[hp[b]]);
    swap(hp[a], hp[b]);
    swap(h[a], h[b]);
}

void down(int u)
{
    int t = u;
    if (u * 2 <= size && h[u * 2] < h[t]) t = u * 2;
    if (u * 2 + 1 <= size && h[u * 2 + 1] < h[t]) t = u * 2 + 1;
    if (u != t)
    {
        heap_swap(u, t);
        down(t);
    }
}

void up(int u)
{
    while (u / 2 && h[u] < h[u / 2])
    {
        heap_swap(u, u / 2);
        u >>= 1;
    }
}

// O(n)建堆
for (int i = n / 2; i; i -- ) down(i);
```

#### 一般哈希 —— 模板题 840. 模拟散列表

(1) 拉链法

```c++
  int h[N], e[N], ne[N], idx;
// 向哈希表中插入一个数
void insert(int x)
{
    int k = (x % N + N) % N;
    e[idx] = x;
    ne[idx] = h[k];
    h[k] = idx ++ ;
}

// 在哈希表中查询某个数是否存在
bool find(int x)
{
    int k = (x % N + N) % N;
    for (int i = h[k]; i != -1; i = ne[i])
        if (e[i] == x)
            return true;

    return false;
}
```

(2) 开放寻址法c++

```c++
 int h[N];

// 如果x在哈希表中，返回x的下标；如果x不在哈希表中，返回x应该插入的位置
int find(int x)
{
    int t = (x % N + N) % N;
    while (h[t] != null && h[t] != x)
    {
        t ++ ;
        if (t == N) t = 0;
    }
    return t;c+
}
```

#### 字符串哈希 —— 模板题 841. 字符串哈希

核心思想：将字符串看成P进制数，P的经验值是131或13331，取这两个值的冲突概率低
小技巧：取模的数用2^64，这样直接用unsigned long long存储，溢出的结果就是取模的结果

```cc
typedef unsigned long long ULL;
ULL h[N], p[N]; // h[k]存储字符串前k个字母的哈希值, p[k]存储 P^k mod 2^64

// 初始化
p[0] = 1;
for (int i = 1; i <= n; i ++ )
{
    h[i] = h[i - 1] * P + str[i];
    p[i] = p[i - 1] * P;
}

// 计算子串 str[l ~ r] 的哈希值
ULL get(int l, int r)
{
    return h[r] - h[l - 1] * p[r - l + 1];
}
```

## C++ STL简介

```c++
vector, 变长数组，倍增的思想
    size()  返回元素个数
    empty()  返回是否为空
    clear()  清空
    front()/back()
    push_back()/pop_back()
    begin()/end()
    []
    支持比较运算，按字典序

pair<int, int>
    first, 第一个元素
    second, 第二个元素
    支持比较运算，以first为第一关键字，以second为第二关键字（字典序）

string，字符串
    size()/length()  返回字符串长度
    empty()
    clear()
    substr(起始下标，(子串长度))  返回子串
    c_str()  返回字符串所在字符数组的起始地址

queue, 队列
    size()
    empty()
    push()  向队尾插入一个元素
    front()  返回队头元素
    back()  返回队尾元素
    pop()  弹出队头元素

priority_queue, 优先队列，默认是大根堆
    size()
    empty()
    push()  插入一个元素
    top()  返回堆顶元素
    pop()  弹出堆顶元素
    定义成小根堆的方式：priority_queue<int, vector<int>, greater<int>> q;

stack, 栈
    size()
    empty()
    push()  向栈顶插入一个元素
    top()  返回栈顶元素
    pop()  弹出栈顶元素

deque, 双端队列
    size()
    empty()
    clear()
    front()/back()
    push_back()/pop_back()
    push_front()/pop_front()
    begin()/end()
    []

set, map, multiset, multimap, 基于平衡二叉树（红黑树），动态维护有序序列
    size()
    empty()
    clear()
    begin()/end()
    ++, -- 返回前驱和后继，时间复杂度 O(logn)

set/multiset
    insert()  插入一个数
    find()  查找一个数
    count()  返回某一个数的个数
    erase()
        (1) 输入是一个数x，删除所有x   O(k + logn)
        (2) 输入一个迭代器，删除这个迭代器
    lower_bound()/upper_bound()
        lower_bound(x)  返回大于等于x的最小的数的迭代器
        upper_bound(x)  返回大于x的最小的数的迭代器
map/multimap
    insert()  插入的数是一个pair
    erase()  输入的参数是pair或者迭代器
    find()
    []  注意multimap不支持此操作。 时间复杂度是 O(logn)
    lower_bound()/upper_bound()

unordered_set, unordered_map, unordered_multiset, unordered_multimap, 哈希表
    和上面类似，增删改查的时间复杂度是 O(1)
    不支持 lower_bound()/upper_bound()， 迭代器的++，--

bitset, 圧位
    bitset<10000> s;
    ~, &, |, ^
    >>, <<
    ==, !=
    []

count()  返回有多少个1

any()  判断是否至少有一个1
none()  判断是否全为0

set()  把所有位置成1
set(k, v)  将第k位变成v
reset()  把所有位变成0
flip()  等价于~
flip(k) 把第k位取反
```



## 搜索与图论

树与图的存储
树是一种特殊的图，与图的存储方式相同。
对于无向图中的边ab，存储两条有向边a->b, b->a。
因此我们可以只考虑有向图的存储。

(1) 邻接矩阵：g[a][b] 存储边a->b

(2) 邻接表：

```c++c++
// 对于每个点k，开一个单链表，存储k所有可以走到的点。h[k]存储这个单链表的头结点
int h[N], e[N], ne[N], idx;

// 添加一条边a->b
void add(int a, int b)
{
    e[idx] = b, ne[idx] = h[a], h[a] = idx ++ ;
}

// 初始化
idx = 0;
memset(h, -1, sizeof h);
```

树与图的遍历
时间复杂度 O(n+m)O(n+m), nn 表示点数，mm 表示边数

#### (1) 深度优先遍历 —— 模板题 846. 树的重心

```c++
int dfs(int u)
{
    st[u] = true; // st[u] 表示点u已经被遍历过

    for (int i = h[u]; i != -1; i = ne[i])
    {
        int j = e[i];
        if (!st[j]) dfs(j);
    }

}
```

#### (2) 宽度优先遍历 —— 模板题847. 图中点的层次

```c++
queue<int> q;c++
st[1] = true; // 表示1号点已经被遍历过
q.push(1);

while (q.size())
{
    int t = q.front();
    q.pop();

    for (int i = h[t]; i != -1; i = ne[i])
    {
        int j = e[i];
        if (!st[j])
        {
            st[j] = true; // 表示点j已经被遍历过
            q.push(j);
        }
    }

}
```

#### 拓扑排序 —— 模板题 848. 有向图的拓扑序列

时间复杂度 O(n+m)O(n+m), nn 表示点数，mm 表示边数c++

```c++
bool topsort()
{
    int hh = 0, tt = -1;

    // d[i] 存储点i的入度
    for (int i = 1; i <= n; i ++ )
        if (!d[i])
            q[ ++ tt] = i;
    
    while (hh <= tt)
    {
        int t = q[hh ++ ];
    
        for (int i = h[t]; i != -1; i = ne[i])
        {
            int j = e[i];
            if (-- d[j] == 0)
                q[ ++ tt] = j;
        }
    }
    
    // 如果所有点都入队了，说明存在拓扑序列；否则不存在拓扑序列。
    return tt == n - 1;

}
```

#### 朴素dijkstra算法 —— 模板题849. Dijkstra求最短路 I

时间复杂是 O(n2+m)O(n2+m), nn 表示点数，mm 表示边数

```c++
int g[N][N];  // 存储每条边c++
int dist[N];  // 存储1号点到每个点的最短距离
bool st[N];   // 存储每个点的最短路是否已经确定

// 求1号点到n号点的最短路，如果不存在则返回-1
int dijkstra()
{
    memset(dist, 0x3f, sizeof dist);
    dist[1] = 0;

    for (int i = 0; i < n - 1; i ++ )
    {
        int t = -1;     // 在还未确定最短路的点中，寻找距离最小的点
        for (int j = 1; j <= n; j ++ )
            if (!st[j] && (t == -1 || dist[t] > dist[j]))
                t = j;
    
        // 用t更新其他点的距离
        for (int j = 1; j <= n; j ++ )
            dist[j] = min(dist[j], dist[t] + g[t][j]);
    
        st[t] = true;
    }
    
    if (dist[n] == 0x3f3f3f3f) return -1;
    return dist[n];

}
```

#### 堆优化版dijkstra —— 模板题 850. Dijkstra求最短路 II

时间复杂度 O(mlogn)O(mlogn), nn 表示点数，mm 表示边数

```c++
typedef pair<int, int> PII;

int n;      // 点的数量
int h[N], w[N], e[N], ne[N], idx;       // 邻接表存储所有边
int dist[N];        // 存储所有点到1号点的距离
bool st[N];     // 存储每个点的最短距离是否已确定

// 求1号点到n号点的最短距离，如果不存在，则返回-1
int dijkstra()
{
    memset(dist, 0x3f, sizeof dist);
    dist[1] = 0;
    priority_queue<PII, vector<PII>, greater<PII>> heap;
    heap.push({0, 1});      // first存储距离，second存储节点编号

    while (heap.size())
    {
        auto t = heap.top();
        heap.pop();
    
        int ver = t.second, distance = t.first;
    
        if (st[ver]) continue;
        st[ver] = true;
    
        for (int i = h[ver]; i != -1; i = ne[i])
        {
            int j = e[i];
            if (dist[j] > distance + w[i])
            {
                dist[j] = distance + w[i];
                heap.push({dist[j], j});
            }
        }
    }
    
    if (dist[n] == 0x3f3f3f3f) return -1;
    return dist[n];

}
```

#### Bellman-Ford算法 —— 模板题  853. 有边数限制的最短路

时间复杂度 O(nm)O(nm), nn 表示点数，mm 表示边数
注意在模板题中需要对下面的模板稍作修改，加上备份数组，详情见模板题。

```c++
int n, m;       // n表示点数，m表示边数c++
int dist[N];        // dist[x]存储1到x的最短路距离

struct Edge     // 边，a表示出点，b表示入点，w表示边的权重
{
    int a, b, w;
}edges[M];

// 求1到n的最短路距离，如果无法从1走到n，则返回-1。
int bellman_ford()
{
    memset(dist, 0x3f, sizeof dist);
    dist[1] = 0;
c++
    // 如果第n次迭代仍然会松弛三角不等式，就说明存在一条长度是n+1的最短路径，由抽屉原理，路径中至少存在两个相同的点，说明图中存在负权回路。
    for (int i = 0; i < n; i ++ )
    {
        for (int j = 0; j < m; j ++ )
        {
            int a = edges[j].a, b = edges[j].b, w = edges[j].w;
            if (dist[b] > dist[a] + w)
                dist[b] = dist[a] + w;
        }
    }
    
    if (dist[n] > 0x3f3f3f3f / 2) return -1;
    return dist[n];

}
```

#### spfa 算法（队列优化的Bellman-Ford算法） —— 模板题 851. spfa求最短路

时间复杂度 平均情况下 O(m)O(m)，最坏情况下 O(nm)O(nm), nn 表示点数，mm 表示边数

```c++
int n;      // 总点数
int h[N], w[N], e[N], ne[N], idx;       // 邻接表存储所有边
int dist[N];        // 存储每个点到1号点的最短距离
bool st[N];     // 存储每个点是否在队列中

// 求1号点到n号点的最短路距离，如果从1号点无法走到n号点则返回-1
int spfa()
{
    memset(dist, 0x3f, sizeof dist);
    dist[1] = 0;

    queue<int> q;
    q.push(1);
    st[1] = true;
    
    while (q.size())
    {
        auto t = q.front();
        q.pop();
    
        st[t] = false;
    
        for (int i = h[t]; i != -1; i = ne[i])
        {
            int j = e[i];
            if (dist[j] > dist[t] + w[i])
            {
                dist[j] = dist[t] + w[i];
                if (!st[j])     // 如果队列中已存在j，则不需要将j重复插入
                {
                    q.push(j);
                    st[j] = true;
                }
            }
        }
    }
    
    if (dist[n] == 0x3f3f3f3f) return -1;
    return dist[n];

}
```

#### spfa判断图中是否存在负环 —— 模板题 AcWing 852. spfa判断负环

时间复杂度是 O(nm)O(nm), nn 表示点数，mm 表示边数

```c++
int n;      // 总点数
int h[N], w[N], e[N], ne[N], idx;       // 邻接表存储所有边
int dist[N], cnt[N];        // dist[x]存储1号点到x的最短距离，cnt[x]存储1到x的最短路中经过的点数
bool st[N];     // 存储每个点是否在队列中

// 如果存在负环，则返回true，否则返回false。
bool spfa()
{
    // 不需要初始化dist数组
    // 原理：如果某条最短路径上有n个点（除了自己），那么加上自己之后一共有n+1个点，由抽屉原理一定有两个点相同，所以存在环。

    queue<int> q;
    for (int i = 1; i <= n; i ++ )
    {
        q.push(i);
        st[i] = true;
    }
    
    while (q.size())
    {
        auto t = q.front();
        q.pop();
    
        st[t] = false;
    
        for (int i = h[t]; i != -1; i = ne[i])
        {
            int j = e[i];
            if (dist[j] > dist[t] + w[i])
            {
                dist[j] = dist[t] + w[i];
                cnt[j] = cnt[t] + 1;
                if (cnt[j] >= n) return true;       // 如果从1号点到x的最短路中包含至少n个点（不包括自己），则说明存在环
                if (!st[j])
                {
                    q.push(j);
                    st[j] = true;
                }
            }
        }
    }
    
    return false;

}
```

#### floyd算法 —— 模板题 854. Floyd求最短路

时间复杂度是 O(n3)O(n3), nn 表示点数

```c++
初始化：
    for (int i = 1; i <= n; i ++ )
        for (int j = 1; j <= n; j ++ )
            if (i == j) d[i][j] = 0;
            else d[i][j] = INF;

// 算法结束后，d[a][b]表示a到b的最短距离
void floyd()
{
    for (int k = 1; k <= n; k ++ )
        for (int i = 1; i <= n; i ++ )
            for (int j = 1; j <= n; j ++ )
                d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
}
```

#### 朴素版prim算法 —— 模板题 858. Prim算法求最小生成树

时间复杂度是 O(n2+m)O(n2+m), nn 表示点数，mm 表示边数

```c++
int n;      // n表示点数
int g[N][N];        // 邻接矩阵，存储所有边
int dist[N];        // 存储其他点到当前最小生成树的距离
bool st[N];     // 存储每个点是否已经在生成树中


// 如果图不连通，则返回INF(值是0x3f3f3f3f), 否则返回最小生成树的树边权重之和
int prim()
{
    memset(dist, 0x3f, sizeof dist);

    int res = 0;
    for (int i = 0; i < n; i ++ )
    {
        int t = -1;
        for (int j = 1; j <= n; j ++ )
            if (!st[j] && (t == -1 || dist[t] > dist[j]))
                t = j;
    
        if (i && dist[t] == INF) return INF;
    
        if (i) res += dist[t];
        st[t] = true;
    
        for (int j = 1; j <= n; j ++ ) dist[j] = min(dist[j], g[t][j]);
    }
    
    return res;

}
```

#### Kruskal算法 —— 模板题 859. Kruskal算法求最小生成树

时间复杂度是 O(mlogm)O(mlogm), nn 表示点数，mm 表示边数

```c++
int n, m;       // n是点数，m是边数
int p[N];       // 并查集的父节点数组

struct Edge     // 存储边
{
    int a, b, w;

    bool operator< (const Edge &W)const
    {
        return w < W.w;
    }

}edges[M];

int find(int x)     // 并查集核心操作
{
    if (p[x] != x) p[x] = find(p[x]);
    return p[x];
}

int kruskal()
{
    sort(edges, edges + m);

    for (int i = 1; i <= n; i ++ ) p[i] = i;    // 初始化并查集
    
    int res = 0, cnt = 0;
    for (int i = 0; i < m; i ++ )
    {
        int a = edges[i].a, b = edges[i].b, w = edges[i].w;
    
        a = find(a), b = find(b);
        if (a != b)     // 如果两个连通块不连通，则将这两个连通块合并
        {
            p[a] = b;
            res += w;
            cnt ++ ;
        }
    }
    
    if (cnt < n - 1) return INF;
    return res;

}
```

#### 染色法判别二分图 —— 模板题 860. 染色法判定二分图

时间复杂度是 O(n+m)O(n+m), nn 表示点数，mm 表示边数

```c++
int n;      // n表示点数
int h[N], e[M], ne[M], idx;     // 邻接表存储图
int color[N];       // 表示每个点的颜色，-1表示未染色，0表示白色，1表示黑色

// 参数：u表示当前节点，c表示当前点的颜色
bool dfs(int u, int c)
{
    color[u] = c;
    for (int i = h[u]; i != -1; i = ne[i])
    {
        int j = e[i];
        if (color[j] == -1)
        {
            if (!dfs(j, !c)) return false;
        }
        else if (color[j] == c) return false;
    }

    return true;

}

bool check()
{
    memset(color, -1, sizeof color);
    bool flag = true;
    for (int i = 1; i <= n; i ++ )
        if (color[i] == -1)
            if (!dfs(i, 0))
            {
                flag = false;
                break;
            }
    return flag;
}
```

#### 匈牙利算法 —— 模板题 861. 二分图的最大匹配

时间复杂度是 O(nm)O(nm), nn 表示点数，mm 表示边数

```c++
int n1, n2;     // n1表示第一个集合中的点数，n2表示第二个集合中的点数
int h[N], e[M], ne[M], idx;     // 邻接表存储所有边，匈牙利算法中只会用到从第一个集合指向第二个集合的边，所以这里只用存一个方向的边
int match[N];       // 存储第二个集合中的每个点当前匹配的第一个集合中的点是哪个
bool st[N];     // 表示第二个集合中的每个点是否已经被遍历过

bool find(int x)
{
    for (int i = h[x]; i != -1; i = ne[i])
    {
        int j = e[i];
        if (!st[j])
        {
            st[j] = true;
            if (match[j] == 0 || find(match[j]))
            {
                match[j] = x;
                return true;
            }
        }
    }

    return false;

}

// 求最大匹配数，依次枚举第一个集合中的每个点能否匹配第二个集合中的点
int res = 0;
for (int i = 1; i <= n1; i ++ )
{
    memset(st, false, sizeof st);
    if (find(i)) res ++ ;
}
```



## 数学

#### 试除法判定质数 —— 模板题 866. 试除法判定质数

```c++
bool is_prime(int x)
{
    if (x < 2) return false;
    for (int i = 2; i <= x / i; i ++ )
        if (x % i == 0)
            return false;
    return true;
}
```

#### 试除法分解质因数 —— 模板题 867. 分解质因数

```C++
void divide(int x)
{
    for (int i = 2; i <= x / i; i ++ )
        if (x % i == 0)
        {
            int s = 0;
            while (x % i == 0) x /= i, s ++ ;
            cout << i << ' ' << s << endl;
        }
    if (x > 1) cout << x << ' ' << 1 << endl;
    cout << endl;
}
```

#### 朴素筛法求素数 —— 模板题  868. 筛质数

```c++
int primes[N], cnt;     // primes[]存储所有素数
bool st[N];         // st[x]存储x是否被筛掉

void get_primes(int n)
{
    for (int i = 2; i <= n; i ++ )
    {
        if (st[i]) continue;
        primes[cnt ++ ] = i;
        for (int j = i + i; j <= n; j += i)
            st[j] = true;
    }
}
```

#### 线性筛法求素数 —— 模板题 868. 筛质数

```
int primes[N], cnt;     // primes[]存储所有素数
bool st[N];         // st[x]存储x是否被筛掉

void get_primes(int n)
{
    for (int i = 2; i <= n; i ++ )
    {
        if (!st[i]) primes[cnt ++ ] = i;
        for (int j = 0; primes[j] <= n / i; j ++ )
        {
            st[primes[j] * i] = true;
            if (i % primes[j] == 0) break;
        }
    }
}
```

#### 试除法求所有约数 —— 模板题 869. 试除法求约数

```
vector<int> get_divisors(int x)
{
    vector<int> res;
    for (int i = 1; i <= x / i; i ++ )
        if (x % i == 0)
        {
            res.push_back(i);
            if (i != x / i) res.push_back(x / i);
        }
    sort(res.begin(), res.end());
    return res;
}
```

#### 约数个数和约数之和 —— 模板题 870. 约数个数, AcWing 871. 约数之和

如果 N = p1^c1 * p2^c2 * ... *pk^ck
约数个数： (c1 + 1) * (c2 + 1) * ... * (ck + 1)
约数之和： (p1^0 + p1^1 + ... + p1^c1) * ... * (pk^0 + pk^1 + ... + pk^ck)

约数个数

```java
//给定 n 个正整数 ai，请你输出这些数的乘积的约数个数，答案对 109+7 取模
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static final int mod = (int) 1e9 + 7;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        HashMap<Integer, Integer> primes = new HashMap<>();
        while (n -- != 0) {
            int x = sc.nextInt();
            for (int i = 2; i <= x / i; i ++) {
                int cur = 0;
                while (x % i == 0) {
                    x /= i;
                    cur ++;
                }
                primes.put(i, primes.getOrDefault(i, 0) + cur);
            }
            if (x > 1) primes.put(x, primes.getOrDefault(x, 0) + 1);
        }
        Long res = 1L;
        for(Integer cur: primes.keySet()) res = res*(primes.get(cur)+1) % mod;
        System.out.println(res);
    }
}
```

约数之和

```java
//给定 n 个正整数 ai，请你输出这些数的乘积的约数之和，答案对 109+7 取模。
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static final int mod = (int) 1e9 + 7;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        HashMap<Integer, Integer> primes = new HashMap<>();
        while (n -- != 0) {
            int x = sc.nextInt();
            for (int i = 2; i <= x / i; i ++) {
                int cur = 0;
                while (x % i == 0) {
                    x /= i;
                    cur ++;
                }
                primes.put(i, primes.getOrDefault(i, 0) + cur);
            }
            if (x > 1) primes.put(x, primes.getOrDefault(x, 0) + 1);
        }
        long res = 1;
        for(Integer cur: primes.keySet()){
            long t = 1;
            for(int i = 0; i < primes.get(cur); i++){
                t = (t * cur + 1) % mod;
            }
            res = (res * t) % mod;
        }
        System.out.println(res);
    }
}
```



#### 欧几里得算法 —— 模板题 872. 最大公约数

```c++
int gcd(int a, int b)
{
    return b ? gcd(b, a % b) : a;
}
```

#### 求欧拉函数 —— 模板题873. 欧拉函数

```c++
int phi(int x)
{
    int res = x;
    for (int i = 2; i <= x / i; i ++ )
        if (x % i == 0)
        {
            res = res / i * (i - 1);
            while (x % i == 0) x /= i;
        }
    if (x > 1) res = res / x * (x - 1);

    return res;

}
```

#### 筛法求欧拉函数 —— 模板题 874. 筛法求欧拉函数

```c++
int primes[N], cnt;     // primes[]存储所有素数
int euler[N];           // 存储每个数的欧拉函数
bool st[N];         // st[x]存储x是否被筛掉


void get_eulers(int n)
{
    euler[1] = 1;
    for (int i = 2; i <= n; i ++ )
    {
        if (!st[i])
        {
            primes[cnt ++ ] = i;
            euler[i] = i - 1;
        }
        for (int j = 0; primes[j] <= n / i; j ++ )
        {
            int t = primes[j] * i;
            st[t] = true;
            if (i % primes[j] == 0)
            {
                euler[t] = euler[i] * primes[j];
                break;
            }
            euler[t] = euler[i] * (primes[j] - 1);
        }
    }
}
```

#### 快速幂 —— 模板题 875. 快速幂

求 m^k mod p，时间复杂度 O(logk)。

```c++
int qmi(int m, int k, int p)
{
    int res = 1 % p, t = m;
    while (k)
    {
        if (k&1) res = res * t % p;
        t = t * t % p;
        k >>= 1;
    }
    return res;
}
```

#### 扩展欧几里得算法 —— 模板题  877. 扩展欧几里得算法

```c++
// 求x, y，使得ax + by = gcd(a, b)
int exgcd(int a, int b, int &x, int &y)
{
    if (!b)
    {
        x = 1; y = 0;
        return a;
    }
    int d = exgcd(b, a % b, y, x);
    y -= (a/b) * x;
    return d;
}
```

#### 高斯消元 —— 模板题  883. 高斯消元解线性方程组

```c++
// a[N][N]是增广矩阵
int gauss()
{
    int c, r;
    for (c = 0, r = 0; c < n; c ++ )
    {
        int t = r;
        for (int i = r; i < n; i ++ )   // 找到绝对值最大的行
            if (fabs(a[i][c]) > fabs(a[t][c]))
                t = i;

        if (fabs(a[t][c]) < eps) continue;
    
        for (int i = c; i <= n; i ++ ) swap(a[t][i], a[r][i]);      // 将绝对值最大的行换到最顶端
        for (int i = n; i >= c; i -- ) a[r][i] /= a[r][c];      // 将当前行的首位变成1
        for (int i = r + 1; i < n; i ++ )       // 用当前行将下面所有的列消成0
            if (fabs(a[i][c]) > eps)
                for (int j = n; j >= c; j -- )
                    a[i][j] -= a[r][j] * a[i][c];
    
        r ++ ;
    }
    
    if (r < n)
    {
        for (int i = r; i < n; i ++ )
            if (fabs(a[i][n]) > eps)
                return 2; // 无解
        return 1; // 有无穷多组解
    }
    
    for (int i = n - 1; i >= 0; i -- )
        for (int j = i + 1; j < n; j ++ )
            a[i][n] -= a[i][j] * a[j][n];
    
    return 0; // 有唯一解

}

```

#### 递推法求组合数 —— 模板题  885. 求组合数 I

```
// c[a][b] 表示从a个苹果中选b个的方案数
for (int i = 0; i < N; i ++ )
    for (int j = 0; j <= i; j ++ )
        if (!j) c[i][j] = 1;
        else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
```

#### 通过预处理逆元的方式求组合数 —— 模板题 886. 求组合数 II

```c++
首先预处理出所有阶乘取模的余数fact[N]，以及所有阶乘取模的逆元infact[N]
如果取模的数是质数，可以用费马小定理求逆元
int qmi(int a, int k, int p)    // 快速幂模板
{
    int res = 1;
    while (k)
    {
        if (k & 1) res = (LL)res * a % p;
        a = (LL)a * a % p;
        k >>= 1;
    }
    return res;
}

// 预处理阶乘的余数和阶乘逆元的余数
fact[0] = infact[0] = 1;
for (int i = 1; i < N; i ++ )
{
    fact[i] = (LL)fact[i - 1] * i % mod;
    infact[i] = (LL)infact[i - 1] * qmi(i, mod - 2, mod) % mod;
}
```



#### Lucas定理 —— 模板题 887. 求组合数 III

```c++
若p是质数，则对于任意整数 1 <= m <= n，有：
    C(n, m) = C(n % p, m % p) * C(n / p, m / p) (mod p)

int qmi(int a, int k, int p)  // 快速幂模板
{
    int res = 1 % p;
    while (k)
    {
        if (k & 1) res = (LL)res * a % p;
        a = (LL)a * a % p;
        k >>= 1;
    }
    return res;
}

int C(int a, int b, int p)  // 通过定理求组合数C(a, b)
{
    if (a < b) return 0;

    LL x = 1, y = 1;  // x是分子，y是分母
    for (int i = a, j = 1; j <= b; i --, j ++ )
    {
        x = (LL)x * i % p;
        y = (LL) y * j % p;
    }
    
    return x * (LL)qmi(y, p - 2, p) % p;

}

int lucas(LL a, LL b, int p)
{
    if (a < p && b < p) return C(a, b, p);
    return (LL)C(a % p, b % p, p) * lucas(a / p, b / p, p) % p;
}
```

#### 分解质因数法求组合数 —— 模板题  888. 求组合数 IV

当我们需要求出组合数的真实值，而非对某个数的余数时，分解质因数的方式比较好用：
    1. 筛法求出范围内的所有质数
    2. 通过 C(a, b) = a! / b! / (a - b)! 这个公式求出每个质因子的次数。 n! 中p的次数是 n / p + n / p^2 + n / p^3 + ...
    3. 用高精度乘法将所有质因子相乘

```c++
int primes[N], cnt;     // 存储所有质数
int sum[N];     // 存储每个质数的次数
bool st[N];     // 存储每个数是否已被筛掉


void get_primes(int n)      // 线性筛法求素数
{
    for (int i = 2; i <= n; i ++ )
    {
        if (!st[i]) primes[cnt ++ ] = i;
        for (int j = 0; primes[j] <= n / i; j ++ )
        {
            st[primes[j] * i] = true;
            if (i % primes[j] == 0) break;
        }
    }
}


int get(int n, int p)       // 求n！中的次数
{
    int res = 0;
    while (n)
    {
        res += n / p;
        n /= p;
    }
    return res;
}


vector<int> mul(vector<int> a, int b)       // 高精度乘低精度模板
{
    vector<int> c;
    int t = 0;
    for (int i = 0; i < a.size(); i ++ )
    {
        t += a[i] * b;
        c.push_back(t % 10);
        t /= 10;
    }

    while (t)
    {
        c.push_back(t % 10);
        t /= 10;
    }
    
    return c;

}

get_primes(a);  // 预处理范围内的所有质数

for (int i = 0; i < cnt; i ++ )     // 求每个质因数的次数
{
    int p = primes[i];
    sum[i] = get(a, p) - get(b, p) - get(a - b, p);
}

vector<int> res;
res.push_back(1);

for (int i = 0; i < cnt; i ++ )     // 用高精度乘法将所有质因子相乘
    for (int j = 0; j < sum[i]; j ++ )
        res = mul(res, primes[i]);
```

#### 卡特兰数 —— 模板题 889. 满足条件的01序列

给定n个0和n个1，它们按照某种顺序排成长度为2n的序列，满足任意前缀中0的个数都不少于1的个数的序列的数量为： Cat(n) = C(2n, n) / (n + 1)
NIM游戏 —— 模板题 AcWing 891. Nim游戏
给定N堆物品，第i堆物品有Ai个。两名玩家轮流行动，每次可以任选一堆，取走任意多个物品，可把一堆取光，但不能不取。取走最后一件物品者获胜。两人都采取最优策略，问先手是否必胜。

我们把这种游戏称为NIM博弈。把游戏过程中面临的状态称为局面。整局游戏第一个行动的称为先手，第二个行动的称为后手。若在某一局面下无论采取何种行动，都会输掉游戏，则称该局面必败。
所谓采取最优策略是指，若在某一局面下存在某种行动，使得行动后对面面临必败局面，则优先采取该行动。同时，这样的局面被称为必胜。我们讨论的博弈问题一般都只考虑理想情况，即两人均无失误，都采取最优策略行动时游戏的结果。
NIM博弈不存在平局，只有先手必胜和先手必败两种情况。

定理： NIM博弈先手必胜，当且仅当 A1 ^ A2 ^ … ^ An != 0

#### 公平组合游戏ICG

若一个游戏满足：

由两名玩家交替行动；
在游戏进程的任意时刻，可以执行的合法行动与轮到哪名玩家无关；
不能行动的玩家判负；
则称该游戏为一个公平组合游戏。
NIM博弈属于公平组合游戏，但城建的棋类游戏，比如围棋，就不是公平组合游戏。因为围棋交战双方分别只能落黑子和白子，胜负判定也比较复杂，不满足条件2和条件3。

#### 有向图游戏

给定一个有向无环图，图中有一个唯一的起点，在起点上放有一枚棋子。两名玩家交替地把这枚棋子沿有向边进行移动，每次可以移动一步，无法移动者判负。该游戏被称为有向图游戏。
任何一个公平组合游戏都可以转化为有向图游戏。具体方法是，把每个局面看成图中的一个节点，并且从每个局面向沿着合法行动能够到达的下一个局面连有向边。

#### Mex运算

设S表示一个非负整数集合。定义mex(S)为求出不属于集合S的最小非负整数的运算，即：
mex(S) = min{x}, x属于自然数，且x不属于S

#### SG函数

在有向图游戏中，对于每个节点x，设从x出发共有k条有向边，分别到达节点y1, y2, …, yk，定义SG(x)为x的后继节点y1, y2, …, yk 的SG函数值构成的集合再执行mex(S)运算的结果，即：
SG(x) = mex({SG(y1), SG(y2), …, SG(yk)})
特别地，整个有向图游戏G的SG函数值被定义为有向图游戏起点s的SG函数值，即SG(G) = SG(s)。

#### 有向图游戏的和 —— 模板题 893. 集合-Nim游戏

设G1, G2, …, Gm 是m个有向图游戏。定义有向图游戏G，它的行动规则是任选某个有向图游戏Gi，并在Gi上行动一步。G被称为有向图游戏G1, G2, …, Gm的和。
有向图游戏的和的SG函数值等于它包含的各个子游戏SG函数值的异或和，即：
SG(G) = SG(G1) ^ SG(G2) ^ … ^ SG(Gm)

定理
有向图游戏的某个局面必胜，当且仅当该局面对应节点的SG函数值大于0。
有向图游戏的某个局面必败，当且仅当该局面对应节点的SG函数值等于0。
