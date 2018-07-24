Description:

Given preorder and inorder traversal of a tree, construct the binary tree.
Note:
You may assume that duplicates do not exist in the tree

Solution:

二叉树的重建 核心
1. preorder 先序遍历(根左右)的一个节点即为根节点
2. 确定 inorder(左根右)数组中的根节点后，其左右也是 preorder 的左右子树

其中第二点是隐含条件，数组中没有重复元素，故可以根据先序遍历中第一个元素（根节点）得到根节点的值，然后在 inorder 中序遍历的数组中搜索得到根节点的索引值，即为左子树，右边为右子树。根据中序遍历中左子树的索引确定先序遍历数组中左子树的起止索引。递归直至处理完所有数组元素。

```java
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder == null || inorder == null) return null;
    if(preorder.length == 0 || inorder.length == 0) return null;
    if(preorder.length != inorder.length) return null;

    TreeNode root = helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    return root;
}

private TreeNode helper(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend) {
    // 边界处理
    if(prestart > preend || instart > inend) return null;
    // built root TreeNode     
    int root_val = preorder[prestart];
    TreeNode root = new TreeNode(root_val);
    // find index of root_val in inorder[]
    int index = findIndex(inorder, instart, inend, root_val);
    // built left subtree, 左半数组的长度 index - inorder
    root.left = helper(preorder, prestart + 1, prestart + (index - instart), inorder, instart, index -1);
    // built right subtree
    root.right = helper(preorder, prestart + (index - instart) + 1, preend, inorder, index + 1, inend);
    return root;
}

private int findIndex(int[] nums, int start, int end, int target) {
    for (int i = start; i <= end; i++) {
        if (nums[i] == target) return i;
    }
    return -1;
}
```