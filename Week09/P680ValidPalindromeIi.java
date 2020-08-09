//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串

package algorithm10.week09;

public class P680ValidPalindromeIi {

    public static void main(String[] args) {
        Solution solution = new P680ValidPalindromeIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean validPalindrome(String s) {
            int left = 0, right = s.length() - 1;
            while (left < right) {
                char leftCh = s.charAt(left), rightCh = s.charAt(right);
                if (leftCh == rightCh) {
                    ++left;
                    --right;
                } else {
                    boolean leftFalg = true, rightFlag = true;
                    //删除左边
                    for (int i = left + 1, j = right; i < j; ++i, --j) {
                        char ch1 = s.charAt(i), ch2 = s.charAt(j);
                        if (ch1 != ch2) {
                            leftFalg = false;
                            break;
                        }
                    }
                    //删除右边
                    for (int i = left, j = right - 1; i < j; ++i, --j) {
                        char ch1 = s.charAt(i), ch2 = s.charAt(j);
                        if (ch1 != ch2) {
                            rightFlag = false;
                            break;
                        }
                    }
                    return leftFalg || rightFlag;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

