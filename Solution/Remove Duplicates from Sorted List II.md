Description:

Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Solution:

处理链表头节点不确定的方法——引入dummy node.

我手撕的代码
每次循环，最后一次处理重复的第一个值，如 2(1)->2(2)->2(3)->3->4  即是最后一次删除的值是 2(1)
```java
public ListNode deleteDuplicates(ListNode head) {
    if(head == null) return null;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode list = dummy;
    
    while(list.next != null && list.next.next != null) {
        if(list.next.val == list.next.next.val) {
            list.next.next = list.next.next.next;
            if(list.next.next == null || list.next.val != list.next.next.val) {
                list.next = list.next.next;
            }
        } else {
            list = list.next;
        }
    }
    return dummy.next;
}
```

大神的代码
每次循环，依次删除第一个重复的值，如 2(1)->2(2)->2(3)->3->4 即是依次删除 2(1), 2(2), 2(3)
```java
public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode node = dummy;
    while(node.next != null && node.next.next != null) {
        if (node.next.val == node.next.next.val) {
            int val_prev = node.next.val;
            while (node.next != null && node.next.val == val_prev) {
                node.next = node.next.next;
            }
        } else {
            node = node.next;
        }
    }

    return dummy.next;
}
```
