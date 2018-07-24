Description:

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Solution:

二叉树的最大深度为根节点到最远叶子节点的距离

如果二叉树为空，则深度为0
如果不为空，分别求左右子树的深度，取大的再加1，因为根节点的深度是1，要加进去

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
public int maxDepth(TreeNode root) {
    if(root == null) return 0;
    
    return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;   // 返回深度大的子树
}
```

