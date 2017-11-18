Description:

Sort a linked list using insertion sort.

Solution:

拿出原 list 的头节点，用一个指针扫描新list直到找到插入位置，并插入
用 dummy 来简化新 list 的头结点操作
由于插入节点需要依赖插入的前一个节点，所以用指针 p 来查找新list节点时，始终用p->next来和要插入的节点比较
注意当节点需要插入新list尾部的情况

无序部分的第一个节点成了判断的条件

```java
public ListNode insertionSortList(ListNode head){
    ListNode dummy = new ListNode(-1);

    // 无序部分的第一个节点成了判断的条件
    while(head != null){
        ListNode node = dummy;  // 每次插入都从头节点开始遍历
        while(node.next != null && node.next.val < head.val){
            node = node.next;
        }
        // 此时，已经找到了要插入的位置的前一个节点 node 
        ListNode temp = head.next;  // temp 用来存放 无序部分 的节点 
        
        // 插入节点
        head.next = node.next;
        node.next = head;
        
        head = temp;    // 回归于 head 指针
    }

    return dummy.next;
}
```