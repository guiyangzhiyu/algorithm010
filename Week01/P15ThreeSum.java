//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


package algorithm10.week01;
//java:三数之和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15ThreeSum {
    public static void main(String[] rgs) {
        Solution solution = new P15ThreeSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length < 3) {
                return null;
            }
            //结果数组
            List<List<Integer>> result = new ArrayList<>();
            //1.先排序
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                //异常
                if (nums[i] > 0) {
                    break;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                //前指针
                int p = i + 1;
                //后指针
                int q = nums.length - 1;
                while (p < q) {
                    if (nums[i] + nums[p] + nums[q] == 0) {
                        result.add(Arrays.asList(nums[i], nums[p], nums[q]));
                        while (p < q && nums[p] == nums[p + 1]) {
                            p++;
                        }
                        while (p < q && nums[q] == nums[q - 1]) {
                            q--;
                        }
                        //继续
                        p++;
                        q--;
                    } else if (nums[i] + nums[p] + nums[q] < 0) {
                        p++;
                    } else {
                        q--;
                    }
                }

            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
