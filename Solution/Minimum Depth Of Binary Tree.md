Description:

Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Solution:

二叉树的最小深度为根节点到最近叶子节点间的距离

判断左子树或右子树是否为空，若左子树为空，则返回右子树的深度，反之返回左子树的深度
如果都不为空，则返回左子树和右子树深度的最小值

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
 public int minDepth(TreeNode root){
    if(root == null) return 0;

    if(root.left == null) return minDepth(root.right) + 1;   // 左子树为空返回右子树的深度
    if(root.right == null) return minDepth(root.left) + 1;   // 右子树为空返回左子树的深度

    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;   //  左右子树都不为空，返回深度小的
 }
```
