Description；

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.


Solution:

贪心

times 就是跳了多少次
reached 就是当前跳了 times 次时能够到的最远的范围
max 就是路过的位置之处再跳一次能到达的最远的位置 

```
public int jump (int[] nums) {
	int times = 0;
	int reached = 0;
	int max = 0;

	for (int i = 0; i < nums.length; i++) {
		if (reached < i) {
			times++;
			reached = max;
		}
		max = Math.max(max, nums[i] + i);
	}

	return times;
}
```