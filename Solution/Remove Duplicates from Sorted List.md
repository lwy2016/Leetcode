Description:

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

Solution:

遍历之，遇到当前节点和下一节点的值相同时，删除下一节点，并将当前节点next值指向下一个节点的next,当前节点首先保持不变，直到相邻节点的值不等时才移动到下一节点。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode deleteDuplicates(ListNode head) {
    if(head == null) return null;
    ListNode dummy = head;
    ListNode list = dummy;
    
    while(list.next != null) {
        if(list.val == list.next.val) {
            list.next = list.next.next;
        } else {
            list = list.next;
        }
    }
    return dummy;
}
```