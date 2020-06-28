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

package algorithm10.week03;

public class P70ClimbingStairs {

    public static void main(String[] args) {
        Solution solution = new P70ClimbingStairs().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //更简洁
        public int climbStairs(int n) {
            if (n < 3) {
                return n;
            }
            int a = 1,b =2;
            for (int i = 2; i < n; i++) {
                a = (b += a) - a;
            }
            return b;
        }
        public int climbStairs2(int n) {
            if (n < 3) {
                return n;
            }
            int temp = 0;
            int a = 1, b = 2;
            for (int i = 2; i < n; i++) {
                temp = b;
                b = b + a;
                a = temp;
            }
            return b;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

