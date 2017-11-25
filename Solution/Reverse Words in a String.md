Description:

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Solution:

空间复杂度为O(1)的解法？

处理异常及特殊情况
处理多个空格及首尾空格
记住单词的头尾指针，翻转之
整体翻转

```java
    public String reverseWords(String s) {
        if(s == null) return null;
        s = s.trim();
        int n = s.length();
        
        char[] c = s.toCharArray();
        
        // 1. reverse s
        reverse(c, 0, n - 1);
        // 2. reverse each words
        reverseWord(c, n);
        // 3. clean up spaces
        return cleanSpace(c, n);
    }
    private String cleanSpace(char[] c, int n){
        int start = 0, end = 0;
        while(end < n){
            while(end < n && c[end] == ' ') end++;
            while(end < n && c[end] != ' ') c[start++] = c[end++];
            
            if(end < n) c[start++] = ' ';
        }
        return new String(c).substring(0, start);
    }
    private void reverseWord(char[] c, int n){
        int i = 0, j = 0;
      
        while (i < n) {
            while (i < j || i < n && c[i] == ' ') i++; // skip spaces
            while (j < i || j < n && c[j] != ' ') j++; // skip non spaces
            reverse(c, i, j - 1);                      // reverse the word
        }
    }
    
    private void reverse(char[] c, int i, int j){
        while(i < j){
            char temp = c[i];
            c[i++] = c[j];
            c[j--] = temp;
        }
    }
```