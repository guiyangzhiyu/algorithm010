//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表


package algorithm10.week01;
//java:反转链表

import model.ListNode;

public class P206ReverseLinkedList {
    public static void main(String[] rgs) {
        Solution solution = new P206ReverseLinkedList().new Solution();
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null, next = head;
            while (next != null) {
                //保存下一个节点
                ListNode tempNode = next.next;
                //next节点连接pre，并且更新pre指针
                next.next = pre;
                pre = next;
                //更新下一个节点
                next = tempNode;
            }
            return pre;
        }
    }

}
