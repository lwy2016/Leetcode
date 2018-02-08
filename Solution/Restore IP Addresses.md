Description:

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Solution:

dfs

```java
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();   
        dfs(res, s, "", 0);
        return res;
    }
    
    private void dfs(List<String> res, String s, String temp, int count) {
        if (count == 4) {
            if (s.length() == 0) {
                res.add(temp.substring(0, temp.length() - 1));  // 截断最后一个.
            }
            return ;
        }
        
        for (int i = 1; i <= 3; i++) { // 每个位置可以有的长度，1位，2位，3位
            if (s.length() < i) continue;  //  如果长度不够，则要进行下一循环
            int val = Integer.parseInt(s.substring(0, i));
            if (val > 255 || i != String.valueOf(val).length()) continue;  // 大于 255 或者 05，088的首位是0 的情况，则要进行下一循环
            
            dfs(res, s.substring(i), temp + s.substring(0, i) + ".", count + 1);
        }
    }
```
