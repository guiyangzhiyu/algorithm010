//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.List;

public class P51NQueens {

    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> result = new ArrayList<>();
        public List<List<String>> solveNQueens(int n) {
            //初始化pan[n][n]
            char[][] pan = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    pan[i][j] = '.';
                }
            }
            backTrack(n,pan,0);
            return result;
        }

        // row表示第几行
        private void backTrack(int n, char[][] pan, int row) {
            if (row == n) {
                result.add(convert(pan));
                return;
            }
            //判断，pan[row][col]的位置是否合法
            for (int col = 0; col < n; col++) {
                if (isVisid(pan,row,col)) {
                    pan[row][col] = 'Q';
                    backTrack(n,pan,row + 1);
                    pan[row][col] = '.';
                }
            }
        }

        /**
         * pan[row][col]是否在已经摆放的皇后的攻击范围中
         * @param pan
         * @return
         */
        private boolean isVisid(char[][] pan, int row, int col) {
            for (int i = 0;i < row;i++) {
                for (int j = 0;j < pan.length;j++) {
                    if (pan[i][j] == 'Q' && (j == col || i - j ==row - col || i + j == row + col)) {
                        return false;
                    }
                }
            }
            return true;
        }

        /**
         * 转化为复合要求的格式输出
         * @param pan
         * @return
         */
        private List<String> convert(char[][] pan) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < pan.length; i++) {
                String str = "";
                for (int j = 0;j < pan.length;j++) {
                    str += pan[i][j];
                }
                list.add(str);
            }
            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

