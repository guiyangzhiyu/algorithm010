//在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。 
//
// 示例: 
//
// 输入: 
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//输出: 4 
// Related Topics 动态规划

package algorithm10.week06;

public class P221MaximalSquare {

    public static void main(String[] args) {
        Solution solution = new P221MaximalSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalSquare(char[][] matrix) {
            int resultSlide = 0;
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return resultSlide;
            }
            int row = matrix.length, col = matrix[0].length;
            //dp[i][j]表示以点（i,j）为右下角的最大的正方形的边长
            int[][] dp = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == '1') {
                        //点在上边界或者左边界
                        if (i == 0 || j == 0) {
                            dp[i][j] = 1;
                        } else {
                            dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                        }
                        resultSlide = Math.max(resultSlide, dp[i][j]);
                    }
                }
            }
            return resultSlide * resultSlide;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

