//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串

package algorithm10.week09;

public class P541ReverseStringIi {

    public static void main(String[] args) {
        Solution solution = new P541ReverseStringIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseStr(String s, int k) {
            char[] sArr = s.toCharArray();
            //直接取每个2k的子串进行反转，对于不满2k长度的子串，进行索引比较
            for (int start = 0; start < sArr.length; start += 2 * k) {
                int left = start, right = Math.min(left + k - 1, sArr.length - 1);
                while (left < right) {
                    char temp = sArr[left];
                    sArr[left++] = sArr[right];
                    sArr[right--] = temp;
                }
            }
            return new String(sArr);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

