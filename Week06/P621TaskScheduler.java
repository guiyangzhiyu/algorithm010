//给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务
//都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。 
//
// 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的最短时间。 
//
// 
//
// 示例 ： 
//
// 输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 
//
// 提示： 
//
// 
// 任务的总个数为 [1, 10000]。 
// n 的取值范围为 [0, 100]。 
// 
// Related Topics 贪心算法 队列 数组

package algorithm10.week06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P621TaskScheduler {

    public static void main(String[] args) {
        Solution solution = new P621TaskScheduler().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用大顶堆，存储任务，在没个调度周期内，取出数量最多的n + 1个任务执行
         *
         * @param tasks
         * @param n
         * @return
         */
        public int leastInterval(char[] tasks, int n) {
            int[] map = new int[26];
            for (char ch : tasks) {
                map[ch - 'A']++;
            }
            //堆中最多26个元素,大顶堆
            PriorityQueue<Integer> heap = new PriorityQueue<>(26, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int f : map) {
                if (f > 0) {
                    heap.add(f);
                }
            }
            int result = 0;
            while (!heap.isEmpty()) {
                int i = 0;
                //记录每个调度周期中，执行一次任务后剩余的任务数（>0）
                List<Integer> tempList = new ArrayList<>();
                while (i <= n) { //每个调度周期
                    if (!heap.isEmpty()) {
                        if (heap.peek() > 1) { //执行一次后不为0，存储到tempList中
                            tempList.add(heap.poll() - 1);
                        } else {
                            heap.poll();
                        }
                    }
                    result++;
                    if (heap.isEmpty() && tempList.size() == 0) {
                        break;
                    }
                    i++;
                }
                for (int tempValue : tempList) {
                    heap.add(tempValue);
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

