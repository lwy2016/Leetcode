Description:

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.

Solution:

递归版
```java 
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        
        if (root.left == null && root.right == null) return true;
        if (root.left != null && root.right == null) return root.left.val < root.val && isValidBST(root.left);
        if (root.left == null && root.right != null) return root.right.val > root.val && isValidBST(root.right);
            
        return root.left.val < root.val && root.right.val > root.val && 
            isValidBST(root.left) && isValidBST(root.right);
    }
```

测试用例
```
[2,1,3]
[1,2,4]
[]
[1]
[5,6]
[10,5,15,null,null,6,20]
```

测试结果对比
```
answer      expected answer
true        true
false       false
true        true
true        true
false       false
true        false 
```

错误原因，只考虑到了该点与两个子节点大小的比较，没有考虑到该点与两个子树节点大小的比较。
怎么才能判断根该点小于右子树上的所有点，大于左子树上的所有点呢？

由于不仅需要考虑当前父节点，还需要考虑父节点的父节点... 故递归时需要引入上界和下界值

```java
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode root, long lower, long upper) {
        if (root == null) return true;
        if (root.val >= upper || root.val <= lower) return false;
        
        // 左子树时，限定upper边界，左子树的所有值都要比该节点小
        // 右子树时，限定lower边界，右子树的所有值都要比该节点大
        return isValidBST(root.left, lower, root.val) && isValidBST(root. right, root.val, upper);
    }
```