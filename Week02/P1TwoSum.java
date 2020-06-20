//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表


package algorithm10.week02;
//java:两数之和

import java.util.HashMap;
import java.util.Map;

public class P1TwoSum {
    public static void main(String[] rgs) {
        Solution solution = new P1TwoSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 使用2次循环，时间复杂度O(n),空间复杂度O(n)
         *
         * @param nums   原数组
         * @param target 目标值
         * @return 索引数组
         */
        public int[] twoSum2(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                throw new IllegalArgumentException("nums参数异常");
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.putIfAbsent(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])) {
                    return new int[]{i, map.get(target - nums[i])};
                }
            }
            throw new IllegalArgumentException("target参数异常");
        }

        /**
         * 使用一次循环
         *
         * @param nums
         * @param target
         * @return
         */
        public int[] twoSum(int[] nums, int target) {
            if (nums == null || nums.length < 2) {
                throw new IllegalArgumentException("nums is illegal");
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{i, map.get(target - nums[i])};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("target is illegal");
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
