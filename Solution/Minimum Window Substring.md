Description:

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.\

Solution:

哦，这题有点难诶，没看懂答案

双指针，动态维护一个区间。尾指针不断往后扫，当扫到有一个窗口包含了所有T的字符后，然后再收缩头指针，直到不能收缩为止。最后记录所有可能的情况中窗口最小的


```java
    public String minWindow(String s, String t) {
        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();
        int[] map = new int[256];
        int start = 0, end = 0;   // 头指针，尾指针
        int min_length = Integer.MAX_VALUE, min_start = 0;   // 窗口大小，起点
        int count = t_array.length;  // t的长度

        for(int i = 0; i < t_array.length; i++)
            map[t_array[i]] ++;
        
        // 尾指针不断往后扫
        while(end < s_array.length){
            if(map[s_array[end]] > 0) {  // this char is a part of T
                count--;
            }
            map[s_array[end]]--;
            while(count == 0) {  //  完整包含一个了 T
                if((end - start + 1) < min_length) {
                    min_length = end - start + 1;
                    min_start = start;
                }
                map[s_array[start]] ++;
                if(map[s_array[start]] > 0) {
                    count ++;
                }
                start++;
            }
            end++;
        }
        if( min_start+min_length > s_array.length)
            return "";
        return s.substring(min_start, min_start+min_length);
    }    
```