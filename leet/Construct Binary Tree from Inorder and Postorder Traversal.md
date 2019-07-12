Desciption:

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

Solution:

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

public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder == null || postorder == null) return null;
    if(inorder.length == 0 || postorder.length == 0) return null;
    if(inorder.length != postorder.length) return null;
    
    TreeNode root = helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    
    return root;
}

private TreeNode helper(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend) {
    if(instart > inend || poststart > postend) return null;
    // built root TreeNode 
    int root_val = postorder[postend];
    TreeNode root = new TreeNode(root_val);
    // find root index in inorder[]
    int index = findIndex(inorder, instart, inend, root_val);
    // built left subtree
    root.left = helper(inorder, instart, index - 1, postorder, poststart, poststart + (index - instart) - 1);
    // built right subtree
    root.right = helper(inorder, index + 1, inend, postorder, poststart + (index - instart), postend - 1);
    
    return root;
}

private int findIndex(int[] nums, int start, int end, int target) {
    for (int i = start; i <= end; i++) {
        if (nums[i] == target) return i;
    }
    return -1;
}
```