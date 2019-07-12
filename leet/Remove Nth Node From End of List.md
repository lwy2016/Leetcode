Description:

Given a linked list, remove the nth node from the end of list and return its head.

For example,
>Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass.

Solution:

双指针 Two Points

LinkedList 一些容易犯的错误是：

1. 越界：容易造成内存访问错误，比如调用了NULL->next。尤其对于空链表的特殊情况。
2. 更新head的特殊处理
3. 删除节点时没有保留下一个移动位置的指针（多用于reverse linked list）。
4. 移动位置存在+-1的偏差。 
5. 链表题多用指针操作。注意指针作为函数参数时是pass by value： f(ListNode *p)，还是pass by reference：f(ListNode *&p)

常用技巧：
1. Dummy head：简化改变、删除头指针的处理。
2. 前后双指针：多用于链表反转。

我的，一般般的解法
```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode p = head;
    int count = 0;
    while(p != null){
        count ++;
        p = p.next;
    }
    if(count == 1){
        return null;
    }
    if(count == n){
        return head.next;
    }
    p = head;
    for(int i = 0; i < count - n - 1; i++){
        head = head.next;
    }
    head.next = head.next.next;
    
    return p;
}
```

喜刷刷的解法：
个关键点：
1. 找到倒数第n+1个节点。
2. 通过找到的节点来删除倒数第i个节点。

如何找到倒数第k个节点?双指针before, after。让before先从head开始走k步，然后双指针同时走，当before为NULL时，after所指的就是倒数第k个节点。以题中例子说明：
k=2

 1 -> 2 -> 3 -> 4 -> 5 -> NULL
 |         |
after    before

 1 -> 2 -> 3 -> 4 -> 5 -> NULL
                |          |
              after     before

需要注意的corner case （尽管LeetCode的test case中n总是小于等于链表长度）
1. k<1
2. k>链表长度，当before从head移动i < k步就已经指向NULL。而如果正好移动k步指向NULL，则k即为链表长度。
3. 要删除的节点为头节点，对应k=链表长度。判断条件在2中已说明              

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode before = dummy;
    ListNode after = dummy;
    // Advances before pointer so that the gap between before and after is n nodes apart
    for (int i = 1; i <= n + 1; i++) {   // 走 n 步
        before = before.next;
    }
    // Move before to the end, maintaining the gap
    while (before != null) {
        before = before.next;
        after = after.next;
    }
    after.next = after.next.next;
    return dummy.next;
}
```

大概2个月之后，自己手撕代码，与上面的解法完全相同，想法很重要

使用快慢指针解决此题，需要注意最后删除的是否为头节点。让快指针先走n步，直至快指针走到终点，找到需要删除节点之前的一个节点，改变node->next域即可
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode removeNthFromEnd(ListNode head, int n) {
    if(head == null) return null;

    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode fast = dummy;
    ListNode slow = dummy;

    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }
    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }
    slow.next = slow.next.next;

    return dummy.next;
}
```