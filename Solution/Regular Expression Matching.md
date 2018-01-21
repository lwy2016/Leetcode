Description:

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

Solution:

解法的核心理念是：从后往前看pattern的每一位，对于pattern的每一位，我们尽可能的把待匹配串string从后往前给匹配上。我们用一个数组match[string.length() + 1]来表示string被匹配的情况，这里如果match[j]是true，而我们pattern正指向第i位，则说明string从第j位到最后都已经被pattern第i位之前的某些部分给成功匹配了，所以我们不用再操心了。match[i]为true的条件是match[i + 1]为true，且string第i个字符也能被成功匹配。

那我们就可以从后往前开始看pattern的每一位能匹配多少string的字符了：

 - 如果pattern的这一位是*，那我们要用这一位，来从后往前尝试匹配string，因为string后面是已经匹配好的，前面是还没匹配好的，所以从前往后匹配星号可能会导致我们匹配了一些pattern该星号前面的星号应该匹配的部分。而从后往前匹配则不会影响pattern该星号后面星号所匹配的部分，因为已经匹配的部分我们会直接跳过。
 - 如果pattern这一位不是*，那我们则不能匹配多个字符，我们只能匹配一个字符，这时候要对string从前往后匹配，因为如果后面没被匹配，前面也肯定不会被匹配，所以从前向后能保证我们把pattern的这一位匹配到string当前最后面那个还没匹配的字符。这样如果那个字符能被匹配就通过了。
 
```java

```