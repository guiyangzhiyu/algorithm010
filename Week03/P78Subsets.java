//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.List;

public class P78Subsets {

    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> newAppend = new ArrayList<>();
                for (List<Integer> integers : result) {
                    List<Integer> newTemp = new ArrayList<>();
                    newTemp.addAll(integers);
                    newTemp.add(nums[i]);
                    newAppend.add(newTemp);
                }
                result.addAll(newAppend);
            }
            return result;
        }


        public List<List<Integer>> subsets2(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null)
                return result;
            backtrack(result,nums,new ArrayList<Integer>(),0);
            return result;
        }

        private void backtrack(List<List<Integer>> result, int[] nums, ArrayList<Integer> list, int i) {
            if (nums.length == i) {
                result.add(new ArrayList<>(list));
                return;
            }
            //
            backtrack(result,nums,list,i+1);

            list.add(nums[i]);
            backtrack(result,nums,list,i+1);
            list.remove(list.size() - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

