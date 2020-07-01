//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法

package algorithm10.week03;

public class P169MajorityElement {

    public static void main(String[] args) {
        Solution solution = new P169MajorityElement().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方法一、投票法
        //初始的count == 0,遍历到第一个数，+1，遇到相同的+1，不同的-1。
        // 当count == 0 时，更新遍历的数，count+1
        //。。。最后遍历的数就是结果
        public int majorityElement1(int[] nums) {
            if (nums == null || nums.length == 0) {
                throw new IllegalArgumentException("数组为空");
            }
            int result = nums[0],count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (result == nums[i]) {
                    count++;
                } else if (--count == 0) {
                        result = nums[i];
                        count = 1;
                }
            }
            return result;
        }

        //方法二、分治。如果x是nums中的众数，那么x一定是nums分成2部分后，其中一部分的众数
        //二分一次nums后，2个子串的众数中的一个就是nums的众数
        //哪个的个数多，哪个就是
        public int majorityElement(int[] nums) {
            return majorityElementDivide(nums,0,nums.length-1);
        }

        private int majorityElementDivide(int[] nums, int low, int high) {
            if (low == high) {
                return nums[low];
            }
            int mid = low + (high - low)/2;
            int left = majorityElementDivide(nums,low,mid);
            int right = majorityElementDivide(nums,mid+1,high);

            if (left == right) {
                return left;
            }

            //比较left在nums[low..high]中的个数 与 right在nums[low..high]中的个数
            int leftSize = rangeSize(nums,left,low,high);
            int rightSize = rangeSize(nums,right,low,high);

            return leftSize > rightSize ? left : right;
        }

        private int rangeSize(int[] nums, int num, int low, int high) {
            int count = 0;
            for (int i = low ;i <= high; i++) {
                if (num == nums[i]) {
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

