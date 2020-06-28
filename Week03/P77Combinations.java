//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法

package algorithm10.week03;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P77Combinations {

    public static void main(String[] args) {
        Solution solution = new P77Combinations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        //回溯
        public List<List<Integer>> combine(int n, int k) {
            if (n <= 0 || k <= 0) {
                return result;
            }
            helper(n,k,1,new LinkedList<Integer>());
            return result;
        }

        private void helper(int n, int k, int i, LinkedList<Integer> tempList) {
            if (tempList.size() == k) {
                result.add(new LinkedList<>(tempList));
            }
            for (int j = i; j <= n; j++) {
                tempList.addLast(j);
                helper(n,k,j+1,tempList);
                tempList.removeLast();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

