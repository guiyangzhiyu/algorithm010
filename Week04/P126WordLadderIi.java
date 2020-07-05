//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换后得到的单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
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
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法

package algorithm10.week04;

import java.util.*;

public class P126WordLadderIi {

    public static void main(String[] args) {
        Solution solution = new P126WordLadderIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> result = new ArrayList<>();
            //构造wordListSet
            Set<String> wordListSet = new HashSet<>();
            for (String s : wordList) {
                wordListSet.add(s);
            }
            wordListSet.add(beginWord);
            //word的相邻word集合
            Map<String, List<String>> nextMap = new HashMap<>();
            //word的距离集合
            Map<String, Integer> distanceMap = new HashMap<>();
            List<String> solution = new ArrayList<>();
            //生成nextMap 和 distanceMap
            bfs(beginWord, endWord, wordListSet, nextMap, distanceMap);
            dfs(beginWord, endWord, nextMap, distanceMap, solution, result);

            return result;
        }


        private void bfs(String beginWord,
                         String endWord,
                         Set<String> wordListSet,
                         Map<String, List<String>> nextMap,
                         Map<String, Integer> distanceMap) {
            for (String str : wordListSet) {
                nextMap.put(str, new ArrayList<String>());
            }
            Deque<String> queue = new LinkedList<>();
            queue.addLast(beginWord);
            distanceMap.put(beginWord, 0);
            while (!queue.isEmpty()) {
                boolean findEnd = false;
                int size = queue.size();
                while (size-- > 0) {
                    String currStr = queue.removeFirst();
                    int currDistance = distanceMap.get(currStr);
                    List<String> currNextList = getNextList(currStr, wordListSet);
                    for (String str : currNextList) {
                        nextMap.get(currStr).add(str);
                        if (!distanceMap.containsKey(str)) {
                            distanceMap.put(str, currDistance + 1);
                            if (str.equals(endWord)) {
                                findEnd = true;
                            } else {
                                queue.addLast(str);
                            }
                        }
                    }
                }
                if (findEnd) {
                    break;
                }
            }
        }

        private List<String> getNextList(String currStr, Set<String> wordListSet) {
            List<String> result = new ArrayList<>();
            char[] currArray = currStr.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                for (int i = 0; i < currArray.length; i++) {
                    if (ch == currArray[i]) continue;
                    char oldTemp = currArray[i];
                    currArray[i] = ch;
                    String tempStr = new String(currArray);
                    if (wordListSet.contains(tempStr)) {
                        result.add(tempStr);
                    }
                    currArray[i] = oldTemp;
                }
            }
            return result;
        }

        private void dfs(String beginWord,
                         String endWord,
                         Map<String, List<String>> nextMap,
                         Map<String, Integer> distanceMap,
                         List<String> solution,
                         List<List<String>> result) {

            solution.add(beginWord);

            if (beginWord.equals(endWord)) {
                result.add(new ArrayList<>(solution));
            } else {
                for (String next : nextMap.get(beginWord)) {
                    if (distanceMap.get(next) == distanceMap.get(beginWord) + 1) {
                        dfs(next, endWord, nextMap, distanceMap, solution, result);
                    }
                }
            }
            solution.remove(solution.size() - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

