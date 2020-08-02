# Week08总结

## 位运算

| 含义                     | 运算符 | 实例                     |
| ------------------------ | ------ | ------------------------ |
| 左移                     | <<     | 0011  -->  0110          |
| 右移                     | >>     | 0110  -->  0011          |
| 按位或                   | \|     | 0100 \| 1011   -->  1111 |
| 按位与                   | &      | 0011 & 1011  -->  0011   |
| 按位取反                 | ~      | ~1100   -->  0011        |
| 按位异或(相同为0不同为1) | ^      | 0011 ^ 1011  -->  1000   |

### 异或操作的一些用法

- X ^ 0 = X
- X ^ 1s = ~X  (1s = ~0)
- X ^ (~X) = 1s
- X ^ X = 0
- c = a ^ b;      a ^ c == b , b ^ c == a  //交换两个数
- a^b^c  = (a^b)^c = a^(b^c)

### 指定位置的位运算

1. 将x最右边的n位清零：x & (~0 << n)
2. 获取x的第n位值（0或者1）：(x >> n) & 1
3. 获取x的第n位的幂值：x & (1 << n)
4. 仅将第n位置为1：x | (1 << n)
5. 仅将第n位置为0：x & (~(1 << n))
6. 将x最高位至第n位（包含）清零：x & ((1 << n) - 1)
7. 将第n位至第0位（包含）清零：x & (~((x << (n + 1)) - 1))

### 实战要点

- 判断奇偶：   x & 1 == 1 奇数；x & 1 == 0 偶数
- x  >> 1   -->   x/2
- x = x & (x - 1)清零最低位的1
- x & -x  得到最低位的1
- x & ~x  --> 0

## 布隆过滤器

一个很长的**二进制向量**和一系列**随机映射函数**。布隆过滤器可以用于检索一个元素是否在一个集合中。 

- **优点是空间效率和查询时间都远远超过一般的算法，** 
- **缺点是有一定的误识别率和删除困难**
- **可以确定元素不存在，但是无法确定存在**

```python
#Python
from bitarray import bitarray 
import mmh3 

class BloomFilter: 
    def __init__(self, size, hash_num): 
        self.size = size 
        self.hash_num = hash_num 
        self.bit_array = bitarray(size) 
        self.bit_array.setall(0) 
    def add(self, s): 
        for seed in range(self.hash_num): 
        	result = mmh3.hash(s, seed) % self.size 
        	self.bit_array[result] = 1
    def lookup(self, s): 
        for seed in range(self.hash_num): 
            result = mmh3.hash(s, seed) % self.size 
            if self.bit_array[result] == 0: 
        		return "Nope"
        return "Probably
```

