//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索

package algorithm10.week04;

import java.util.*;

public class P127WordLadder {

    public static void main(String[] args) {
        Solution solution = new P127WordLadder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 与433 最小基因的题目类似
         *
         * @param beginWord
         * @param endWord
         * @param wordList
         * @return
         */
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            //使用queue记录每层的数据，返回当前匹配的层级
            int result = 1;
            Set<String> wordListSet = new HashSet<>();
            for (String s : wordList) {
                wordListSet.add(s);
            }
            Deque<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            queue.addLast(beginWord);
            visited.add(beginWord);
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    String currStr = queue.removeFirst();
                    if (currStr.equals(endWord)) {
                        return result;
                    }
                    char[] currArray = currStr.toCharArray();
                    for (int i = 0; i < currArray.length; i++) {
                        char oldCh = currArray[i];
                        for (char ch = 'a'; ch <= 'z'; ch++) {
                            currArray[i] = ch;
                            String tempStr = new String(currArray);
                            if (!visited.contains(tempStr) && wordListSet.contains(tempStr)) {
                                visited.add(tempStr);
                                queue.addLast(tempStr);
                            }
                        }
                        currArray[i] = oldCh;
                    }
                }
                result++;
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

