# Week01总结

## 数组、链表、跳表

- 数组是线性结构，在物理上的存储是连续的。查询操作很快，增加和删除节点会调用以下方法，因此效率较低：

```java
System.arrayCopy()
```

- 链表仅在逻辑上连续，可分为单链表，双向链表，循环链表等。其插入和删除操作不涉及到大量元素的复制操作，时间复杂度较低。但是查询操作需要遍历整个链表，时间复杂度较高。
- 跳表是在链表基础上的改进，通过添加多级索引的方式，加速链表的查询速度，但是会使得增加和删除操作降速。

| 数据结构/操作        | 增      | 删      | 查      |
| -------------------- | ------- | ------- | ------- |
| 数组                 | O(n)    | O(n)    | O(1)    |
| 链表                 | O(1)    | O(1)    | O(n)    |
| 跳表(对链表添加索引) | O(logn) | O(logn) | O(logn) |

需要根据实际的应用场景合理选择。

## 栈、队列、双端队列、优先队列

### 改写Deque代码

```Java
		//之前的写法
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);

        //当做Stack使用
        //elements are pushed and popped from the beginning of the deque
        Deque<String> deque2 = new LinkedList<String>();
        deque2.addFirst("a");
        deque2.addFirst("b");
        deque2.addFirst("c");
        System.out.println(deque2);
        String str2 = deque2.getFirst();
        System.out.println(str2);
        System.out.println(deque2);
        while (deque2.size() > 0) {
            System.out.println(deque2.removeFirst());
        }
        System.out.println(deque2);

        //当做queue使用的时候，
        //Elements are added at the end of the deque and removed from the beginning.
        Deque<String> deque3 = new LinkedList<String>();
        deque3.addLast("a");
        deque3.addLast("b");
        deque3.addLast("c");
        System.out.println(deque3);
        String str3 = deque3.getFirst();
        System.out.println(str3);
        System.out.println(deque3);
        while (deque3.size() > 0) {
            System.out.println(deque3.removeFirst());
        }
        System.out.println(deque3);
```



### Queue源码分析

队列是一种具有“先进先出”特点的数据结构

```java
/**
*
* 继承自Collection的接口
*/
public interface Queue<E> extends Collection<E> {
    /**
     * 向队列中添加元素。空间够，返回true;空间不够报异常IllegalStateException
     */
    boolean add(E e);

    /**
     * 向队列中添加元素。插入成功，返回true；失败，返回false
     */
    boolean offer(E e);

    /**
     * 移除并返回队列的头元素，如果队列为空，报异常NoSuchElementException
     */
    E remove();

    /**
     * 移除并返回队列的头元素，如果队列为空，返回null
     */
    E poll();

    /**
     * 返回队列的头元素，如果队列为空，报异常NoSuchElementException
     */
    E element();

    /**
     * 返回队列的头元素，如果队列为空，返回null
     */
    E peek();
}
```

### PriorityQueue分析

- PriorityQueue是一个基于priority heap 的无限优先队列。
- 不能插入null
- 插入的元素必须实现Comparable接口，或者在构造方法中传入一个Comparator对象，优先使用Comparator。