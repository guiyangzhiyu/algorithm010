//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class P17LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        List<String> result = new ArrayList<>();
        HashMap<Character,String> map = new HashMap<>();
        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return result;
            }

            map.put('2',"abc");
            map.put('3',"def");
            map.put('4',"ghi");
            map.put('5',"jkl");
            map.put('6',"mno");
            map.put('7',"pqrs");
            map.put('8',"tuv");
            map.put('9',"wxyz");
            helper(digits,0,"");
            return result;
        }

        private void helper(String digits, int index, String s) {
            if (index == digits.length()) {
                result.add(s);
                return;
            }
            //得到digits.charAt(index)对应的字母串
            String str = map.get(digits.charAt(index));
            for (char c : str.toCharArray()) {
                helper(digits,index + 1,s+c);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

