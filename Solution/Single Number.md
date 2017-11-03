Description:

Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Solution:

共有2 * n + 1个数，且有且仅有一个数落单，要找出相应的「单数」。鉴于有空间复杂度的要求，不可能使用另外一个数组来保存每个数出现的次数，考虑到异或运算的特性，根据x ^ x = 0可将给定数组的所有数依次异或，最后保留的即为结果。

```java
public int singleNumber(int[] nums){
    if(nums.length == 0){
        return 0;
    }
    int result = 0;
    for(int i = 0; i < nums.length; i++){
        result ^= nums[i];
    }
    return result;
}
```