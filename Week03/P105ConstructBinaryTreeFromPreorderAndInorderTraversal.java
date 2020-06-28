//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组

package algorithm10.week03;

import model.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
        Map<Integer,Integer> hashMap;
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = preorder.length;
            hashMap = new HashMap<>();
            //中序遍历的索引
            //因为没有重复节点，所以可以保证唯一
            for (int i = 0; i < n; i++) {
                hashMap.put(inorder[i],i);
            }
            return helper(preorder,inorder,0,n-1,0,n-1);
        }

        private TreeNode helper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }
            //前序的第一个节点
            int root_pre = preStart;
            //中序遍历中根节点的index
            int root_in = hashMap.get(preorder[preStart]);
            //根节点是preorder的第一个节点
            TreeNode rootNode = new TreeNode(preorder[preStart]);
            //左子树的节点数
            int leftSize = root_in - inStart;
            //递归左子树
            rootNode.left = helper(preorder,inorder,preStart+1,preStart+1+leftSize,inStart,root_in-1);
            //递归右子树
            rootNode.right = helper(preorder,inorder,preStart+1+leftSize,preEnd,root_in+1,inEnd);
            return rootNode;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

