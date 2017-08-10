Description:

Determine whether an integer is a palindrome. Do this without extra space.

Solution:
回文数是自然数，最小的回文数是0，其他回文数比如，1,22,333,424,5665,788997,9902099
此题与LeetCode-7题Reverse Integer有类似之处，将反转之后的数字与原数字比较，如果相等，则该数字为回文数
```java
public class Solution {
    public boolean isPalindrome(int x) {
        long rev = 0;
        int y = x;
        if(x < 0) return false;
        while(x != 0){
            rev = rev * 10 + x % 10;
            x = x / 10;
            if( rev > Integer.MAX_VALUE ){
                return false;
            }
        }
        return (int)rev==y?true:false;
    }
}
```