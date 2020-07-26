//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树

package algorithm10.week07;

public class P208ImplementTriePrefixTree {

    public static void main(String[] args) {
        Trie solution = new P208ImplementTriePrefixTree().new Trie();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {
        //定义Trie的节点
        class TrieNode {
            private TrieNode[] childs;
            private int R = 26;
            private boolean isEnd;

            public TrieNode() {
                childs = new TrieNode[R];
            }

            public boolean containsKey(char ch) {
                return childs[ch - 'a'] != null;
            }

            public void put(char ch, TrieNode node) {
                childs[ch - 'a'] = node;
            }

            public TrieNode get(char ch) {
                return childs[ch - 'a'];
            }

            public void setEnd() {
                isEnd = true;
            }

            public boolean isEnd() {
                return isEnd;
            }
        }

        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                    node.put(ch, new TrieNode());
                }
                node = node.get(ch);
            }
            node.setEnd();
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }

        /**
         * search a prefix or whole key in the trie
         *
         * @param prefix
         * @return the node where the search ends
         */
        public TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.containsKey(ch)) {
                    node = node.get(ch);
                } else {
                    return null;
                }
            }
            return node;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

