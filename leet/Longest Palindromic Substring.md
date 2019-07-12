Description:

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"



Solution:


```
public String longestPalindrome(String s) {
    int n = s.length();
    boolean[][] pal = new boolean[n][n];
    int maxLen = 0;
    int start = 0, end = 0;
    
    for (int i = 0; i < n; i++) {  // i作为终点 
        for (int j = 0; j <= i; j++) {  // j作为起点
            if (s.charAt(j) == s.charAt(i) && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                pal[j][i] = true;
                if (i - j + 1 >= maxLen) {
                    maxLen = i - j + 1;
                    start = j;
                    end = i + 1;
                }
            }
        }
    } 
    return s.substring(start, end);
}
```