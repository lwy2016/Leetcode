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

## 递归解法
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

## 迭代

要想得到『左右根』的后序遍历结果，我们发现只需将『根右左』的结果转置即可，而先序遍历通常为『根左右』，故改变『左右』的顺序即可

```java
public ArrayList<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    if(root == null){ 
        return result;
    }
    Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
    stack.push(root);

    while(!stack.isEmpty()){
        TreeNode curr = stack.pop();
        result.add(curr.val);
        根右左存储
        if(curr.left != null){
            stack.push(curr.left);
        }
        if(curr.right != null){
            stack.push(curr.right);
        }
    }
    Collections.reverse(result);   // reverse
    return result;
}
```