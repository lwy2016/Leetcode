Description:

Write a function to find the longest common prefix string amongst an array of strings.

Solution:

LeetCode解题报告：

##水平扫描

```java
public String longestCommonPrefix(String[] strs) {
    if(strs.length == 0) return "";
    String prefix = strs[0];
    
    // 这里 "abc".indexOf("") = 0, "abc".indexOf("abc") = 0
    for(int i = 1; i < strs.length; i++){
        while(strs[i].indexOf(prefix) != 0){
            prefix = prefix.substring(0, prefix.length() - 1);
            if(prefix.isEmpty()) return "";
        }
    }
    return prefix;
}
```

## 垂直扫描

```java
public String longestCommonPrefix(String[] strs) {
    if(strs.length == 0) return "";

    for(int i = 0; i < strs[0].length(); i++){   // 以数组中第一个字符串为参考
        char c = strs[0].charAt(i);
        for(int j = 1; j < strs.length; j++){
            // 后续的字符串，当长度为 i的时候，说明它是当前长度最小的一个字符串，且匹配所有的前缀 
            // 这一列中有不匹配的字符
            if(i == strs[j].length() || strs[j].charAt(i) != c){
                return strs[0].substring(0, i);
            }
        }
    }
    return strs[0];
}
```
