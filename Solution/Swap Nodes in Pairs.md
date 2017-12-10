Description:

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

Solution:

迭代解法：

类似逆转单链表，只不过一次需要处理两个节点，注意对最后没有进行交换节点的处理
这里使用 dummy 处理不定头节点

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode swapPairs(ListNode head) {
    if(head == null || head.next == null) return head;

    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode list = dummy;

    while(head != null && head.next != null) {
        ListNode temp = head.next.next;

        list.next = head.next;
        list.next.next = head;
        list = list.next.next;

        head = temp;
    }

    list.next = head;

    return dummy.next;
}
```

递归解法：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode after = head.next;
    head.next = swapPairs(after.next);
    after.next = head;

    return after;
} 
```
