Description:

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

For example:
Given the below binary tree,
>       1
      / \
     2   3

Return 6.

Solution:

路径中不一定包含根节点，也就是说可以起止于任一连通节点
路径和 = 根节点 + 左子树路径长度 + 右子树路径长度
f(root) = root.val + Math.max(f(root.left), f(root.right))

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
int maxValue = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
    maxPathDown(root);
    return maxValue;
} 

private int maxPathDown(TreeNode root) {
    if(root == null) return 0;

    // 如果左子树或者右子树的路径长度之和<0，那么等于是减少了总的路径长度的值，应该舍弃该左子树或右子树
    int left = Math.max(0, maxPathDown(root.left));
    int right = Math.max(0, maxPathDown(root.right));

    maxValue = Math.max(maxValue, left + right + root.val);

    return Math.max(left, right) + root.val;
}
```