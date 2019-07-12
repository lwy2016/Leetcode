Description:

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.


Solution:

使用Stack
看LeetCode官方给出的解题报告：

Instead of finding every possible string and checking its validity, we can make use of stack while scanning the given string to check if the string scanned so far is valid, and also the length of the longest valid string. In order to do so, we start by pushing -1−1 onto the stack.

For every \text{‘(’}‘(’ encountered, we push its index onto the stack.

For every \text{‘)’}‘)’ encountered, we pop the topmost element and subtract the current element's index from the top element of the stack, which gives the length of the currently encountered valid string of parentheses. If while popping the element, the stack becomes empty, we push the current element's index onto the stack. In this way, we keep on calculating the lengths of the valid substrings, and return the length of the longest valid string at the end.

stack存储'('的位置，而不是存储它本身

```java
public int longestValidParentheses(String s) {
    int max = 0;
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(-1);

    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == '('){
            stack.push(i);
        } else {
            stack.pop();
            if(stack.empty()){
                stack.push(i);
            } else {
                max = Math.max(max, i - stack.peek());
            }
        }
    }
    return max;
}
```