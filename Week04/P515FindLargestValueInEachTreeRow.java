//您需要在二叉树的每一行中找到最大的值。 
//
// 示例： 
//
// 
//输入: 
//
//          1
//         / \
//        3   2
//       / \   \  
//      5   3   9 
//
//输出: [1, 3, 9]
// 
// Related Topics 树 深度优先搜索 广度优先搜索


package algorithm10.week04;
//java:在每个树行中找最大值

import model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P515FindLargestValueInEachTreeRow {
    public static void main(String[] rgs) {
        Solution solution = new P515FindLargestValueInEachTreeRow().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        List<Integer> result = new ArrayList<>();

        /**
         * 方法一、使用BFS方式，queue的每层的最大值
         *
         * @param root
         * @return
         */
        public List<Integer> largestValues1(TreeNode root) {
            if (root == null) {
                return result;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                //每层的queeu
                int size = queue.size();
                int currMax = Integer.MIN_VALUE;
                while (size-- > 0) {
                    TreeNode currNode = queue.removeFirst();
                    currMax = Math.max(currMax, currNode.val);
                    if (currNode.left != null) {
                        queue.addLast(currNode.left);
                    }
                    if (currNode.right != null) {
                        queue.addLast(currNode.right);
                    }
                }
                result.add(currMax);
            }
            return result;
        }

        /**
         * 方法二、使用前序遍历的方式，通过比较resutl元素的索引和当前层级的索引，获取最大值
         *
         * @param root
         * @return
         */
        public List<Integer> largestValues(TreeNode root) {
            if (root == null) {
                return result;
            }
            helper(root, 0);
            return result;
        }

        /**
         * level 表示当前层级，如果result.size() == level,那么初始化result[level]的值为root.val
         * 否则进行比较
         *
         * @param root
         * @param level
         */
        private void helper(TreeNode root, int level) {
            if (root == null) {
                return;
            }
            if (result.size() == level) {
                result.add(root.val);
            } else { //表示level层已经存在值了
                result.set(level, Math.max(root.val, result.get(level)));
            }
            helper(root.left, level + 1);
            helper(root.right, level + 1);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
