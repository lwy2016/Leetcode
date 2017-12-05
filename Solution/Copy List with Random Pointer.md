Description:

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Solution:

所有类似的深度拷贝题目的传统做法，都是维护一个 hash table。即先按照复制一个正常链表的方式复制，复制的时候把复制的结点做一个 hash table，以旧结点为 key，新节点为 value。这么做的目的是为了第二遍扫描的时候我们按照这个哈希表把结点的 random 指针接上。

```java
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public RandomListNode copyRandomList(RandomListNode head) {
    if(head == null) return null;
    
    RandomListNode dummy = new RandomListNode(-1);
    RandomListNode curNode = dummy;
    
    HashMap<RandomListNode, RandomListNode> randomMap = new HashMap<RandomListNode, RandomListNode>();
    
    while (head != null) {
        // link newNode to new List
        RandomListNode newNode = new RandomListNode(head.label);
        curNode.next = newNode;
        // map old node head to newNode
        randomMap.put(head, newNode);
        
        newNode.random = head.random;
        
        head = head.next;
        curNode = curNode.next;
    }
    
    // re-Mapping old random node to new node
    curNode = dummy.next;
    while (curNode != null) {
        if (curNode.random != null) {
            curNode.random = randomMap.get(curNode.random);
        }
        curNode = curNode.next;
    }
    
    return dummy.next;
}
```
