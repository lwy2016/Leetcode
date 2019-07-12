Description:

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.
>
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


Solution:

```java
public List<TreeNode> generateTrees(int n) {
    if (n <= 0) return new ArrayList<TreeNode>();
    return genBST(1, n);
}

private List<TreeNode> genBST(int min, int max) {
    List<TreeNode> list = new ArrayList<TreeNode>();
    if(min > max) {
        list.add(null);
        return list;
    }
    
    for (int i = min; i <= max; i++) {
        List<TreeNode> leftTree = genBST(min, i - 1);
        List<TreeNode> rightTree = genBST(i + 1, max);
        
        for (TreeNode lnode: leftTree) {
            for (TreeNode rnode: rightTree) {
                TreeNode root = new TreeNode(i);
                root.left = lnode;
                root.right = rnode;
                
                list.add(root);
            }
        }
    }
    
    return list;
}
```