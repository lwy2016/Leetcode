Description:

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

Solution:

```java
public int removeDuplicates(int[] nums) {
    int index = 0, int L = nums.length, count = 1;

    for(int i = 1; i < L; i++){
        if(nums[index] == nums[i]){
            if(count == 2){
                coutinue;
            }
            count++;
        } else {
            count = 1;
        }
        index++;
        nums[index] = nums[i];
    }
    return index+1;
}
```