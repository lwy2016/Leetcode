Description:

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

>[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]

Solution:

自己做出来哒题

使用回溯

```
public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if (n < 0) return res;
        
        List<Character> list = new ArrayList<Character>();
        helper(res, n, list);
        return res;
    }
    
    private void helper(List<String> res, int n, List<Character> list) {
        if (list.size() == 2 * n) {
            StringBuilder sb = new StringBuilder();
            if (checkValid(list) == false) return ;
            for (char c : list) {
                sb.append(c);
            }
            res.add(new String(sb));
            
            return ;
        }
        
        list.add('(');
        helper(res, n, list);
        list.remove(list.size() - 1);
        list.add(')');
        helper(res, n, list);
        list.remove(list.size() - 1);
    }
    
    private boolean checkValid(List<Character> list) {
        Stack<Character> stack = new Stack<Character>();
        int count = 0;
        for (char c : list) {
            if (c == '(') {
                stack.push(c);
                count++;
            } else {
                if (!stack.isEmpty() && '(' == stack.pop()) {
                    count--;
                } else {
                    return false;
                }
            }
        }
        if (count != 0) return false;
        
        return true;
    } 
```