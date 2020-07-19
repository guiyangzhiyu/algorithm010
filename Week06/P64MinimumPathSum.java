//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 示例: 
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
// 
// Related Topics 数组 动态规划

package algorithm10.week06;

public class P64MinimumPathSum {

    public static void main(String[] args) {
        Solution solution = new P64MinimumPathSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length, n = grid[0].length;
            //dp[i][j]表示从点（i,j）到右下角的最小路径
            //dp[m - 1][n - 1] = grid[m - 1][n - 2]
            //dp[i][n - 1] += dp[i + 1][n - 1]
            //dp[m - 1][j] += dp[m - 1][j + 1]
            //dp[i][j] = Math.min(dp[i + 1][j],dp[i][j + 1]);
            //TODO 可进行空间优化
            int[][] dp = new int[m][n];
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (i == m - 1 && j == n - 1) {
                        dp[i][j] = grid[i][j];
                    } else if (i == m - 1) {
                        dp[i][j] += dp[i][j + 1] + grid[i][j];
                    } else if (j == n - 1) {
                        dp[i][j] += dp[i + 1][j] + grid[i][j];
                    } else {
                        dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
                    }
                }
            }
            return dp[0][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

