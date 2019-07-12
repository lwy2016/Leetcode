Description:

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Solution:
桶排序

编号为i的桶用于存放数组中出现频次为i的元素——即编号为i的桶存放“映射表”中“值”等于i的“键”

step1—用哈希表统计数组中各元素出现的频次，表中“键”为元素数值，“值”为对应元素出现的频次
step2—桶排序
step3—“逆序”取数据

```java
	public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (map.get(num) == null) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num) + 1);
			}
		}
		
		List<Integer>[] bucket = new List[nums.length + 1];
		for (int key : map.keySet()) {
			int count = map.get(key);
			
			if (bucket[count] == null) {
				bucket[count] = new ArrayList<Integer>();
			}
			bucket[count].add(key);
		}
		
		List<Integer> result = new ArrayList<Integer>();
		for (int i = nums.length; i > 0; i--) {
			if (bucket[i] != null && result.size() < k) {
				result.addAll(bucket[i]);
			}
		}
		
		return result;
    }
```