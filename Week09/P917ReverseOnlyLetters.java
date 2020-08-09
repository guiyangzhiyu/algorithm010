//给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入："ab-cd"
//输出："dc-ba"
// 
//
// 示例 2： 
//
// 输入："a-bC-dEf-ghIj"
//输出："j-Ih-gfE-dCba"
// 
//
// 示例 3： 
//
// 输入："Test1ng-Leet=code-Q!"
//输出："Qedo1ct-eeLg=ntse-T!"
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 100 
// 33 <= S[i].ASCIIcode <= 122 
// S 中不包含 \ or " 
// 
// Related Topics 字符串

package algorithm10.week09;

public class P917ReverseOnlyLetters {

    public static void main(String[] args) {
        Solution solution = new P917ReverseOnlyLetters().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseOnlyLetters(String S) {
            char[] SArr = S.toCharArray();
            int left = 0, right = S.length() - 1;
            while (left < right) {
                //不是字母
                if (!Character.isLetter(SArr[left])) {
                    ++left;
                    continue;
                }
                if (!Character.isLetter(SArr[right])) {
                    --right;
                    continue;
                }
                char temp = SArr[left];
                SArr[left++] = SArr[right];
                SArr[right--] = temp;
            }
            return new String(SArr);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

