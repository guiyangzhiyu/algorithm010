//给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。 
//
// 说明：不要使用任何内置的库函数，如 sqrt。 
//
// 示例 1： 
//
// 输入：16
//输出：True 
//
// 示例 2： 
//
// 输入：14
//输出：False
// 
// Related Topics 数学 二分查找

package algorithm10.week04;

public class P367ValidPerfectSquare {

    public static void main(String[] args) {
        Solution solution = new P367ValidPerfectSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //二分法
        //注意边界条件
        //注意int范围越界
        public boolean isPerfectSquare(int num) {
            if (num < 2) {
                return true;
            }
            long low = 2, high = num;
            while (low <= high) {
                long mid = low + (high - low) / 2;
                if (mid * mid == num) {
                    return true;
                } else if (mid * mid > num) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return false;
        }

        //牛顿迭代法
        public boolean isPerfectSquare1(int num) {
            if (num < 2) {
                return true;
            }
            long x = num;
            while (x * x > num) {
                x = (x + num / x) / 2;
            }
            return x * x == num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

