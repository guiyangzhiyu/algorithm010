//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。 
//
// 示例 1: 
//
// 
//输入: "abc"
//输出: 3
//解释: 三个回文子串: "a", "b", "c".
// 
//
// 示例 2: 
//
// 
//输入: "aaa"
//输出: 6
//说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
// 
//
// 注意: 
//
// 
// 输入的字符串长度不会超过1000。 
// 
// Related Topics 字符串 动态规划

package algorithm10.week06;

public class P647PalindromicSubstrings {

    public static void main(String[] args) {
        Solution solution = new P647PalindromicSubstrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //回文串中心往外扩散
        //长度为N的字符串，可能有2*N - 1个回文中心
        public int countSubstrings(String s) {
            int n = s.length(), result = 0;
            for (int center = 0; center < 2 * n; center++) {
                //center为中心的回文串左右边界
                int left = center / 2;
                int right = left + center % 2;
                while (left >= 0 && right < n && (s.charAt(left) == s.charAt(right))) {
                    left--;
                    right++;
                    result++;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

