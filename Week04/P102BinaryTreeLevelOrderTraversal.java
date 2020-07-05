//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索

package algorithm10.week04;

import model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P102BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Solution solution = new P102BinaryTreeLevelOrderTraversal().new Solution();
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
        public List<List<Integer>> result = new ArrayList<>();

        /**
         * 使用BFS进行遍历，使用queue
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return result;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            TreeNode temp;
            while (!queue.isEmpty()) {
                //queue每次存放一层的数据
                int size = queue.size();
                //存储当前层的val
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    temp = queue.removeFirst();
                    list.add(temp.val);
                    if (temp.left != null) {
                        queue.addLast(temp.left);
                    }
                    if (temp.right != null) {
                        queue.addLast(temp.right);
                    }
                }
                result.add(list);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

