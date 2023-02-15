### 剑指 Offer

##### 找出数组中重复的数字

给定一个长度为 n 的整数数组 `nums`，数组中所有的数字都在 0∼n−1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。

请找出数组中任意一个重复的数字。

**注意**：如果某些数字不在 0∼n−10∼n−1 的范围内，或数组中不包含重复数字，则返回 -1；

```java
class Solution {
    public int duplicateInArray(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i] > arr.length - 1 || arr[i] < 0) {
                return -1;
            }
        }
        for (int i = 0; i < arr.length; i ++) {
            while (arr[i] != arr[arr[i]]) {
                int temp = arr[arr[i]];
                arr[arr[i]] = arr[i];
                arr[i] = temp;
            }
            if (arr[i] != i) return arr[i];
        }
        return -1;
    }
}
/*
首先遍历一遍数组，如果存在某个数不在0到n-1的范围内，则返回-1。
下面的算法的主要思想是把每个数放到对应的位置上，即让 nums[i] = i。

从前往后遍历数组中的所有数，假设当前遍历到的数是 nums[i]=x，那么：

1.如果x != i && nums[x] == x，则说明 x 出现了多次，直接返回 x 即可；
2.如果nums[x] != x，那我们就把 xx 交换到正确的位置上，即 swap(nums[x], nums[i])，交换完之后如果nums[i] != i，则重复进行该操作。由于每次交换都会将一个数放在正确的位置上，所以swap操作最多会进行 n 次，不会发生死循环。
循环结束后，如果没有找到任何重复的数，则返回-1。
*/
```

##### 不修改数组找出重复的数字

给定一个长度为 n+1 的数组`nums`，数组中所有的数均在 1∼n 的范围内，其中 n ≥ 1 。

请找出数组中任意一个重复的数，但不能修改输入的数组。

```java
class Solution {
    public int duplicateInArray(int[] nums) {
        int l = 1, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            int s = 0;
            for (int x : nums) {
                if (x >= l && x <= mid) {
                    s ++;
                }
            }
            if (s > mid - l + 1) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
/*
(分治，抽屉原理) O(nlogn)O(nlogn)
这道题目主要应用了抽屉原理和分治的思想。
抽屉原理：n+1 个苹果放在 n 个抽屉里，那么至少有一个抽屉中会放两个苹果。

用在这个题目中就是，一共有 n+1 个数，每个数的取值范围是1到n，所以至少会有一个数出现两次。
然后我们采用分治的思想，将每个数的取值的区间[1, n]划分成[1, n/2]和[n/2+1, n]两个子区间，然后分别统计两个区间中数的个数。
注意这里的区间是指 数的取值范围，而不是 数组下标。

划分之后，左右两个区间里一定至少存在一个区间，区间中数的个数大于区间长度。
这个可以用反证法来说明：如果两个区间中数的个数都小于等于区间长度，那么整个区间中数的个数就小于等于n，和有n+1个数矛盾。

因此我们可以把问题划归到左右两个子区间中的一个，而且由于区间中数的个数大于区间长度，根据抽屉原理，在这个子区间中一定存在某个数出现了两次。

依次类推，每次我们可以把区间长度缩小一半，直到区间长度为1时，我们就找到了答案。

复杂度分析
时间复杂度：每次会将区间长度缩小一半，一共会缩小 O(logn) 次。每次统计两个子区间中的数时需要遍历整个数组，时间复杂度是 O(n)。所以总时间复杂度是 O(nlogn)。
空间复杂度：代码中没有用到额外的数组，所以额外的空间复杂度是 O(1)。
*/
```

##### 二维数组中的查找

在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

```java
class Solution {
    public boolean searchArray(int[][] array, int target) {
        int x = 0, y = array[0].length - 1;
        while (x < array[0].length && y >= 0) {
            if (array[x][y] == target) return true;
            if (array[x][y] > target) {
                y --;
            } else {
                x ++;
            }
        }
        return false;
    }
}
/*
如下图所示，x左边的数都小于等于x，x下边的数都大于等于x
...x
....
....
因此我们可以从整个矩阵的右上角开始枚举，假设当前枚举的数是 xx：

如果 x 等于target，则说明我们找到了目标值，返回true；
如果 x 小于target，则 x 左边的数一定都小于target，我们可以直接排除当前一整行的数；
如果 x 大于target，则 x 下边的数一定都大于target，我们可以直接排序当前一整列的数；
排除一整行就是让枚举的点的横坐标加一，排除一整列就是让纵坐标减一。
当我们排除完整个矩阵后仍没有找到目标值时，就说明目标值不存在，返回false
*/  
```

##### 替换空格

请实现一个函数，把字符串中的每个空格替换成`"%20"`



##### 斐波那契数列

输入一个整数 n ，求斐波那契数列的第 n 项。

假定从 0 开始，第 0 项为 0。

```java
class Solution {
    public int Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
}
class Solution {
    public int Fibonacci(int n) {
        int a = 0, b = 1;
        while (n -- != 0) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return a;
    }
}
/*
用两个变量滚动式得往后计算，aa 表示第 n−1n−1 项，bb 表示第 nn 项。
则令 c=a+bc=a+b 表示第 n+1n+1 项，然后让 a,ba,b 顺次往后移一位。
*/
```

