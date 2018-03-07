Description:

Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Solution:


非递归
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode reverseList(ListNode head) {
    // 熟练默写
    if (head == null) return null;
    
    ListNode prev = null;
    while(head != null) {
        ListNode temp = head.next;
        head.next = prev;
        prev = head;
        head = temp;
    }
    
    return prev;
}
```

递归版
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode reverseList(ListNode head) {
    return reverse(head, null);
}

private ListNode reverse(ListNode head, ListNode prev) {
    if (prev == null) return prev;

    ListNode temp = head.next;
    head.next = prev;

    return reverse(temp, head); 
}
```