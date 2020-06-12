//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划


package algorithm10.week01;
//java:爬楼梯

public class P70ClimbingStairs {
    public static void main(String[]rgs) { 
        Solution solution = new P70ClimbingStairs().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int climbStairs(int n) {
            int a = 1, b = 1;
            while (n-- > 0) {
                a = (b += a) - a;
            }
            return a;
        }

        public int climbStairs2(int n ) {
            if (n <= 2) {
                return n;
            }
            int result = 0;
            int first = 1,second = 2;
            for (int i = 3; i <= n; i++) {
                result = first + second;
                first = second;
                second = result;
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
