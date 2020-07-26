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

package algorithm10.week07;

public class P200NumberOfIslands {

    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        char[][] input = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(solution.numIslands(input));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方法一、使用dfs
        public int numIslands1(char[][] grid) {
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

        int row, col;

        //方法二、使用并查集
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            row = grid.length;
            col = grid[0].length;
            //建立一个虚拟节点row*col与所有的水域连通
            UnionFind unionFind = new UnionFind(row * col + 1);
            int visualNode = row * col;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        //只需要处理右下即可
                        if (j + 1 < col && grid[i][j + 1] == '1') {
                            unionFind.union(landIndex(i, j), landIndex(i, j + 1));
                        }
                        if (i + 1 < row && grid[i + 1][j] == '1') {
                            unionFind.union(landIndex(i, j), landIndex(i + 1, j));
                        }
//                        if (i > 0 && grid[i - 1][j] == '1') {
//                            unionFind.union(landIndex(i, j), landIndex(i - 1, j));
//                        }
//                        if (j > 0 && grid[i][j - 1] == '1') {
//                            unionFind.union(landIndex(i, j), landIndex(i, j - 1));
//                        }
                    } else {
                        unionFind.union(visualNode, landIndex(i, j));
                    }
                }
            }
            //减去虚拟并查集
            return unionFind.getCount() - 1;
        }

        /*将二维坐标转换为一维索引 */
        int landIndex(int i, int j) {
            //注意索引位置
            return i * col + j;
        }


        class UnionFind {
            private int count;
            private int[] parent;

            /* 定义n个并查集 */
            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            /* 找到所属并查集的代表元素 */
            public int find(int p) {
                while (p != parent[p]) {
                    parent[p] = parent[parent[p]];
                    p = parent[p];
                }
                return p;
            }

            /* 合并2个并查集 */
            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return;
                parent[rootQ] = rootP;
                --count;
            }

            public int getCount() {
                return count;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}

