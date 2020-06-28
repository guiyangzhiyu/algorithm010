//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索

package algorithm10.week03;

import model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class P111MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        Solution solution = new P111MinimumDepthOfBinaryTree().new Solution();
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
        //递归1
        public int minDepth2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return 1;
            }
            int resultDepth = Integer.MAX_VALUE;
            if (root.left != null) {
                resultDepth = Math.min(resultDepth,minDepth2(root.left));
            }
            if (root.right != null) {
                resultDepth = Math.min(resultDepth,minDepth2(root.right));
            }
            return resultDepth + 1;
        }

        //递归2
        public int minDepth3(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = minDepth3(root.left);
            int rightDepth = minDepth3(root.right);
            return (leftDepth == 0 || rightDepth == 0) ? Math.max(leftDepth,rightDepth) + 1: Math.min(leftDepth,rightDepth) + 1;
        }
        //TODO https://leetcode.com/problems/minimum-depth-of-binary-tree/discuss/36045/My-4-Line-java-solution
        //BFS方式
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            int result = 1;
            while (!queue.isEmpty()) {
                //当前queue的长度
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode tempNode = queue.removeFirst();
                    if (tempNode.left == null && tempNode.right == null) {
                        return result;
                    }
                    if (tempNode.left != null) {
                        queue.addLast(tempNode.left);
                    }
                    if (tempNode.right != null) {
                        queue.addLast(tempNode.right);
                    }
                }
                result++;
            }
            return result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}

