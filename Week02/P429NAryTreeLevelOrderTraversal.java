//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索


package algorithm10.week02;
//java:N叉树的层序遍历

import model.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P429NAryTreeLevelOrderTraversal {
    public static void main(String[] rgs) {
        Solution solution = new P429NAryTreeLevelOrderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    class Solution {
        List<List<Integer>> result = new ArrayList<>();

        /**
         * 使用广度优先搜索，队列
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(Node root) {
            Deque<Node> queue = new LinkedList<>();
            if (root == null) {
                return  result;
            }
            queue.addLast(root);
            while (!queue.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.removeFirst();
                    list.add(node.val);
                    for (Node tempNode : node.children) {
                        queue.addLast(tempNode);
                    }
                }
                result.add(list);
            }
            return result;
        }


        /**
         * 使用递归的方式，记录每层的数据
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder2(Node root) {
            if (root == null) {
                return result;
            }
            levelOrderHelper(root,0);
            return result;
        }

        /**
         *
         * @param root
         * @param i 当前的层级
         */
        private void levelOrderHelper(Node root, int i) {
            if (result.size() <= i) {
                result.add(new ArrayList<>());
            }
            result.get(i).add(root.val);
            for (Node node : root.children) {
                levelOrderHelper(node,i+1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
