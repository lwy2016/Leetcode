Description:

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

Solution:

slow-fast方法

```java
/*
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
public ListNode detectCycle(ListNode head) {
    while(head == null || head.next == null){
        return null;
    }
    ListNode slow = head;
    ListNode fast = head;
    
    while(fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
        
        if(slow == fast){
            fast = head;   // 回到头结点开始遍历
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
    return null;
} 
```