```java
//Java
package com.github.lovasoa.bloomfilter;

import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

public class BloomFilter implements Cloneable {
  private BitSet hashes;
  private RandomInRange prng;
  private int k; // Number of hash functions
  private static final double LN2 = 0.6931471805599453; // ln(2)

  /**
   * Create a new bloom filter.
   * @param n Expected number of elements
   * @param m Desired size of the container in bits
   **/
  public BloomFilter(int n, int m) {
    k = (int) Math.round(LN2 * m / n);
    if (k <= 0) k = 1;
    this.hashes = new BitSet(m);
    this.prng = new RandomInRange(m, k);
  }

  /**
   * Create a bloom filter of 1Mib.
   * @param n Expected number of elements
   **/
  public BloomFilter(int n) {
    this(n, 1024*1024*8);
  }

  /**
  * Add an element to the container
  **/
  public void add(Object o) {
    prng.init(o);
    for (RandomInRange r : prng) hashes.set(r.value);
  }
  /** 
  * If the element is in the container, returns true.
  * If the element is not in the container, returns true with a probability ≈ e^(-ln(2)² * m/n), otherwise false.
  * So, when m is large enough, the return value can be interpreted as:
  *    - true  : the element is probably in the container
  *    - false : the element is definitely not in the container
  **/
  public boolean contains(Object o) {
    prng.init(o);
    for (RandomInRange r : prng)
      if (!hashes.get(r.value))
        return false;
    return true;
  }

  /**
   * Removes all of the elements from this filter.
   **/
  public void clear() {
    hashes.clear();
  }

  /**
   * Create a copy of the current filter
   **/
  public BloomFilter clone() throws CloneNotSupportedException {
    return (BloomFilter) super.clone();
  }

  /**
   * Generate a unique hash representing the filter
   **/
  public int hashCode() {
    return hashes.hashCode() ^ k;
  }

  /**
   * Test if the filters have equal bitsets.
   * WARNING: two filters may contain the same elements, but not be equal
   * (if the filters have different size for example).
   */
  public boolean equals(BloomFilter other) {
    return this.hashes.equals(other.hashes) && this.k == other.k;
  }

  /**
   * Merge another bloom filter into the current one.
   * After this operation, the current bloom filter contains all elements in
   * other.
   **/
  public void merge(BloomFilter other) {
    if (other.k != this.k || other.hashes.size() != this.hashes.size()) {
      throw new IllegalArgumentException("Incompatible bloom filters");
    }
    this.hashes.or(other.hashes);
  }

  private class RandomInRange
      implements Iterable<RandomInRange>, Iterator<RandomInRange> {

    private Random prng;
    private int max; // Maximum value returned + 1
    private int count; // Number of random elements to generate
    private int i = 0; // Number of elements generated
    public int value; // The current value

    RandomInRange(int maximum, int k) {
      max = maximum;
      count = k;
      prng = new Random();
    }
    public void init(Object o) {
      prng.setSeed(o.hashCode());
    }
    public Iterator<RandomInRange> iterator() {
      i = 0;
      return this;
    }
    public RandomInRange next() {
      i++;
      value = prng.nextInt() % max;
      if (value<0) value = -value;
      return this;
    }
    public boolean hasNext() {
      return i < count;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
```



## LRU算法

- **两个要素： 大小 、替换策略**
- **Hash Table + Double LinkedList**
- **O(1) 查询** 
- **O(1) 修改、更新**



```python
#Python
class LRUCache(object): 
    def __init__(self, capacity): 
        self.dic = collections.OrderedDict() 
        self.remain = capacity
    def get(self, key): 
        if key not in self.dic: 
        	return -1
		v = self.dic.pop(key) 
		self.dic[key] = v # key as the newest one 
		return v 
    def put(self, key, value): 
        if key in self.dic: 
        	self.dic.pop(key) 
    	else: 
    		if self.remain > 0: 
    			self.remain -= 1
    		else: # self.dic is full
                self.dic.popitem(last=False) 
        self.dic[key] = value
```

```java
//Java
public class LRUCache {
 
     private Map<Integer, Integer> map;

     public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
     }

     public int get(int key) {
         if(!map.containsKey(key)) { 
             return -1; 
         }
         return map.get(key);
     }

     public void put(int key, int value) {
        map.put(key,value);
     }
    
     private static class LinkedCappedHashMap<K,V> extends LinkedHashMap<K,V> {
         int maximumCapacity;
         LinkedCappedHashMap(int maximumCapacity) {
             //true使用读取顺序保存
             super(16, 0.75f, true);
             this.maximumCapacity = maximumCapacity;
         }
         protected boolean removeEldestEntry(Map.Entry eldest) {
             return size() > maximumCapacity;
     	 }
     } 
}
```



## 各种排序

```java
//选择排序
public void sort(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < n - 1; i++) {
        int min = i;
        for (int j = i + 1; j < N; j++) {
            if (nums[j] < nums[min]) {
                min = j;
            }
        }
        swap(nums, i, min);
    }
}

```

```java
//插入排序
public void sort(int[] nums) {
    int n = nums.length;
    for (int i = 1; i < n; i++) {
        for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
            swap(nums, j, j - 1);
        }
    }
}
```

```java
//冒泡排序
public void sort(T[] nums) {
    int n = nums.length;
    boolean isSorted = false;
    for (int i = n - 1; i > 0 && !isSorted; i--) {
        isSorted = true;
        for (int j = 0; j < i; j++) {
            if (nums[j + 1] < nums[j]) {
                isSorted = false;
                swap(nums, j, j + 1);
            }
        }
    }
}
```

