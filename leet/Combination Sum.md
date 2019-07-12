Description:

Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
```
[
  [7],
  [2, 2, 3]
]
```

Solution:

回溯 Backtracking

题目所给条件：candidates数组中没有重复的数字，且都是正数

基本思路是先排好序，然后每次递归中把剩下的元素一一加到结果集合中，并且把目标减去加入的元素，然后把剩下元素（包括当前加入的元素）放到下一层递归中解决子问题。


```java
public List<List<Integer>> combinationSum(int[] candidates, int target){
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	Arrays.sort(candidates); 		// 将 candidates 数组 从小到大排序
	getResult(result, new ArrayList<Integer>(), candidates, target, 0);  // 调用 回溯 算法

	return result;    // 把符合条件的 List 都存入到 result 中，并返回
}

// 每个符合条件的 List 都存入到同一个 cur 中，当 target > 0 时，继续递归调用 getResult(args), 直到满足 target == 0，将 cur 添加到 result 中，调用
public void getResult(List<List<Integet>> result, ArrayList<Integer> cur, int[] candidates, int target, int start){
	if(target > 0){
		// i的初始值为start
		for(int i = start; i < candidates.length && candidates[i] <= target; i++){  
			cur.add(candidates[i]);
			System.out.println("result-->"+result+", cur-->"+cur+", target-->"+(target-candidates[i])+", start-->"+start);
			getResult(result, cur, candidates, (target - candidates[i]), i);

			cur.remove(cur.size() - 1);  // 不符合 for循环 条件，则回溯cur（执行此行），继续for循环
			System.out.println("---------"+cur+", target-->"+(target)+", i-->"+i);
		}
	} else if(target == 0) {
		result.add(new ArrayList<Integer>( cur ));
	}
}
```
以 combination(new int[]{2,3,6,7}, 7)为例
syso 打印数据
```
result-->[], cur-->[2], target-->5, i-->0
result-->[], cur-->[2, 2], target-->3, i-->0
result-->[], cur-->[2, 2, 2], target-->1, i-->0
---------[2, 2], target-->3, i-->0
result-->[], cur-->[2, 2, 3], target-->0, i-->1
---------[2, 2], target-->3, i-->1
---------[2], target-->5, i-->0
result-->[[2, 2, 3]], cur-->[2, 3], target-->2, i-->1
---------[2], target-->5, i-->1
---------[], target-->7, i-->0
result-->[[2, 2, 3]], cur-->[3], target-->4, i-->1
result-->[[2, 2, 3]], cur-->[3, 3], target-->1, i-->1
---------[3], target-->4, i-->1
---------[], target-->7, i-->1
result-->[[2, 2, 3]], cur-->[6], target-->1, i-->2
---------[], target-->7, i-->2
result-->[[2, 2, 3]], cur-->[7], target-->0, i-->3
---------[], target-->7, i-->3
[[2, 2, 3], [7]]
```