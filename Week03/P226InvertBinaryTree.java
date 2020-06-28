//翻转一棵二叉树。 
//
// 示例： 
//
// 输入： 
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9 
//
// 输出： 
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1 
//
// 备注: 
//这个问题是受到 Max Howell 的 原问题 启发的 ： 
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。 
// Related Topics 树

package algorithm10.week03;

import model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class P226InvertBinaryTree {

    public static void main(String[] args) {
        Solution solution = new P226InvertBinaryTree().new Solution();
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
        public TreeNode invertTree(TreeNode root) {
            //方法一、使用递归的方式
//        return helper(root);
            //方法二、使用队列迭代的方式。root先入队。
            //队列不为空时，进行循环。取出队头节点，交换其左右节点，然后将对头元素的左右节点入队
            if (root == null) {
                return null;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                TreeNode tempNode = queue.removeFirst();
                TreeNode temp = tempNode.right;
                tempNode.right = tempNode.left;
                tempNode.left = temp;
                if (tempNode.left != null) {
                    queue.addLast(tempNode.left);
                }
                if (tempNode.right != null) {
                    queue.addLast(tempNode.right);
                }
            }
            return root;
        }

        private TreeNode helper(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode leftTreeNode = helper(root.left);
            TreeNode rightTreeNode = helper(root.right);
            root.left = rightTreeNode;
            root.right = leftTreeNode;
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

