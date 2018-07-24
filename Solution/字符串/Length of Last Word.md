Description:

Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5

Solution:

```java
public int lengthOfLastWord(String s) {
    int right = s.length() - 1, res = 0;
    while (right >= 0 && s.charAt(right) == ' ') right--;
    while (right >= 0 && s.charAt(right) != ' ') {
        right--;
        res++;
    }
    return res;
}
```
