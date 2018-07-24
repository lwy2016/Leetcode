Description:

Implement strStr().
Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Solution:

暴力解决

```java
public int strStr(String haystack, String needle) {
    if(haystack == null || needle == null || needle.length()==0) 
        return 0;
    if(needle.length()>haystack.length())  
        return -1;  
    int start = 0,res = 0;
    for(int i = 0; i < haystack.length(); i++){
        int cur = i;
        for(int j = 0; j < needle.length(); j++){
            if(cur < haystack.length() && needle.charAt(j) == haystack.charAt(cur) ){
                if(j == 0){   // needle的第一个字符匹配时，记录下当前 i 的位置
                    res = cur;
                }
                cur++;
                if(needle.length()-1 == j){   // needle全部匹配，返回它的在haystack中首次匹配的位置
                    return res;
                }
            }  else {       // 有字符不匹配时，
                break;  // 跳出本次循环
            }
        }
    }
    return -1;
}
```
提交超时了,测试代码很疯狂


双指针 Two Points
通过测试

双指针算法，循环次数为两者长度之差，并且设置flag当第一匹配的时候就返回对应的index，出现不匹配的情况时，及时break掉，
```java
public int strStr(String haystack, String needle) {
    if(haystack == null || needle == null || needle.length()==0){
        return 0;
    } 
    if(needle.length()>haystack.length()){
        return -1;  
    } 

    for(int i = 0; i <= haystack.length() - needle.length(); i++){
        boolean successFlag = true; 
        for(int j = 0; j < needle.length(); j++){
            if(haystack.charAt(i+j) != needle.charAt(j)){
                successFlag = false;
                break;
            }
        }
        if(successFlag){
            return i;
        }
    }
    
    return -1;
}
```

KMP算法：
看讲解没看懂，233,下面裂解是对KMP的讲解及实现

[洗刷刷](http://bangbingsyb.blogspot.com/2014/11/leetcode-implement-strstr-kmp.html)
[matrix](http://www.matrix67.com/blog/archives/115)
[segment](https://segmentfault.com/a/1190000003707284)
