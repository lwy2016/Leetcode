Description:

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

Solution:

仔细观察结果可以看出，这是遍历循环Combinations题中(n, k), k form 0 to n 的结果，于是有

```java
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        List<Integer> list = new ArrayList<Integer>();
        for (int k = 0; k < nums.length + 1; k++) {
            dfs(nums, nums.length, k, list, res);
        }
        
        return res;
    }
    
    public void dfs(int[] nums, int n, int k,List<Integer> list, List<List<Integer>> res) {
        if (list.size() == k) {
            res.add(new ArrayList<Integer>(list));
            return ;
        }
        
        for (int i = 0; i < n; i++) {
            if (list.contains(nums[i])) continue;
            if (!list.isEmpty() && nums[i] < list.get(list.size() - 1)) continue;
            
            list.add(nums[i]);
            dfs(nums, n, k, list, res);
            list.remove(list.size() - 1);
        }
    }
```
