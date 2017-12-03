Description:

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
 >   3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:
>[
  [3],
  [20,9],
  [15,7]
]

Solution:

二叉树的广度优先遍历使用队列非常容易实现，这道题要求的是蛇形遍历，我们可以发现奇数行的遍历仍然可以按照广度优先遍历的方式实现，而对于偶数行，只要翻转一下就好了。

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
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList();
    if(root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.offer(root);
    int isEven = 0;
    
    while(!queue.isEmpty()){
        List<Integer> list = new ArrayList<Integer>();
        int len = queue.size();
        isEven++;
        
        for(int i = 0; i < len; i++) {
            TreeNode curr = queue.poll();
            list.add(curr.val);
            if(curr.left != null) queue.offer(curr.left);
            if(curr.right != null) queue.offer(curr.right);
        }
        if(isEven % 2 == 0) Collections.reverse(list);
        result.add(list);
    }
    return result;
}
``