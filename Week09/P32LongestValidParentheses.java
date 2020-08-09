//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划

package algorithm10.week09;

import java.util.Deque;
import java.util.LinkedList;

public class P32LongestValidParentheses {

    public static void main(String[] args) {
        Solution solution = new P32LongestValidParentheses().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方法一、使用栈添加索引的方式
        public int longestValidParentheses1(String s) {
            int result = 0;
            Deque<Integer> stack = new LinkedList<>();
            stack.addFirst(-1);
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    stack.addFirst(i);
                } else {
                    stack.removeFirst();
                    if (stack.isEmpty()) {
                        stack.addFirst(i);
                    } else {
                        result = Math.max(result, i - stack.peekFirst());
                    }
                }
            }
            return result;
        }

        //方法二、使用动态规划
        public int longestValidParentheses(String s) {
            int result = 0;
            int[] dp = new int[s.length()];
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == ')') {
                    if (s.charAt(i - 1) == '(') {
                        dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    result = Math.max(result, dp[i]);
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

