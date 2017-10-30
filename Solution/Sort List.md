Description:



Solution:

O(nlogn)的排序算法有quick sort, heap sort, merge sort。对于链表排序，难点之一就是如何O(1)定位节点。如果是数组，那么可以通过下标直接找到节点。但对于链表，如果需要定位到第K个元素，只能从节点头部顺序的访问K次，但是，如果排序中每一个定位操作都要这样做的话就太慢了。问题其实就是，如何能够节省链表节点的定位时间。如果采用merge sort的话，就可以通过递归的特性来避免这个时间损耗。

找到链表的middle节点，然后递归对前半部分和后半部分分别进行归并排序，最后对两个已排好序的链表进行Merge

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
    ListNode getMiddleOfList(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public ListNode sortList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode middle = getMiddleOfList(head);
        ListNode next = middle.next;
        middle.next = null;
        return megerList(sortList(head), sortList(next));
    }
    ListNode megerList(ListNode l1, ListNode l2){
        ListNode list = new ListNode(-1), cur = list;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
        }
        cur.next = (l1 != null) ? l1 : l2;
        return list.next;
    }
```