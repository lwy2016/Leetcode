Description:

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

UPDATE (2017/1/4):
The wordDict parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.

Solution:

```java
public boolean wordBreak(String s, List<String> wordDict) {
    if(s == null || s.length() == 0) return true;
    if(wordDict == null || wordDict.isEmpty()) return false;
    
    // find max length of wordDict
    int word_max_len = 0;
    for(String word : wordDict){
        word_max_len = Math.max(word_max_len, word.length());
    }
    // 0--i-1, i--k-1, k --
    boolean can_break[] = new boolean[s.length() + 1];
    can_break[0] = true;
    
    for(int i = 1; i <= s.length(); i++){
        for(int j = i - 1; j >= 0; j--){
            if(i - j > word_max_len) break;
            
            String word = s.substring(j, i);
            if(can_break[j] && wordDict.contains(word)){
                can_break[i] = true;
                break;
            }
        }
    }
    return can_break[s.length()];
}
```

