//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 示例 1: 
//
// 
//输入: "Let's take LeetCode contest"
//输出: "s'teL ekat edoCteeL tsetnoc" 
// 
//
// 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// Related Topics 字符串

package algorithm10.week09;

public class P557ReverseWordsInAStringIii {

    public static void main(String[] args) {
        Solution solution = new P557ReverseWordsInAStringIii().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            char[] sArr = s.toCharArray();
            int left = 0, right;
            for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] == ' ' || i == sArr.length - 1) {
                    right = i == sArr.length - 1 ? sArr.length - 1 : i - 1;
                    while (left < right) {
                        char temp = sArr[left];
                        sArr[left++] = sArr[right];
                        sArr[right--] = temp;
                    }
                    left = i + 1;
                }
            }
            return new String(sArr);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

