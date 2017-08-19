Description:



Solution:

![分析思路](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-53.jpg)
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