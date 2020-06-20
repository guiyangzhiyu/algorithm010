//给定一个二叉树，返回它的中序 遍历。 
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
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


package algorithm10.week02;
//java:二叉树的中序遍历

import model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P94BinaryTreeInorderTraversal {
    public static void main(String[] rgs) {
        Solution solution = new P94BinaryTreeInorderTraversal().new Solution();
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
         * 迭代方式
         *
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.addFirst(root);
                    root = root.left;
                }
                root = stack.removeFirst();
                result.add(root.val);
                root = root.right;
            }
            return result;
        }

        /**
         * 递归的方式
         *
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal2(TreeNode root) {
            helper(root);
            return result;
        }

        public void helper(TreeNode root) {
            if (root == null) {
                return;
            }
            helper(root.left);
            result.add(root.val);
            helper(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
