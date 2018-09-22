Description:

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Solution:

状态转移方程如下：
f=max(f+A[i],A[i]);//对于数组里的一个整数，它只有两种 选择：1、加入之前的 SubArray；2. 自己另起一个 

![分析思路](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-53.jpg)


手撕
```java
public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) return 0; 
    
    int max = nums[0];
    int f = 0;
    for (int i = 0; i < nums.length; i++) {
        f = Math.max(f + nums[i], nums[i]);
        max = Math.max(max, f);
    }

    return max;
}
```


```java
public int maxSubArray(int[] nums){
    int f = 0, max = nums[0];
    int start = 0, end = 0;
    for(int i = 0, L = nums.length; i < L; ++i){
//          f = Math.max(f + nums[i], nums[i]);
        if(f+nums[i] > nums[i]){
            f = f + nums[i];
            if(f > max){
                end = i;   // 记录最大子串的结束位置
            }
        } else {
            f = nums[i];
            if(max < f){
                start = i;  // 记录最大子串的开始位置
            }
        }
        
        max = Math.max(max, f);
    }
    System.out.println(start +" -- " + end);
    return max;
}
```