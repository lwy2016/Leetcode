Description:

Given a list, rotate the list to the right by k places, where k is non-negative.

Example:

Given 1->2->3->4->5->NULL and k = 2,

return 4->5->1->2->3->NULL.

Solution:

本题最大的问题在于，边界值
旋转链表，链表类问题通常需要找到需要处理节点处的前一个节点。因此我们只需要找到旋转节点和最后一个节点即可。需要注意的细节是 k 有可能比链表长度还要大，此时需要取模，另一个 corner case 则是链表长度和 k 等长。

左旋转链表，相当于将链表分为2部分，使用slow-fast双指针，找到两段链表的首尾节点
分别是 head-slow, slow.next-fast节点，
然后重新指向两段链表的指向

手撕
```java
public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return null;
    
    ListNode fast = head;
    ListNode slow = head;
    ListNode list = head;
    
    // 对k取余，得到最小旋转次数
    int len = 0;
    while (list != null) {
        len++;
        list = list.next;
    }
    k = k % len;
    
    int i = 1;
    for (i = 1; fast.next != null && i <= k; i++) {
        fast = fast.next;
    }
    
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }
    // 转换两个链表的方向
    fast.next = head;
    head = slow.next;
    slow.next = null;
    
    return head;
}
```

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
        for (int i = 0; i < len; i++) {
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

