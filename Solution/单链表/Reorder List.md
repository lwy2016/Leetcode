Description:

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.



Solution:

将链表从中间分为2部分A, B 
反转 B 链表
将A与反转后的链表B 合并

```java
public ListNode getMiddleListNode(ListNode head){  // slow,fast方法 获取到链表中间的节点
    ListNode slow = head;
    ListNode fast = head;
    while(fast.next != null && fast.next.next != null){
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
public ListNode ReverseListNode(ListNode head){
    ListNode prev = null;
    while(head != null){
        ListNode next = head.next;
        
        head.next = prev;
        prev = head;
        
        head = next;
    }
    return prev;
}
public void reorderList(ListNode head) {
    if(head == null || head.next == null){
        return ;
    }
    
    ListNode middle = getMiddleListNode(head);
    ListNode next = middle.next;
    middle.next = null;
    
    // 反转 
    ListNode rHead = ReverseListNode(next);
    // head， next分别是两部分
    ListNode lHead = head;
    while(lHead != null && rHead != null){
        ListNode tempF = lHead.next;
        ListNode tempN = rHead.next;
        
        rHead.next = lHead.next;
        lHead.next = rHead;
        
        lHead = tempF;
        rHead = tempN;
    }
}
```