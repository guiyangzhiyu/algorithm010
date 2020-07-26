# Week07总结

## 字典树/Trie数

典型应用于**统计**和**排序**大量的字符串（不仅限于字符串），经常被搜索引擎系统用于文本词频统计。

### 基本性质

1. 节点本身不存完整单词
2. 从根节点到某一节点，路径上经过的字符连接起来为该节点对应的字符串
3. 每个节点的所有子节点路径代表的字符都不相同

### 核心思想

- 空间换时间
- 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的

### 代码实现

#### python版本

```python
class Trie(object): 
    def __init__(self): 
        self.root = {} 
        self.end_of_word = "#" 
    def insert(self, word): 
        node = self.root 
        for char in word: 
            node = node.setdefault(char, {}) 
            node[self.end_of_word] = self.end_of_word 
    def search(self, word): 
        node = self.root 
        for char in word: 
            if char not in node: 
                return False 
            node = node[char] 
            return self.end_of_word in node 
    def startsWith(self, prefix): 
        node = self.root 
        for char in prefix: 
            if char not in node: 
                return False 
            node = node[char] 
            return True
```

#### java版本

```java
class Trie {
    /**
    * define the node
    */
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
```



## 并查集/Disjoint Set

适用于**组团**、**配对**等场景。

### 基本操作

- makeSet(s)  建立一个新的并查集，其中包含s个单元素集合
- unionSet(x,y)  把元素x和元素y所在的集合合并，要求x、y所在的集合不想交，相交则不合并
- find(x)  找到x所在的集合代表，该操作可用开来判断2个元素是否位于同一个集合

### 代码实现

#### python版本

```python
def init(p): 
    # for i = 0 .. n: p[i] = i; 
    p = [i for i in range(n)] 
def union(self, p, i, j): 
    p1 = self.parent(p, i) 
    p2 = self.parent(p, j) 
    p[p1] = p2 
def parent(self, p, i): 
    root = i 
    while p[root] != root: 
        root = p[root] 
    while p[i] != i: # 路径压缩 ? 
        x = i; i = p[i]; p[x] = root 
    return root
```



#### java版本

```java
class UnionFind { 
    private int count = 0; 
    private int[] parent; 
    //建立n个并查集，每个都是单元素i
    public UnionFind(int n) { 
        count = n; 
        parent = new int[n]; 
        for (int i = 0; i < n; i++) { 
            parent[i] = i;
     	} 
    } 
    //可以使用路径压缩
    public int find(int p) { 
        while (p != parent[p]) { 
            parent[p] = parent[parent[p]]; 
            p = parent[p]; 
        }
        return p; 
    }
    //合并p、q对应的集合
    public void union(int p, int q) { 
        int rootP = find(p); 
        int rootQ = find(q); 
        if (rootP == rootQ) return; 
        parent[rootP] = rootQ; 
        count--;
	} 
}
```

## 搜索

### 初级搜索

对于朴素搜索的优化方向：**不重复、剪枝**。

由DFS,BFS   ---->   双向BFS搜索，启发式搜索（优先队列+估价函数/启发式函数）。

**重复重复重复重复重重复重重复重重复重重复重重复重重复重重复重重复重重复重重复重**

```python
#DFS 递归
visited = set()

def dfs(node , visited) :
    #terminator
    if node in visited :
        return
    
    visited.add(node)
    # current process
    
    for next_node in node.children() :
        if not next_node in visited:
            #drll down
            def(next_node , visited)
            
#DFS 非递归

def DFS(self , tree):
    if tree.root is None :
        return []
    visited , stac = [] , [tree.root]
    
    while stack :
        node = stack.pop()
        visited,add(node)
        process(node)
        
        nodes = generate_related_nodes(node)
        stack.push(nodes)
        
```

```python
#BFS
def BFS (graph, start , end) :
    queue = []
    queue.append([start])
    
    while queue:
        node = queue.pop()
        visited.add(node)
        
        process(node)
        nodes = generate_related_nodes(node)
        queue.push(nodes)
```

重在**实战理解**

## 二叉搜索树的优化

为了保证二叉搜索树的二维维度，避免其退化为链表的形态。

### AVL树

1. 由发明者G. M. Adelson-Velsky和 Evgenii Landis命名
2. Balance Factor（平衡因子）：是它的左子树的高度减去它的右子树的高度（有时相反）balance factor = {-1, 0, 1}
3. 通过旋转操作进行平衡（四种）

#### 四种旋转操作

- 左左字数	右旋
- 右右字数    左旋
- 左右子树    左右旋
- 右左子树    右左旋

**注意带子树的旋转情况**

#### 总结

1. 严格的平衡二叉搜索树
2. 每个节点存平衡因子 balance factor = {-1.0.1}
3. 四种旋转操作
4. 节点需要存储额外信息，且调整次数频繁

### **红黑树**

红黑树是一种**近似平衡**的二叉搜索树，它能够保证每个节点的左右子树的**高度差小于2倍。**

- 每个节点要么红色，要么黑色
- 根节点是黑色
- 叶子节点是黑色
- **不能有相邻的两个红色节点**
- **从任一节点到其每个叶子节点的所有路径都包含相同数目的叶子节点**

### AVL树和红黑树对比

- AVL trees provide **faster lookups** than Red Black Trees because they are **more strictly balanced.**
- Red Black Trees provide **faster insertion and removal** operations than AVL trees as fewer roations are done due to relatively relaxed balancing.
- AVL trees store **balance factors or heights** with each node,thus requires storage for an integer per node whereas     Red Black Tree requires only 1 bit of information per node.
- Red Black Trees are used in most of **language libraries like map,multimap,multisetin C++** whereas AVL trees are used in **databases** where faster retrievals are required.