//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划

package algorithm10.week06;

public class P152MaximumProductSubarray {

    public static void main(String[] args) {
        Solution solution = new P152MaximumProductSubarray().new Solution();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProduct2(int[] nums) {
            //maxDp[i]表示以i为结尾的子串最大乘积
            //maxDp[i] = Math.max(maxDp[i - 1] * nums[i],nums[i]);
            //但是需要考虑nums[i]为负数的情况，可能nums[i]*minDp[i - 1]才是maxDp[i]的最大值
            int[] maxDp = new int[nums.length];
            int[] minDp = new int[nums.length];
            int result = maxDp[0] = minDp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                maxDp[i] = Math.max(maxDp[i - 1] * nums[i], Math.max(minDp[i - 1] * nums[i], nums[i]));
                minDp[i] = Math.min(maxDp[i - 1] * nums[i], Math.min(minDp[i - 1] * nums[i], nums[i]));

                result = Math.max(result, maxDp[i]);
            }
            return result;
        }

        /**
         * 空间优化
         *
         * @param nums
         * @return
         */
        public int maxProduct(int[] nums) {
            int maxValue, minValue, result;
            result = maxValue = minValue = nums[0];
            int temp;
            for (int i = 1; i < nums.length; i++) {
                temp = maxValue;
                maxValue = Math.max(temp * nums[i], Math.max(minValue * nums[i], nums[i]));
                minValue = Math.min(temp * nums[i], Math.min(minValue * nums[i], nums[i]));

                result = Math.max(result, maxValue);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

