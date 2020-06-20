//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划

package algorithm10.week02;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class P264UglyNumberIi {

    public static void main(String[] args) {
        Solution solution = new P264UglyNumberIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class Ugly{
            //存储前1690个丑数
            public int[] nums = new int[1690];
            public Ugly(){
                //去重
                Set<Long> set = new HashSet<>();
                // 小顶堆
                PriorityQueue<Long> heap = new PriorityQueue<>();
                // 丑数的因子
                int[] uglyArr = new int[]{2,3,5};
                set.add(1L);
                heap.add(1L);
                long currTemp,newTemp;
                for (int i = 0; i < nums.length; i++) {
                    currTemp = heap.poll();
                    nums[i] = (int) currTemp;
                    for (int j : uglyArr) {
                        newTemp = currTemp * j;
                        if (!set.contains(newTemp)) {
                            set.add(newTemp);
                            heap.add(newTemp);
                        }
                    }
                }
            }
        }
        public int nthUglyNumber(int n) {
            Ugly ugly = new Ugly();
            return ugly.nums[n-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

