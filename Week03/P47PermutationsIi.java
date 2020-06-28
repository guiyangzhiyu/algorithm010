//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47PermutationsIi {

    public static void main(String[] args) {
        Solution solution = new P47PermutationsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            backTrack(new ArrayList<Integer>(),nums,new boolean[nums.length]);
            return result;
        }

        private void backTrack(ArrayList<Integer> list, int[] nums, boolean[] used) {
            if (list.size() == nums.length) {
                result.add(new ArrayList<>(list));
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i-1])) {
                        continue;
                    }
                    used[i] = true;
                    list.add(nums[i]);
                    backTrack(list,nums,used);
                    used[i] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

