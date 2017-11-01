Description:

Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3}

>
   1
    \
     2
    /
   3

return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

Solution:

先序遍历，根左右

递归

```java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    
    if(root != null){
        res.add(root.val);
        List<Integer> left = preorderTraversal(root.left);
        res.addAll(left);
        List<Integer> right = preorderTraversal(root.right);
        res.addAll(right);
    }
    
    return res;
}
```

迭代：
迭代时需要利用栈来保存遍历到的节点，应首先进行出栈抛出当前节点，保存当前节点的值，随后将右、左节点分别入栈(注意入栈顺序，先右后左)，迭代到其为叶子节点(NULL)为止。

```java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();   // result 专门用来存储遍历的结果
    if(root == null){
        return result;
    }
    Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
    stack.push(root);
    while(!stack.isEmpty()){
        TreeNode curr = stack.pop(); // 弹出的第一个节点
        result.add(curr.val);        // 存入出栈的值
        
        if(curr.right != null){
            stack.push(curr.right);
        }
        if(curr.left != null){
            stack.push(curr.left);
        }
    }
    return result;
    
}
```

 - 1.对root进行异常处理
 - 2.将root压入栈
 - 3.循环终止条件为栈s为空，所有元素均已处理完
 - 4.访问当前栈顶元素(首先取出栈顶元素，随后pop掉栈顶元素)并存入最终结果
 - 5.将右、左节点分别压入栈内，以便取元素时为先左后右。
 - 6.返回最终结果