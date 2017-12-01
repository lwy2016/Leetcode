Description:

Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
>   1
    \
     2
    /
   3
return [1,3,2].


Solution:

## 递归

中序遍历的访问顺序为『先左再根后右』，递归版最好理解，递归调用时注意返回值和递归左右子树的顺序即可
```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    helper(root, result);
    return result;
}

private void helper(TreeNode root, List<Integer> ret) {
    if (root != null) {
        helper(root.left, ret);
        ret.add(root.val);
        helper(root.right, ret);
    }
}
```

## 迭代

使用辅助栈改写递归程序，中序遍历没有前序遍历好写，其中之一就在于入栈出栈的顺序和限制规则。我们采用「左根右」的访问顺序可知主要由如下四步构成

1. 首先需要一直对左子树迭代并将非空节点入栈
2. 节点指针为空后不再入栈
3. 当前节点为空时进行出栈操作，并访问栈顶节点
4. 将当前指针p用其右子节点替代
步骤2,3,4对应「左根右」的遍历结构，只是此时的步骤2取的左值为空

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    if(root == null) return result;
    
    Deque<TreeNode> stack = new ArrayDeque<TreeNode>(); 
    
    while(root != null || !stack.isEmpty()) {
        if(root != null) {
            stack.push(root);
            root = root.left;
        } else {
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        
    }
    return result;
}
```