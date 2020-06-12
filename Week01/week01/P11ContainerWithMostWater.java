//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针


package algorithm10.week01;
//java:盛最多水的容器

public class P11ContainerWithMostWater {
    public static void main(String[] rgs) {
        Solution solution = new P11ContainerWithMostWater().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 头尾两个指针，每次移动值较小的
         *
         * @param height 目标数组
         * @return 数组在坐标系中形成的区域
         */
        public int maxArea(int[] height) {
            if (height == null || height.length < 2) {
                return 0;
            }
            int p = 0, q = height.length - 1;
            int maxArea = 0;
            while (p < q) {
                maxArea = Math.max(Math.min(height[p], height[q]) * (q - p), maxArea);
                if (height[p] < height[q]) {
                    p++;
                } else {
                    q--;
                }
            }
            return maxArea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
