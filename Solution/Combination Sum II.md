Description:

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
 - All numbers (including target) will be positive integers.
 - The solution set must not contain duplicate combinations.

For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 

```
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

Solution:

与上一题的区别：
1. 不能使用 candidates中同一个数字了，除非它本身就重复，
2. 要去重复，

大致代码与 Combination Sum 相同，稍有修改

```java
public List<List<Integer>> combinationSum2(int[] candidates, int target){
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	Arrays.sort(candidates);
	getResult(result, new ArrayList<Integer>(), candidates, target, 0);

	return result;
}
public void getResult(List<List<Integer>> result, ArrayList<Integer> cur, int[] candidates, int target, int start){
	if(target > 0){
		// i的初始值为start
		for (int i = start; i < candidates.length && candidates[i] <= target; i++){
			cur.add(candidates[i]);
			getResult(result, cur, candidates, (target - candidates[i]), i + 1); // 改为i+1

			cur.remove(cur.size() - 1);
			//  去除重复
			if(i < candidates.length && candidates[i] == candidates[i+1]){
				i++;
			}
		}
	} else if(target == 0){
		result.add(new ArrayList<Integer>(cur));
	}
}
```
以 combination(new int[]{1,1,2,5,6,7,10}, 8)为例
syso 打印数据
```
result-->[], cur-->[1], target-->7, i-->0
result-->[], cur-->[1, 1], target-->6, i-->1
result-->[], cur-->[1, 1, 2], target-->4, i-->2
---------[1, 1], target-->6, i-->2
result-->[], cur-->[1, 1, 5], target-->1, i-->3
---------[1, 1], target-->6, i-->3
result-->[], cur-->[1, 1, 6], target-->0, i-->4
---------[1, 1], target-->6, i-->4
---------[1], target-->7, i-->1
result-->[[1, 1, 6]], cur-->[1, 2], target-->5, i-->2
result-->[[1, 1, 6]], cur-->[1, 2, 5], target-->0, i-->3
---------[1, 2], target-->5, i-->3
---------[1], target-->7, i-->2
result-->[[1, 1, 6], [1, 2, 5]], cur-->[1, 5], target-->2, i-->3
---------[1], target-->7, i-->3
result-->[[1, 1, 6], [1, 2, 5]], cur-->[1, 6], target-->1, i-->4
---------[1], target-->7, i-->4
result-->[[1, 1, 6], [1, 2, 5]], cur-->[1, 7], target-->0, i-->5
---------[1], target-->7, i-->5
---------[], target-->8, i-->1
result-->[[1, 1, 6], [1, 2, 5], [1, 7]], cur-->[2], target-->6, i-->2
result-->[[1, 1, 6], [1, 2, 5], [1, 7]], cur-->[2, 5], target-->1, i-->3
---------[2], target-->6, i-->3
result-->[[1, 1, 6], [1, 2, 5], [1, 7]], cur-->[2, 6], target-->0, i-->4
---------[2], target-->6, i-->4
---------[], target-->8, i-->2
result-->[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]], cur-->[5], target-->3, i-->3
---------[], target-->8, i-->3
result-->[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]], cur-->[6], target-->2, i-->4
---------[], target-->8, i-->4
result-->[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]], cur-->[7], target-->1, i-->5
---------[], target-->8, i-->5
[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
```