# Week02总结

## 哈希表、集合

哈希表又称为散列表。通过K-V来进行访问的数据结构。

通过把Key映射到表中的一个位置来访问记录，加快查找速度。

这个映射函数称为散列函数，存放记录的数组称为哈希表。

### Map、Set

Map：K-V键值对，Key不能重复

Set：不能存放重复元素

具体实现

TreeSet、HashSet

​	ConcurrentSkipListSet、CopyOnWriteArraySet、EnumSet、JobStateReasons、LinkedHashSet

HashMap、TreeMap、HashTable、ConcurrentHashMap



**HashSet也是通过HashMap来实现的。通过HashMap的Key不能重复的特性，来实现HashSet的元素不重复。**

### HashMap分析（Java1.8）

重点分析put()和get方法。

```java
/**
* 	HashMap分析参考:https://juejin.im/post/5dedb448f265da33b071716a
*	hash(key)函数的解析https://blog.csdn.net/qq_42034205/article/details/90384772
*/
public V put(K key, V value) {
	return putVal(hash(key), key, value, false, true);
}
//h >>> 16 取key的hashCode的高16位
//高16位与低16位进行异或操作，目的是为了让结果更加散列，减少哈希冲突
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
/**
 * Implements Map.put and related methods
 *
 * @param hash hash for key
 * @param key the key
 * @param value the value to put
 * @param onlyIfAbsent if true, don't change existing value
 * @param evict if false, the table is in creation mode.
 * @return previous value, or null if none
 */
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //table表示空的，表示是第一个元素，那么直接扩容
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //没有发生hash碰撞，直接创建新元素，放在index处
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else { // 发生了hash碰撞
        Node<K,V> e; K k;
        // 如果hash值相等，key也相等。那么覆盖之前的值
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else { //如果key不相等，那么插入到链表尾部
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    //如果插入节点后，链表的长度>=8，那么转化为红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        //存在被覆盖的节点
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            //空实现，用于LinkedHashMap的处理逻辑
            afterNodeAccess(e);
            //返回被覆盖之前的节点值
            return oldValue;
        }
    }
    //成功新增节点后，修改modCount
    ++modCount;
    //更新size，判断是否需要扩容
    if (++size > threshold)
        resize();
    //空实现，用于LinkedHashMap的处理逻辑
    afterNodeInsertion(evict);
    return null;
}

```





```java
public V get(Object key) {
    Node<K,V> e;
    //通过hash(key)与key的值共同确定Value
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}

final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    //容器不为为空的同时，然后再比较hash值，以及key，找到后返回
    //否则返回null
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```



## 树

树是一种由链表演变来的数据结构。一个节点同事拥有多个后继指针。

Link List 可以看成是一种特殊化的树。

而树又可以看成是一种特殊化的图。他们的区别是：树没有环。

### 二叉树

二叉树是指每个节点最多2个子节点的树。

#### 二叉树的遍历算法

```python
#DFS：前序、中序、后序遍历
def preorder(self, root): 
    if root: 
        self.traverse_path.append(root.val) 
        self.preorder(root.left) 
        self.preorder(root.right) 

def inorder(self, root):     
    if root: 
        self.inorder(root.left) 
        self.traverse_path.append(root.val) 
        self.inorder(root.right) 

def postorder(self, root): 
    if root: 
        self.postorder(root.left) 
        self.postorder(root.right) 
        self.traverse_path.append(root.val)
```



### 二叉搜索树

二叉搜索树又称为二叉查找树、有序二叉树、排序二叉树。

空树或者满足如下条件：

1. 左子树上**所有节点**的值均小于根节点的值。
2. 右子树上**所有节点**的值均大于根节点的值。
3. 以此类推，左右字数也分别为二叉搜索树。

它的中序遍历是升序排列的。

## 堆

堆是一种数据结构，能够迅速找到一推数中的最大值或者最小值。即要求findMax/findMin的时间复杂度是O(1)。

根节点最大的称为大顶堆（大根堆），根节点最小的称为小顶堆（小根堆）。

常见的实现由二叉堆、斐波那契堆等。

### 二叉堆

由完全二叉树实现。如果是大顶堆，需要满足以下条件：

1. 是一颗完全二叉树
2. 树种任意节点的值>=子节点的值

二叉堆一般用数组实现

因为是完全二叉树，假设有一个索引为i的节点：

- 其左子节点索引为 2*i + 1
- 其右子节点索引为 2*i + 2
- 其父节点的索引为 floor((i-1)/2)

Java中二叉堆的实现是PriorityQueue，默认是小顶堆。

二叉堆是堆的一种实现，并且不是最优的实现。他的插入和删除操作的时间复杂度是O(logn)。

## 图

### 图的定义

Graph(V,E)

- V-vertex：点

  1.度-入度和出度

  2.点与点之间：是否连通

- E-edge：边

  1.有向和无向

  2.权重（边长）

可分为：无向无权图、有向无权图、无向有权图、有向有权图



### 图的算法

```python
#DFS代码

visited = set() #和树中的DFS最大区别，需要记录遍历过的点

def dfs(node, visited):
	if node in visited: # terminator
		# already visited 
		return 
	visited.add(node) 
	# process current node here. 
	...
	for next_node in node.children(): 
		if not next_node in visited: 
			dfs(next_node, visited)
            
            
#BFS代码 , 使用队列结构

def bfs(graph, start, end):
    queue = [] 	
    queue.append([start]) 
    
    visited = set() # 和数中的BFS的最大区别
    
    while queue: 
        node = queue.pop() 
        visited.add(node)
        process(node) 
        nodes = generate_related_nodes(node) 
        queue.push(nodes)
```

