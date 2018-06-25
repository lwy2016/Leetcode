Description:

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target

Note: The solution set must not contain duplicate quadruplets.

>For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]


Solution:

```
public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    
    HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
    List<List<Integer>> res = new ArrayList();
    
    for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
            int k = j + 1;
            int last = nums.length - 1;
            
            while (k < last) {
                int sum = nums[i] + nums[j] + nums[k] + nums[last];
                
                if (sum > target) {
                    last--;
                } else if (sum < target) {
                    k++;
                } else {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    list.add(nums[last]);
                    
                    if (!hashSet.contains(list)) {
                        hashSet.add(list);
                        res.add(list);
                    }
                    k++;
                    last--;
                }
            }
        }
    }
    
    return res;
}
```