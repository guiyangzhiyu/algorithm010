//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集

package algorithm10.week04;

public class P200NumberOfIslands {

    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            int result = 0;
            if (grid == null || grid.length == 0) {
                return result;
            }
            int n = grid.length;
            int m = grid[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        //清除与grid[i][j]相连的岛屿
                        ++result;
                        hepler(grid, i, j, n, m);
                    }
                }
            }
            return result;
        }

        private void hepler(char[][] grid, int i, int j, int n, int m) {
            grid[i][j] = '0';
            if (i > 0 && grid[i - 1][j] == '1') {
                hepler(grid, i - 1, j, n, m);
            }
            if (j > 0 && grid[i][j - 1] == '1') {
                hepler(grid, i, j - 1, n, m);
            }
            if (i < n - 1 && grid[i + 1][j] == '1') {
                hepler(grid, i + 1, j, n, m);
            }
            if (j < m - 1 && grid[i][j + 1] == '1') {
                hepler(grid, i, j + 1, n, m);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

