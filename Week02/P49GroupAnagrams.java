//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串

package algorithm10.week02;

import java.util.*;

public class P49GroupAnagrams {

    public static void main(String[] args) {
        Solution solution = new P49GroupAnagrams().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1、使用sort排序来确定
        public List<List<String>> groupAnagrams2(String[] strs) {
            Map<String, List> map = new HashMap<>();
            if (strs == null || strs.length == 0) {
                return new ArrayList<>();
            }
            for (String str : strs) {
                char[] charArr = str.toCharArray();
                Arrays.sort(charArr);
                String key = String.valueOf(charArr);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList());
                }
                map.get(key).add(str);
            }
            return new ArrayList(map.values());
        }

        //2、使用counter[26]来确定
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> result = new ArrayList<>();
            if (strs == null || strs.length == 0) {
                return result;
            }
            Map<String, List> map = new HashMap<>();
            int[] counter = new int[26];
            for (String str : strs) {
                //每次循环，初始化counter
                Arrays.fill(counter, 0);
                for (char c : str.toCharArray()) {
                    counter[c - 'a']++;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    sb.append("#");
                    sb.append(counter[i]);
                }
                String key = sb.toString();
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList());
                }
                map.get(key).add(str);
            }
            for (List value : map.values()) {
                result.add(value);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

