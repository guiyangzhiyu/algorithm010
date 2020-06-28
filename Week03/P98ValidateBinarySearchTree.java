//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索

package algorithm10.week03;

import model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class P98ValidateBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new P98ValidateBinarySearchTree().new Solution();
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
        //方法一、使用中序遍历的方式，如果发现值不是递增的，那么返回false
        public boolean isValidBST(TreeNode root) {
            double currValue = -Double.MAX_VALUE;
            Deque<TreeNode> stack = new LinkedList<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.addFirst(root);
                    root = root.left;
                }
                root = stack.removeFirst();
                if (root.val <= currValue) {
                    return false;
                }
                currValue = root.val;
                root = root.right;
            }
            return true;
        }

        //方法二、使用递归的方式
        public boolean isValidBST2(TreeNode root) {
            return helper(root, null, null);
        }

        /**
         * @param root
         * @param min  表示最小值，如果是root的右子树，min = root.val
         * @param max  表示最大值，如果是root的左子树，max = root.val
         * @return
         */
        private boolean helper(TreeNode root, Integer min, Integer max) {
            if (root == null) {
                return true;
            }
            if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
                return false;
            }
            return helper(root.left, min, root.val) && helper(root.right, root.val, max);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

