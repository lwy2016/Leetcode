Description:

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:

```
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
```

Solution:
逆波兰表达式的解释器一般是基于堆栈的：
操作数入栈；遇到操作符时，操作数出栈，求值，将结果入栈；当一遍后，栈顶就是表达式的值

```java
public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<Integer>();   // +,-,*,/ do not push in stack, just Integer type number
    int x, y;
    
    for (int i = 0; i < tokens.length; i++){
        if("+".equals(tokens[i]) || "-".equals(tokens[i]) || "*".equals(tokens[i]) || "/".equals(tokens[i])){
            x = stack.pop();
            y = stack.pop();
            if("+".equals(tokens[i])) y = y + x;
            if("-".equals(tokens[i])) y = y - x;
            if("*".equals(tokens[i])) y = y * x;
            if("/".equals(tokens[i])) y = y / x;
            
            stack.push(y);
        } else {
            stack.push(Integer.valueOf(tokens[i]));   // Parse to Integer before push in stack
        }
    }
    
    return stack.peek();
}
```