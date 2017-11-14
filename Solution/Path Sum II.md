Description:


Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
For example:
Given the below binary tree andsum = 22,
>
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

return
>
[
   [5,4,11,2],
   [5,8,4,5]
]

Solution:

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
public List<List<Integer>> pathSum(TreeNode root, int sum){
    List<List<Integer>> result = new ArrayList();
    // or init as --> List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> path = new ArrayList<Integer>();

    if(root == null) return result;
    dfs(root, sum, path, result);

    return result;
}
public void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result){
    if(root == null) return ;

    path.add(root.val);
    sum -= root.val;

    if(root.left == null && root.right == null && sum == 0){
        result.add(new ArrayList<Integer>(path));
    } else {
        dfs(root.left, sum, path, result);
        dfs(root.right, sum, path, result);
    }
    path.remove(path.size() - 1);  // 当不满足条件时，往回退
}
```