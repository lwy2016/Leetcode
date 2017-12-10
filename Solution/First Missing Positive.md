Description:

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

Solution:

思路：

无序数组的题目如果要O(n)解法往往要用到hash table，但这题要求constant space。所以可以用数组本身作为一个"hash table"：A[0] = 1, A[1] = 2, .... A[n-1] = n。目标是尽可能将数字i放到数组第i-1个位置。

扫描数组中每个数：
1. 如果A[i]<1或者A[i]>n。说明A[i]一定不是first missing positive。跳过
2. 如果A[i] = i+1，说明A[i]已经在正确的位置，跳过
3. 如果A[i]!=i+1，且0<A[i]<=n，应当将A[i]放到A[A[i]-1]的位置，所以可以交换两数。
这里注意，当A[i] = A[A[i]-1] 避免欲交换的值和自身相同，否则有重复值时会产生死循环。这种情况下直接跳过。

```java
public int firstMissingPositive(int[] nums){
    int n = nums.length;
    int i = 0;
    int j = 0;
    while(i < n){
        // 条件 1.2.3
        if(nums[i] > 0 && nums[i] <= n && nums[i] != i+1 && nums[nums[i] - 1] != nums[i]){  // 满足条件时，仍计算i位置的数字
            int temp = nums[nums[i] - 1];   
            nums[nums[i] - 1] = nums[i];
            nums[i] = temp;
            // 反过来 int temp = nums[i]; nums[i] = nums[nums[i] - 1]; nums[nums[i - 1]] = temp; 第三段nums[i]已不再是之前的值

            j = i;
        } else {   // 不满足条件时，i才+1
            i++;
        }
        System.out.println(Arrays.toString(nums)+ ", j-->"+j +", i-->"+i );
    }
    for(i = 0; i < n; i++){
        if(nums[i] != i + 1){
            return i + 1;
        }
    }
    return n + 1;
}
```
以 [3,4,-1,1]为例，输出为
```
[-1, 4, 3, 1], j-->0, i-->0
[-1, 4, 3, 1], j-->0, i-->1
[-1, 1, 3, 4], j-->1, i-->1
[1, -1, 3, 4], j-->1, i-->1
[1, -1, 3, 4], j-->1, i-->2
[1, -1, 3, 4], j-->1, i-->3
[1, -1, 3, 4], j-->1, i-->4
```
j 表示 当前 nums[i] 的位置， 