Description:

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

Solution:

参考博客： http://huntfor.iteye.com/blog/2077665
通过中序遍历，不使用栈；不需要存储每个节点，只需要存一个前驱（中序线索遍历）
将第一次逆序的前驱与第二次逆序的root节点交换值

1,4,3,2,5,6
1.当我们读到4的时候，发现是正序的，不做处理
2.但是遇到3时，发现逆序，将4存为第一个错误节点，3存为第二个错误节点
3.继续往后，发现3，2又是逆序了，那么将第2个错误节点更新为2
如果是这样的序列：1,4,3,5,6同上，得到逆序的两个节点为4和3。
同理对于边界情况也是可以处理的，例如2,1

```
        2 
   4         5
1    3          6      
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
TreeNode first = null, second = null;
TreeNode prev = null;

public void recoverTree(TreeNode root) {
    findNode(root);
    swap(first, second);
} 

private void findNode(TreeNode root) {
    if (root == null) return ;
    if (root.left != null) findNode(root.left);
    // 出现逆序
    if (prev != null && root.val < prev.val) {
        if (first == null) {    // 第一次逆序
            first = prev;
        }
        second = root;
    }
    prev = root;
    if (root.right != null) findNode(root.right);
}

private void swap(TreeNode node1, TreeNode node2) {
    int temp = node1.val;
    node1.val = node2.val;
    node2.val = temp;
}
```