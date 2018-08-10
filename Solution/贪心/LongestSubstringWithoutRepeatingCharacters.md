Question:
Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.

Analyze:
假设子串里含有重复字符，则父串一定含有重复字符。单个子问题就可以解决父问题，因此可以用贪心算法。跟动规不同，动规里，单个子问题只能影响父问题，不足以决定父问题。
从左到又扫描，当遇到重复字母时，以上一个字母的index+1作为新的搜索起始位置,知道最后一个字母，复杂度是O(n)
![](http://7xnyvm.com1.z0.glb.clouddn.com/11.png)

```java
public int lengthOfLongestSubstring(String s) {
    // 用ASCII表示字符，从空格到z一共有128个
    // last[]数组下标表示字符，元素表示字符在字符串中出现的位置
    int last[] = new int[128];
    // start表示重复出现的字符的下一个位置
    int start = 0;
    int maxLen = 0; 
    // 将last[]所有值赋值为1
    for (int i = 0;i < 128 ;i++ ) {
        last[i] = -1;
    }

    for (int i = 0; i < s.length() ; i++) {
        if(last[s.charAt(i)] >= start){  // 当出现重复字符时
            // 更新最大子串长度和重复出现的字符的下一位置
            maxLen = Math.max(i - start, maxLen);
            start = last[s.charAt(i)] + 1;
        }
        last[s.charAt(i)] = i;   // 记录或更新字符出现的位置
    }
    return Math.max(s.length() - start, maxLen);
}
```
