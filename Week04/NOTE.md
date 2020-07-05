# Week04总结

## 深度优先搜索DFS

树的前、中、后序遍历都属于DFS。一般有递归和非递归两种写法。非递归是借助Stack的方式迭代

```python
# 递归模板
visited = set()

def dfs(node,visited)：
	# terminator
	if node in visited:
        return
    
    visited.add(node)
    
    # process current node here
    ...
    # drill down
    for next_node in node.children():
        if not next_node in vidited:
            dfs(next_node,visited)
    
```

```python
# 非递归模板
def dfs(self,tree):
    if tree.root is Nome:
        return[]
    visited,stack = [].[tree.root]
    
    while stack:
        node = stack.pop()
        visited.add(node)
        
        process(node)
        nodes = generate_nodes(node)
        stack.push(nodes)
        
```



## 广度优先搜索BFS

BFS没有递归的写法，借助queue来实现

```python
# bfs模板
def bfs(graph,start,end):
    queue = []
    queue.append([start])
    visited.add(start)
    
    while queue:
        node = queue.pop()
        visited.add(node)
        
        process
        nodes = generate_nodes(node)
        queue.push(nodes)
        
```



## 贪心算法

贪心算法实在每一步的选择中都采取当前最优，从而希望结果是全局最优。

贪心算法一旦做出选择就不能回退。

在实际使用中，能够使用贪心算法的一般都是其最优解，在使用前需要证明其满足最优子结构。

子问题的最优解能够递推得出最终问题的最优解，**最优子结构**。

## 二分查找

二分查找有以下前提：

- 目标函数的单调性
- 存在上下界
- 能够通过索引访问



```python
# 二分查找代码模板
left，right = 0,len(array) - 1
while left <= right:
    mid = left + (right -left)/2
    if array[mid] == target:
        return
    elif array[mid] < target:
        left = mid + 1
    else:
        right = mid - 1
    
```

## 寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

寻找无序的地方即寻找数组中的**最小值**

```Java
public int findMin(int[] nums) {
    int start = 0,end = nums.length() - 1;
    while (start < end) {
        if (nums[start] < nums[end]) {
            return nums[start];
        } 
        
        int mid = start + (end - start)/2;
        // 最小值在右侧
        if (nums[mid] >= nums[left]) {
            start = mid + 1;
        } else {
            end = mid;
        }
    }
    return nums[start];
}
```

