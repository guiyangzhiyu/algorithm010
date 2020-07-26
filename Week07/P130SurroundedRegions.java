//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集

package algorithm10.week07;

public class P130SurroundedRegions {

    public static void main(String[] args) {
        Solution solution = new P130SurroundedRegions().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
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
                while (parent[p] != p) {
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
                parent[rootP] = rootQ;
                --count;
            }

            public int getCount() {
                return count;
            }

            public boolean isUnion(int p, int q) {
                return find(p) == find(q);
            }
        }

        int row, col;

        public void solve(char[][] board) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return;
            }
            row = board.length;
            col = board[0].length;
            //建立一个虚拟节点与边界的‘O’相连
            UnionFind unionFind = new UnionFind(row * col + 1);
            int visualNode = row * col;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 'O') {
                        if (j == 0 || i == 0 || i == row - 1 || j == col - 1) {
                            unionFind.union(index(i, j), visualNode);
                        } else {
                            // 和上下左右合并成一个连通区域.
                            if (i > 0 && board[i - 1][j] == 'O') {
                                unionFind.union(index(i, j), index(i - 1, j));
                            }
                            if (i < row - 1 && board[i + 1][j] == 'O') {
                                unionFind.union(index(i, j), index(i + 1, j));
                            }
                            if (j > 0 && board[i][j - 1] == 'O') {
                                unionFind.union(index(i, j), index(i, j - 1));
                            }
                            if (j < col - 1 && board[i][j + 1] == 'O') {
                                unionFind.union(index(i, j), index(i, j + 1));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == 'O' && !unionFind.isUnion(index(i, j), visualNode)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        /* 转化为一维 */
        int index(int i, int j) {
            return i * col + j;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

