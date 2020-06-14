//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针

package algorithm10.week01;

public class P42TrappingRainWater {

    public static void main(String[] args) {
        Solution solution = new P42TrappingRainWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            // 初始化左边的最大高度
            int left = 0;
            //记录每个元素右边的最大高度
            int[] right = new int[height.length];
            for (int i = height.length - 2; i >= 0; i--) {
                right[i] = Math.max(height[i + 1], right[i + 1]);
            }
            int sum = 0;
            for (int i = 1; i < height.length - 1; i++) {
                left = Math.max(left, height[i - 1]);
                int temp = Math.min(left, right[i]);
                if (temp > height[i]) {
                    sum += temp - height[i];
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

