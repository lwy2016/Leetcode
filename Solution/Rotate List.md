Description:

Given a list, rotate the list to the right by k places, where k is non-negative.

Example:

Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL.

Solution:

本题最大的问题在于，边界值
旋转链表，链表类问题通常需要找到需要处理节点处的前一个节点。因此我们只需要找到旋转节点和最后一个节点即可。需要注意的细节是 k 有可能比链表长度还要大，此时需要取模，另一个 corner case 则是链表长度和 k 等长。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode rotateRight(ListNode head, int k) {
    if(head == null) return head;

    ListNode fast = head;
    ListNode slow = head;

    int len = 1;
    for (len = 1; fast.next != null && len <= k; len++) {
        fast = fast.next;
    }
    if(len <= k) {  // k == len 的情况
        k = k % len;
        fast = head;
        for (int i = 0; i < l; i++) {
            fast = fast.next;
        }
    }
    while(fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }

    fast.next = head;
    head = slow.next;
    slow.next = null;

    return head;
}
```

