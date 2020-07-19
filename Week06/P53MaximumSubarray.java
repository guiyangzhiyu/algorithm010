//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划

package algorithm10.week06;

public class P53MaximumSubarray {

    public static void main(String[] args) {
        Solution solution = new P53MaximumSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray2(int[] nums) {
            //dp[i] 表示以i结尾的子串最大和
            //dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            int[] dp = new int[nums.length];
            int result = dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                result = Math.max(result, dp[i]);
            }
            return result;
        }

        /**
         * 优化空间
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int maxValue;
            int result = maxValue = nums[0];
            for (int i = 1; i < nums.length; i++) {
                maxValue = Math.max(maxValue + nums[i], nums[i]);
                result = Math.max(result, maxValue);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

