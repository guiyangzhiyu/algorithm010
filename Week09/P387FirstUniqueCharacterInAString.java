//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串

package algorithm10.week09;

public class P387FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        Solution solution = new P387FirstUniqueCharacterInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstUniqChar(String s) {
            int result = Integer.MAX_VALUE;
            int[] counter = new int[26];
            for (int i = 0; i < 26; i++) {
                counter[i] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < s.length(); i++) {
                if (counter[s.charAt(i) - 'a'] != Integer.MAX_VALUE) {
                    counter[s.charAt(i) - 'a'] = -1;
                } else {
                    counter[s.charAt(i) - 'a'] = i;
                }
            }
            for (int i = 0; i < 26; i++) {
                if (counter[i] != -1) {
                    result = Math.min(result, counter[i]);
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

