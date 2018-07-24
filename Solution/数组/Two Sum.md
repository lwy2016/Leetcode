Question descripted:

Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
---

刚开始看英文，也没看大懂，于是便有了三个版本，时间复杂度是O(N2)

version1
```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2]; 
		
        int begin = 0;
        int end = 1;
        
        for(int i=0; i<nums.length-1; i++){
        	for(int j=1; j<nums.length; j++){
        		if(nums[i] + nums[j] == target){
        			begin = i;
        			end = j;
        			break;
        		}
        	}
        }
        
        res[0] = begin+1;
        res[1] = end+1;
        
		return res;
    }
}

```
Input: 		[-1,-2,-3,-4,-5]  8 
Output: 	[4,4]
Expected:	[3,5]

应该是先[3,5]后[4,4]的，应该是break没有跳出去，才会导致后面的把前面的覆盖掉
这里添加一个条件 i!=j， 排除掉自己加自己的这种情况
version2
```java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2]; 
		
        int begin = 0;
        int end = 1;
        
        for(int i=0; i<nums.length-1; i++){
        	for(int j=1; j<nums.length; j++){
        		if(nums[i] + nums[j] == target && i!=j){
        			begin = i;
        			end = j;
        			break;
        		}
        	}
        }
        
        res[0] = begin+1;
        res[1] = end+1;
        
		return res;
    }
}
```
Input:		[2,1,9,4,4,56,90,3] 8
Output:		[5,4]
Expected:	[4,5]

出现这种情况应该还是break的问题，所以的话，遇到符合条件的直接就return出来就好了，但 i!=j这个条件仍然不能忽略

version3
```java
public int[] twoSum(int[] nums, int target) {
        int res[] = new int[2]; 
		
        int begin = 0;
        int end = 1;
        
        for(int i=0; i<nums.length-1; i++){
        	for(int j=1; j<nums.length; j++){
        		if(nums[i] + nums[j] == target && i!=j){
        			begin = i;
        			end = j;
        			
        			res[0] = begin+1;
        	        res[1] = end+1;
        	        
        			return res;
        		}
        	}
        }
		return res;
    }
```

这里要想一下如何降低时间复杂度，首先应该想到将逐个比较转变为 直接查找，**即先计算出target与当前元素的差值，然后在序列中寻找这个差值，**
查找最快的方法就是利用一个map容器存储每个元素的索引，这样取得某个特定元素的索引只需要常数时间即可完成，最多只需便利一次序列，这样时间的复杂度是O(N), its amazing!!!
version4
```java
 public int[] twoSum(int[] nums, int target) {
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i=0; i<nums.length; i++){
        	Integer n = map.get(nums[i]);      // 判断 map 中是否已经存入了该数值
        	if(null == n){                     // 没有，则存入
        		map.put(nums[i], i);
        	}

        	n = map.get(target - nums[i]);     // 获得 target 与 该存入值的差值 在 map 中的位置, 当前的值的坐标是 i, 且i的值大 
        	if(null != n && n <i){             // n < i，用来保证返回的数组，前者比后者小 
        		return new int[]{n, i};
        	}
        		
        }
        return null;
    }
```
1.先判断nums[i]是否存在于map中，若没存在则添加到map中，key表示查找的值，value表示key在数组中的顺序；
2.获取target-nums[i]的值，若存在说明已找到，且res[0]要小于res[1]，那么需要添加限制条件n < i
