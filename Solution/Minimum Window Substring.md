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
        if (s == null || s.length() == 0) return "";
        if (s.length() < t.length()) return "";

        char[] s_array = s.toCharArray();
        char[] t_array = t.toCharArray();
        int[] map = new int[256];  // 映射ASCII码，当做HashMap使用
        int start = 0, end = 0;   // 头指针，尾指针
        int min_length = Integer.MAX_VALUE, min_start = 0;   // 窗口大小，记录最小窗口的起点
        int count = t_array.length;  // 设置为 t 的长度，尾指针指向s中出现一次t中的字符，则count--; 头指针指向s中出现一次t的字符，则count++

        for(int i = 0; i < t_array.length; i++)  // map统计 t 中字符出现的次数
            map[t_array[i]] ++;
        
        // 尾指针不断往后扫
        while(end < s_array.length){
            // 判断t中的字符是否在 s 中出现，若出现，则count--
            if(map[s_array[end]] > 0) {  // this char is a part of T, 将滑动窗口
                count--;
            }
            map[s_array[end]]--;  // 凡是被尾指针遍历过的，均需要map--

            while(count == 0) {  //  完整包含一个了 T
                if((end - start + 1) < min_length) {  // 当该窗口比原窗口还小的时候，更新最小窗口的起点和长度
                    min_length = end - start + 1;
                    min_start = start;
                }
                
                map[s_array[start]]++;  // 凡是被头指针遍历过的，均需要map++
                // 若start指针指向的那个字符是t中所有的，count++, s中start，end指针之间包含的窗口中，属于t的字符就少了1个
                if(map[s_array[start]] > 0) { 
                    count++;
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