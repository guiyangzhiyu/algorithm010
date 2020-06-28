//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.List;

public class P46Permutations {

    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)

        //https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
        List<List<Integer>> result = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            backTrack(new ArrayList<Integer>(),nums);
            return result;
        }

        private void backTrack(ArrayList<Integer> list, int[] nums) {
            if (list.size() == nums.length) {
                result.add(new ArrayList<>(list));
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (list.contains(nums[i])) {
                        continue;
                    }
                    list.add(nums[i]);
                    backTrack(list,nums);
                    list.remove(list.size() - 1);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

