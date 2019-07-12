Description:

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Solution:

迭代调用mergeTwoLists，先合并链表1和2，接着将合并后的链表再与链表3合并，直到最后

在LeetCode中Time Limit Exceeded 超时了(239ms)，在牛客网上测试通过

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode mergeKLists(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;
    
    ListNode dummy = new ListNode(-1);
    ListNode list = dummy;
    
    for (int i = 0; i < lists.length; i++) {
        list = mergeTwoLists(list, lists[i]);
    }
    
    return dummy.next;
}

private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode list = dummy;
    
    while(l1 != null && l2 != null) {
        if(l1.val < l2.val) {
            list.next = l1;
            l1 = l1.next;
        } else {
            list.next = l2;
            l2 = l2.next;
        }
        list = list.next;
    }
    list.next = (l1 != null) ? l1 : l2;
    
    return dummy.next;
}
```

二分调用mergeTwoLists
重新再 LeetCode上跑了一遍 (3ms) Amazing

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public ListNode mergeKLists(ListNode[] lists) {
    if(lists == null || lists.length == 0) return null;
    
    return helper(lists, 0, lists.length - 1);
}

private ListNode helper(ListNode[] lists, int start, int end) {
    if(start == end) {
        return lists[start];
    } else if(start + 1 == end) {
        return mergeTwoLists(lists[start], lists[end]);
    }
    
    ListNode left = helper(lists, start, start + (end - start)/2);
    ListNode right = helper(lists, start + (end - start)/2 + 1, end);
    
    return mergeTwoLists(left, right);
}

private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode list = dummy;
    
    while(l1 != null && l2 != null) {
        if(l1.val < l2.val) {
            list.next = l1;
            l1 = l1.next;
        } else {
            list.next = l2;
            l2 = l2.next;
        }
        list = list.next;
    }
    list.next = (l1 != null) ? l1 : l2;
    
    return dummy.next;
}
```