//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
//


package algorithm10.week04;
//java:最小基因变化

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class P433MinimumGeneticMutation {
    public static void main(String[] rgs) {
        Solution solution = new P433MinimumGeneticMutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // BFS,queue存储每层的元素
        public int minMutation(String start, String end, String[] bank) {
            int result = 0;
            char[] fourChars = new char[]{'A', 'C', 'G', 'T'};
            Set<String> bankSet = new HashSet<>();
            for (String s : bank) {
                bankSet.add(s);
            }
            Deque<String> queue = new LinkedList<>();
            queue.addLast(start);
            //已经访问过的元素
            Set<String> visited = new HashSet<>();
            visited.add(start);

            while (!queue.isEmpty()) {
                //每层的数量
                int size = queue.size();
                while (size-- > 0) {
                    String currStr = queue.removeFirst();
                    if (currStr.equals(end)) {
                        return result;
                    }
                    char[] currArray = currStr.toCharArray();
                    for (int i = 0; i < currArray.length; i++) {
                        char oldChar = currArray[i];
                        for (char fourChar : fourChars) {
                            currArray[i] = fourChar;
                            String tempStr = new String(currArray);
                            if (!visited.contains(tempStr) && bankSet.contains(tempStr)) {
                                visited.add(tempStr);
                                queue.addLast(tempStr);
                            }
                        }
                        //恢复原字符
                        currArray[i] = oldChar;
                    }
                }
                result++;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
