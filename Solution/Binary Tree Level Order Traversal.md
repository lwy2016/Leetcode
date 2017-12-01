Description:

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
>    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:
>[
  [3],
  [9,20],
  [15,7]
]

Solution:

使用一个队列保存每层的节点即可。出队和将子节点入队的实现使用 for 循环，将每一轮的节点输出。

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
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList();
    if(root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    
    while(!queue.isEmpty()){
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
    return result;
}
```