//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
//个问题。 
//
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。 
// Related Topics 树 设计

package algorithm10.week03;

import model.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P297SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        Codec solution = new P297SerializeAndDeserializeBinaryTree().new Codec();
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
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            //使用先序遍历，转化为字符串
            return preOrderSerialize(root, "");
        }

        private String preOrderSerialize(TreeNode root, String s) {
            if (root == null) {
                s += "Null,";
            } else {
                s += String.valueOf(root.val) + ",";
                s = preOrderSerialize(root.left, s);
                s = preOrderSerialize(root.right, s);
            }
            return s;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            //把字符串转化为List
            String[] tempStrs = data.split(",");
            return deserializeHelper(new LinkedList<>(Arrays.asList(tempStrs)));
        }

        /**
         * 把先序集合转化为一颗二叉树
         *
         * @param asList
         */
        private TreeNode deserializeHelper(List<String> asList) {
            if (asList.get(0).equals("Null")) {
                asList.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(asList.get(0)));
            asList.remove(0);
            root.left = deserializeHelper(asList);
            root.right = deserializeHelper(asList);
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}

