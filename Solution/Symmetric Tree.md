Description:

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3

Solution:

迭代版

```java
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        
        Deque<TreeNode> stackP = new ArrayDeque<TreeNode>();
        Deque<TreeNode> stackQ = new ArrayDeque<TreeNode>();
        
        if (root.left == null && root.right == null) return true;
        if ((root.left != null && root.right == null) || (root.left == null && root.right != null)) return false;
        stackP.push(root.left);
        stackQ.push(root.right);
        
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();
            
            if (nodeP.val != nodeQ.val) return false;
            // right不同时等于null 
            if ((nodeP.right != null && nodeQ.left == null) || (nodeP.right == null && nodeQ.left != null)) return false;
            // right同时不等于null
            if (nodeP.right != null && nodeQ.left != null) {
                stackP.push(nodeP.right);
                stackQ.push(nodeQ.left);
            }
            // left不同时等于null 
            if ((nodeP.left != null && nodeQ.right == null) || (nodeP.left == null && nodeQ.right != null)) return false;
            if (nodeP.left != null && nodeQ.right != null) {
                stackP.push(nodeP.left);
                stackQ.push(nodeQ.right);
            }
        }
        return true;
    }
```

递归版

