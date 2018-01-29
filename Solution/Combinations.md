Description:

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

Solution:

此题与全排很相似，可依照全排列做法实现该题
```
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        List<Integer> list = new ArrayList<Integer>();
        dfs(n, k, list, res);
        
        return res;
    }
    
    public void dfs(int n, int k, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == k) {
            res.add(new ArrayList<Integer>(list));
            return ;
        }
        
        for (int i = 1; i < n+1; i++) {
            if (list.contains(i)) continue;
            // 新添加进去的数字要比原list中的数字大
            if (!list.isEmpty() &&  i < list.get(list.size() - 1)) continue;
            
            list.add(i);
            dfs(n, k, list, res);
            list.remove(list.size() - 1); 
        }
    }
```