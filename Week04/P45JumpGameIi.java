//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组

package algorithm10.week04;

public class P45JumpGameIi {

    public static void main(String[] args) {
        Solution solution = new P45JumpGameIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.从左到右贪心
        public int jump1(int[] nums) {
            int result = 0;
            //每次跳跃次数加一的边界
            int end = 0;
            int max = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                max = Math.max(max, i + nums[i]);
                //更新跳跃次数以及边界
                if (i == end) {
                    end = max;
                    result++;
                }
            }
            return result;
        }

        // 2.从右向左，每次得到能达到的最小索引
        public int jump(int[] nums) {
            int result = 0;
            int targetIndex = nums.length - 1;
            while (targetIndex > 0) {
                for (int i = 0; i < targetIndex; i++) {
                    if (i + nums[i] >= targetIndex) {
                        targetIndex = i;
                        result++;
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

