Description:

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

 
>A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Solution:
此题急于知道答案，没有自己写（或许写不出来），在Discuss区找到一个相当好的Solution
```java
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		 List<List<Integer>> list = new ArrayList<List<Integer>>();
		 
		 Arrays.sort(nums);    // 对原始数组进行从小到大排序，这是此题的思路来源，
		 
		 for( int i = 0; i < nums.length - 2; i++){  //  循环进行比较，每一次遍历找出一个符合的数组，或不符合条件
			 if(i > 0 && nums[i] == nums[i-1]){      // skip same result
				 continue;
			 }
			 int j = i + 1, k = nums.length - 1;
			 Integer target = -nums[i];        	// 目标值为 剩下两者之和的相反数
			 while (j < k){						// 在[j, k]区间中，寻找目标，
				 if( nums[j] + nums[k] == target){
					 list.add(Arrays.asList(nums[i], nums[j], nums[k]));   // 添加List到list中
					 j++;
					 k--;
					 while(j < k && nums[j] == nums[j - 1]) j++;   	// skip same result
					 while(j < k && nums[k] == nums[k + 1]) k--; 	// skip same result
				 } else if ( nums[j] + nums[k] > target){   // 大于 target 时， 应减小两者之和
					 k--;
				 } else {									// 小于 target 时， 应增加两者之和
					 j++;
				 }
				 
			 }	 
		 }
		 
		 return list;
	 }
}
```

