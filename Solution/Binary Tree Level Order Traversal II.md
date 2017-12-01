Description:

Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],

>   3
   / \
  9  20
    /  \
   15   7


return its bottom-up level order traversal as:
>[
  [15,7],
  [9,20],
  [3]
]   


Solutoin:

此题在普通的 BFS 基础上增加了逆序输出，简单的实现可以使用辅助栈或者最后对结果逆序。

// 辅助栈
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
public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> result = new ArrayList();
    if (root == null) return result;
    
    // use stack to reverse
    Stack<ArrayList<Integer>> stack = new Stack<ArrayList<Integer>>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    
    queue.offer(root);
    while(queue.size() > 0){
        int len = queue.size();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < len; i++){
            TreeNode curr = queue.poll();
            list.add(curr.val);
            if(curr.left != null) queue.offer(curr.left);
            if(curr.right != null) queue.offer(curr.right);
        }
        stack.push(list);
    }
    while(!stack.isEmpty()){
        result.add(stack.pop());
    }
    return result;
}
```

// 最后对结果进行逆序
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

public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> result = new ArrayList();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    
    queue.offer(root);
    while(queue.size() > 0){
        int len = queue.size();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < len; i++){
            TreeNode curr = queue.poll();
            list.add(curr.val);
            if(curr.left != null) queue.offer(curr.left);
            if(curr.right != null) queue.offer(curr.right);
        }
        result.add(list);
    }
    Collections.reverse(result);
    return result;
}
```