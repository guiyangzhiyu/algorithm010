//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表


package algorithm10.week02;
//java:有效的字母异位词

import java.util.Arrays;

public class P242ValidAnagram {
    public static void main(String[] rgs) {
        Solution solution = new P242ValidAnagram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用Arrays.sort()进行排序，然后比较。
         * 时间复杂度是O(nlogn)。排序是O(nlogn),比较是O(n).
         */
        public boolean isAnagram2(String s, String t) {
            char[] sArr = s.toCharArray();
            char[] tArr = t.toCharArray();
            Arrays.sort(sArr);
            Arrays.sort(tArr);
            return Arrays.equals(sArr,tArr);
        }
        /**
         * 使用哈希表进行比较
         * 时间复杂度O(n)
         * 空间复杂度O(1)，因为使用的是常数长度的数组
         */
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            /**
             * 因为s和t的取值是26个字母，因此最多有26种不同的数
             */
            int[] counter = new int[26];
            for (int i = 0; i < s.length(); i++) {
                counter[s.charAt(i) - 'a']++;
                counter[t.charAt(i) - 'a']--;
            }
            //如果counter中所有的元素都是0，返回true
            for (int a : counter) {
                if (a != 0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
