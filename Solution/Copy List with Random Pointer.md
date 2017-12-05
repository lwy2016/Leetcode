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


间接使用哈希表

可以通过链表原来结构中的 next 指针来替代 hash table 做哈希

实际我们对链表进行了三次扫描，第一次扫描对每个结点进行复制，然后把复制出来的新节点接在原结点的 next 指针上，也就是让链表变成一个重复链表，就是新旧更替；第二次扫描中我们把旧结点的随机指针赋给新节点的随机指针，因为新结点都跟在旧结点的下一个，所以赋值比较简单，就是 node->next->random = node->random->next，其中 node->next 就是新结点，因为第一次扫描我们就是把新结点接在旧结点后面。现在我们把结点的随机指针都接好了，最后一次扫描我们把链表拆成两个，第一个还原原链表，而第二个就是我们要求的复制链表。因为现在链表是旧新更替，只要把每隔两个结点分别相连，对链表进行分割即可。

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
    
    RandomListNode curr = head;
    // step1 generate new list with node
    while(curr != null) {
        RandomListNode newNode = new RandomListNode(curr.label);
        
        newNode.next = curr.next;
        curr.next = newNode;
        
        curr = curr.next.next;
    }
    // step2 copy random pointer
    curr = head;
    while (curr != null) {
        if (curr.random != null) {
            curr.next.random = curr.random.next;
        }
        curr = curr.next.next;
    }
    // step3 split original and new list
    RandomListNode newHead = head.next;
    curr = head;
    while (curr != null) {
        RandomListNode newNode = curr.next;
        curr.next = curr.next.next;
        curr = curr.next;
        if (newNode.next != null) {
            newNode.next = newNode.next.next;
        }
    }
    
    return newHead;
}
```