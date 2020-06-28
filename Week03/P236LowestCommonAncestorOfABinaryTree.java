//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
// Related Topics 树

package algorithm10.week03;

import model.TreeNode;

import java.util.*;

public class P236LowestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {
        Solution solution = new P236LowestCommonAncestorOfABinaryTree().new Solution();
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

        //方法一、递归
        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode leftNode = lowestCommonAncestor1(root.left, p, q);
            TreeNode rightNode = lowestCommonAncestor1(root.right, p, q);
            if (leftNode == null) {
                return rightNode;
            }
            if (rightNode == null) {
                return leftNode;
            }
            return root;
        }

        //方法二、记录p和q的祖先节点路径
        //使用BFS，队列记录层序
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }
            Map<TreeNode, TreeNode> parentMap = new HashMap<>();
            parentMap.put(root, null);
            Deque<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            // 通过BFS遍历，知道parentMap的key中包含了p和q
            while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
                TreeNode temp = queue.removeFirst();
                if (temp.left != null) {
                    queue.addLast(temp.left);
                    parentMap.put(temp.left, temp);
                }
                if (temp.right != null) {
                    queue.addLast(temp.right);
                    parentMap.put(temp.right, temp);
                }
            }
            //从p到根节点的所有节点
            Set<TreeNode> ancestor_p = new HashSet<>();
            while (p != null) {
                ancestor_p.add(p);
                p = parentMap.get(p);
            }
            //遍历q的祖先节点，第一个在ancestor_p中包含的即为最近公共祖先
            while (!ancestor_p.contains(q)) {
                q = parentMap.get(q);
            }
            return q;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

