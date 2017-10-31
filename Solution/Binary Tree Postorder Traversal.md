Description:

Given a binary tree, return the postorder traversal of its nodes' values.
For example:
Given binary tree{1,#,2,3},
>
   1
    \
     2
    /
   3

Solution:

1. 递归解法
左右根
```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public ArrayList<Integer> postorderTraversal(TreeNode root){
    ArrayList<Integer> result = new ArrayList<Integer>();
    if(root != null){
        ArrayList<Integer> left = postorderTraversal(root.left);
        result.addAll(left);
        ArrayList<Integer> right = postorderTraversal(root.right);
        result.addAll(right);
        result.add(root.val);
    }
    return result;
}
```
