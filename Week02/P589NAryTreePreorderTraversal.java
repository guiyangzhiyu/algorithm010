//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树


package algorithm10.week02;
//java:N叉树的前序遍历


import model.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P589NAryTreePreorderTraversal {
    public static void main(String[] rgs) {
        Solution solution = new P589NAryTreePreorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * // Definition for a Node.
     * class Node {
     * public int val;
     * public List<Node> children;
     * <p>
     * public Node() {
     * }
     * <p>
     * public Node(int _val) {
     * val = _val;
     * }
     * <p>
     * public Node(int _val, List<Node> _children) {
     * val = _val;
     * children = _children;
     * }
     * }
     */
    class Solution {
        /**
         * 使用迭代的方式，用一个栈来维护
         *
         * @param root
         * @return
         */
        public List<Integer> preorder(Node root) {
            List<Integer> result = new LinkedList<>();
            if (root == null) {
                return result;
            }
            Deque<Node> stack = new LinkedList<>();
            stack.addFirst(root);
            while (!stack.isEmpty()) {
                Node tempNode = stack.removeFirst();
                result.add(tempNode.val);
                //把root的子元素插入到栈中
                for (int i = tempNode.children.size() - 1; i >= 0; i--) {
                        stack.addFirst(tempNode.children.get(i));
                }
            }
            return result;
        }

        /**
         * 2.使用递归的方式
         *
         * @param root
         * @return
         */
        public List<Integer> preorder2(Node root) {
            List<Integer> list = new LinkedList<>();
            helper(root, list);
            return list;
        }

        /**
         * @param root
         * @param list
         */
        private void helper(Node root, List<Integer> list) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            for (Node node : root.children) {
                helper(node, list);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
