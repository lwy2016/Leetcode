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

深度优先遍历：

因为求所有组合，这就意味着不能重复使用元素，要用visited数组。

有因为是所有可能的组合，所以循环length次，就是这里面每位都有可能有length个可能性。

正因为如此，每一层递归就不需要传递一个start点，告诉他从哪开始（因为都是从头开始循环）。
```
public List<List<Integer>> permute2(int[] nums){
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    ArrayList<Integer> item = new ArrayList<Integer>();
    
    if(nums == null || nums.length == 0){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        result.add(temp);
        return result;
    }
    boolean[] visited = new boolean[nums.length];
    
    getPermute(nums, result, item, visited);
    return result;
}

public void getPermute(int[] nums, List<List<Integer>> result, ArrayList<Integer> item, boolean[] visited){
    if(item.size() == nums.length){
        result.add(new ArrayList<Integer>(item));
        return ;
    }
    
    for(int i = 0; i < nums.length; i++){
        if(!visited[i]){
            item.add(nums[i]);
            visited[i] = true;
            getPermute(nums, result, item, visited);
            item.remove(item.size() - 1);
            visited[i] = false;
        }
    }
}
```    


某高深算法
```
public List<List<Integer>> permute(int[] nums){
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    if(nums == null || nums.length == 0){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        result.add(temp);
        return result;
    }
    
    dfs(0, nums, result);
    
    return result;
}

public void dfs(int i, int[] nums, List<List<Integer>> result){
    if(i == nums.length){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int j = 0; j < nums.length; j++){
            temp.add(nums[j]);
        }
        result.add(temp);
    }
    for(int j = i; j < nums.length; j++){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
        System.out.println(Arrays.toString(nums)+", i = " + i +", j = " + j);
        
        dfs(i+1, nums, result);
        
        tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```