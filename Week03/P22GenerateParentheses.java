//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.List;

public class P22GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用递归的方式，2*n个格子里面，放入左/右括号。
         * 左括号 < n，直接放
         * 右括号 < 前面的左括号，才能放
         * @param n 括号的对数
         * @return 所有的情况
         */
        public List<String> result = new ArrayList<>();
        public List<String> generateParenthesis(int n) {
            helper(0,0,n,"");
            return result;
        }

        /**
         * 每次生成的括号串
         * @param left 已经添加的左括号数量
         * @param right 已经添加的右括号数量
         * @param sum 括号的对数
         * @param s 生成的括号串
         */
        private void helper(int left, int right, int sum, String s) {
            //terminate
            if (left == sum && right == sum) {
                result.add(s);
            }
            //process
            //drill dowm
            if (left < sum) {
                helper(left + 1,right,sum,s+"(");
            }
            if (right < left) {
                helper(left,right + 1,sum,s+")");
            }
            //reverse state
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

