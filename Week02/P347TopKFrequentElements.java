//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表

package algorithm10.week02;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P347TopKFrequentElements {

    public static void main(String[] args) {
        Solution solution = new P347TopKFrequentElements().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //使用优先队列，大顶堆，<num,count>，通过count进行比较
        public int[] topKFrequent(int[] nums, int k) {
            if (nums == null || nums.length < k) {
                return new int[0];
            }
            int [] result = new int[k];
            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Integer> heap = new PriorityQueue<Integer>((o1, o2) -> {
                return map.get(o2) - map.get(o1);
            });
            for (int i : nums) {
                map.put(i,map.getOrDefault(i,0) + 1);
            }
            for (Integer key :map.keySet()) {
                heap.add(key);
            }
            for (int i = 0; i < k; i++) {
                result[i] = (int) heap.poll();
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

