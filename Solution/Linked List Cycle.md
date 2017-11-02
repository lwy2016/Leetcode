Description:

Given a linked list, determine if it has a cycle in it.
Follow up:
Can you solve it without using extra space?

Solution:

slow-fast方法

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public boolean hasCycle(ListNode head) {
    // 当有节点相等的时候，说明有环
    if(head == null || head.next == null){
        return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast){
            return true;
        }
    }
    return false;
} 
```