Description:

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

Solution:

1. 找到需要链表逆转的区间 slow-->m 的位置,fast-->n 的位置
2. 记录 slow 之前的位置 preslow, fast 后面的位置 postfast
3. 逆转 slow 到 fast 之间的链表，用 prev 记录链表头，判断条件 while(slow != postfast)
4. 连接三段链表

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode reverseBetween(ListNode head, int m, int n) {
    if(head == null) return null;
    
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    
    ListNode fast = dummy;
    ListNode slow = dummy;
    ListNode preslow = null;
    ListNode postfast = null;
    ListNode prev = null;
    ListNode prevnext = new ListNode(-1);
    
    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }
    for (int i = 0; i < m - 1; i++) {
        slow = slow.next;
    }
    
    postfast = fast.next;
    preslow = slow;
    slow = slow.next;
    
    while(slow != postfast) {
        ListNode temp = slow.next;
        slow.next = prev;
        prev = slow;
        slow = temp;
    }
    preslow.next = prev;
    prevnext.next = preslow;
    
    while(prevnext.next != null) {
        prevnext = prevnext.next;
    }
    prevnext.next = postfast;
    
    return dummy.next;
}
```


1. 由于只翻转指定区域，分析受影响的区域为第m-1个和第n+1个节点
2. 找到第m个节点，使用for循环n-m次，使用上题中的链表翻转方法
3. 处理第m-1个和第n+1个节点
4. 返回dummy->next

```java
/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public ListNode reverseBetween(ListNode head, int m, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    // find the mth node
    ListNode premNode = dummy;
    for (int i = 0; i < m - 1; i++) {
        premNode = premNode.next;
    }

    // reverse node between m and n 
    ListNode prev = null;
    ListNode curr = premNode.next;
    ListNode prevnext = new ListNode(-1);
    while(curr != null && (m <= n)) {
        ListNode nextNode = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextNode;
        m++;
    }
    prevnext.next = prev;
    while(prevnext.next != null) {
        prevnext = prevnext.next;
    }

    // join head and tail before m and after n 
    premNode.next.next = curr;
    premNode.next = prev;

    return dummy.next;
} 
```