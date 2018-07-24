Dscirption:

Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Solution:

相同的三个值，转换为二进制后，如果相加后不进位
110110
110110
110110

330330
则被3取余后为 00000
单独出现的数字就可以求出来了

1. 计算二进制 i 位上的数值 ((x>>i) & 1) ,求其和
2. 对3取余，并恢复原值

```java
public int singleNumber(int[] nums) {
    if(nums.length == 0){
        return -1;
    }
    int result = 0;
    for(int i = 0; i < 32; i++){  // int 类型，32位
        int bit_i_num = 0;
        for(int j = 0; j < nums.length; j++){
            bit_i_num += ((nums[j] >> i) & 1);  // 把第i位的值移到倒数第一个位置，然后跟1相与，原位置为1，则结果为1，为0，则结果为0
        }
        result += (bit_i_num % 3) << i;   // 恢复第 i 位的大小
    }
    return result;
}
```