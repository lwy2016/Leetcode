Description:

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Solution:

不使用堆
```java

```

使用堆
```java
public boolean isValid(String s) {
        if(s.length() < 2) return false;
        char[] c = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('[', ']');
        map.put('(', ')');
        map.put('{', '}');
        
        if(map.get(c[0]) == null){
            return false;
        }
        stack.add(c[0]);
        
        for(int i = 1, L = c.length; i < L; i++){
            char ch = c[i];
            if( ch == '[' || ch == '(' || ch == '{'){
                stack.push(ch);
            } else if(!stack.isEmpty() && map.get(stack.peek()) == ch) {
                stack.pop();
            } else {
                return false;
            }
        }
        
        return stack.size() == 0 ? true: false;
    }
```
