Description:

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
```
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

Solution:

深度优先遍历
回溯

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length == 0) return res;

    List<Integer> list = new ArrayList<Integer>();
    dfs(nums, list, res);

    return res;
}

private void dfs(int[] nums, List<Integer> list, List<List<Integer>> res) {
    if (list.size() == nums.length) {
        res.add(new ArrayList<Integer>(list));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (list.contains(nums[i])) continue;
        list.add(nums[i]);
        dfs(nums, list, res);
        list.remove(list.size() - 1);
    }
}
```    


