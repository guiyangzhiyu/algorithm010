//给定一个二叉树，返回它的 前序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


package algorithm10.week02;
//java:二叉树的前序遍历

import model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P144BinaryTreePreorderTraversal {
    public static void main(String[] rgs) {
        Solution solution = new P144BinaryTreePreorderTraversal().new Solution();
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

        public List<Integer> preorderTraversal(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<>();
            if (root != null) {
                stack.addFirst(root);
            }
            while (!stack.isEmpty()) {
                root = stack.removeFirst();
                result.add(root.val);
                if (root.right != null) {
                    stack.addFirst(root.right);
                }
                if (root.left != null) {
                    stack.addFirst(root.left);
                }
            }
            return result;
        }
        /**
         * 递归方式
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal2(TreeNode root) {
            helper(root);
            return result;
        }

        private void helper(TreeNode root) {
            if (root == null) {
                return;
            }
            result.add(root.val);
            helper(root.left);
            helper(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
