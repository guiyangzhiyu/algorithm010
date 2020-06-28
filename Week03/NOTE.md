# Week03总结

## 递归

```java
//递归的代码模板
public void recur(int level,int param) {
	// terminate
	if (level > MAX_VALUE) {
		return;
	}
	// process current logic
	...
	// drill down
	scur(level + 1, newParam);
	// reverse states
}
```

- 避免使用人肉递归
- 找到最近最简问题，拆分为可重复解决的问题（最近重复子问题）
- 善用数学归纳法思维

## 分治

把一个规模很大、很复杂的问题分解成几个子问题。求出这几个子问题的解，然后再将他们组合成整个问题的解。

```python
# 分治代码模板
def divide_conquer(problem, param1, param2, ...): 
    # recursion terminator 
    if problem is None: 
        print_result 
        return 
    # prepare data 
    data = prepare_data(problem) 
    subproblems = split_problem(problem, data) 
    # conquer subproblems 
    subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
    subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
    subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
    ...
    # process and generate the final result 
    result = process_result(subresult1, subresult2, subresult3, …) 

    # revert the current level states
```



## 回溯

回溯问题，实际上是一个决策树的遍历过程

1. 路径：已经做出的选中
2. 选择列表：当前可以做出的选中
3. 结束条件：达到决策树底层，无法再做出选择的条件

```python
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

**核心是for循环里面的递归，在递归调用之前做选择，递归调用之后，撤销选择。**