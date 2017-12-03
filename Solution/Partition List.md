Description:

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

Solution:

使用两个dummy节点，left指向小于 x 的节点， right指向大于 x 的节点


```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode partition(ListNode head, int x) {
    if(head == null) return null;
    
    ListNode leftdummy = new ListNode(-1);
    ListNode left = leftdummy;
    ListNode rightdummy = new ListNode(-1);
    ListNode right = rightdummy;
    
    while(head != null) {
        if(head.val < x) {
            left.next = head;
            left = left.next;
        } else {
            right.next = head;
            right = right.next;
        }
        head = head.next;
    }
    right.next = null;
    left.next = rightdummy.next;
    
    return leftdummy.next;
}
```