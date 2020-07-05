//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找

package algorithm10.week04;

public class P69Sqrtx {

    public static void main(String[] args) {
        Solution solution = new P69Sqrtx().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方法一、二分法
        public int mySqrt1(int x) {
            if (x < 2) {
                return x;
            }
            int low = 0, high = x;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (mid <= x / mid && (mid + 1) > x / (mid + 1)) {
                    return mid;
                } else if (mid > x / mid) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }

        //方法二、牛顿迭代法
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            double C = x, oldX = x;
            while (true) {
                double newX = (oldX + (C / oldX)) * 0.5;
                if (Math.abs(newX - oldX) < 1e-6) {
                    break;
                }
                oldX = newX;
            }
            return (int) oldX;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

