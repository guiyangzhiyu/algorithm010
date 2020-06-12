package algorithm10.week01;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 2020/6/12
 * @auther weixiang
 */
public class DequeDemo {
    public static void main(String[] args) {
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
        //When a deque is used as a stack, elements are pushed and popped from the beginning of the deque
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
        // Elements are added at the end of the deque and removed from the beginning.
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
    }
}
