Description:

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Solution:

平衡二叉树的定义是两个子树的深度差不超过1，显然使用递归进行分析比较方便。既然使用高递归，那么接下来就要分析递归调用的终止条件。 root == null 必然是其中一个终止条件，返回0；根据题意还需另一终止条件-->左右子树高度差大于1，但对此终止条件的返回值应是多少？
Math.abs(leftDepth - rightDepth) > 1 肯定是要特殊处理的，如果返回 -1, 咋一看似乎在下一步返回 Math.max(leftDepth, rightDepth) + 1时会出错，再进一步想想，能够不让 Math.max() 这一句执行呢？ 如果返回了 -1， 其接盘侠 必然是 leftDepth or rightDepth 中的一个，因此，我们只需要在判断子树高度差大于1的同时，也判断下左右子树的深度是否为 -1，即可都返回 -1. 这种处理方法很精妙.

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
public boolean isBalanced(TreeNode root) {
    return (-1 ！= maxDepth(root));
}
private int maxDepth(TreeNode root) {
    if(root == null) return 0;

    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    if(leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
        return -1;
    }

    return Math.max(leftDepth, rightDepth) + 1;
}
``` 