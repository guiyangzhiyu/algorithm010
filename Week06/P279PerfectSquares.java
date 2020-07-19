//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 示例 1: 
//
// 输入: n = 12
//输出: 3 
//解释: 12 = 4 + 4 + 4. 
//
// 示例 2: 
//
// 输入: n = 13
//输出: 2
//解释: 13 = 4 + 9. 
// Related Topics 广度优先搜索 数学 动态规划

package algorithm10.week06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P279PerfectSquares {

    public static void main(String[] args) {
        Solution solution = new P279PerfectSquares().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方法一、动态规划：类似于零钱兑换问题
        public int numSquares2(int n) {
            int squareLength = (int) (Math.sqrt(n)) + 1;
            //可能符合条件的平方数
            int[] squareNums = new int[squareLength];
            for (int i = 1; i <= squareLength; i++) {
                squareNums[i - 1] = i * i;
            }
            //dp[i]表示i可由平方数组成的最小个数
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int value : squareNums) {
                    if (value > i) {
                        break;
                    }
                    dp[i] = Math.min(dp[i], dp[i - value] + 1);
                }
            }
            return dp[n];
        }

        Set<Integer> squareSet = new HashSet<>();

        //方法二、贪心
        public int numSquares(int n) {
            int squareLength = (int) (Math.sqrt(n)) + 1;
            //可能符合条件的平方数
            int[] squareNums = new int[squareLength];
            for (int i = 1; i <= squareLength; i++) {
                squareSet.add(i * i);
            }
            int result = 1;
            while (result <= n) {
                if (is_valid(n, result)) {
                    return result;
                }
                result++;
            }
            return result;
        }

        /**
         * n是否能够被result个平方数组合
         *
         * @param n
         * @param result
         * @return
         */
        public boolean is_valid(int n, int result) {
            if (result == 1) {
                return squareSet.contains(n);
            }
            for (Integer temp : squareSet) {
                if (is_valid(n - temp, result - 1)) {
                    return true;
                }
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}

