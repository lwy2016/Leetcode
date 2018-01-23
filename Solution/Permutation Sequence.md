Description:

The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

Solution:

按照 全排列的方式 代码会超时
Time Limit Exceeded
```java
    public String getPermutation(int n, int k) {
        String[] nums = new String[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1 + "";
        }
        List<List<String>> res = new ArrayList<List<String>>();
        if (nums == null || nums.length == 0) return "";

        List<String> list = new ArrayList<String>();
        dfs(nums, list, res);
        return construct(res.get(k - 1));
    }

    private void dfs(String[] nums, List<String> list, List<List<String>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<String>(list));
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i])) continue;
            list.add(nums[i]);
            dfs(nums, list, res);
            list.remove(list.size() - 1);
        }
    }

    private String construct(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }
```

参照 Discuss 的代码思想
```java

```