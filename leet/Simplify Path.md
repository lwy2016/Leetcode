Description:

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Solution:

使用堆来处理

```java
public String simplifyPath(String path) {
    Deque<String> stack = new LinkedList<String>();
    
    Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));

    for (String dir : path.split("/")) {
        if(dir.equals("..") && !stack.isEmpty()) {
            // 是 ".." 的时候，弹出前面的路径
            stack.pop();
        } else if (!skip.contains(dir)) {
            // 不是 "." 或 "." 的时候，直接push 进去
            stack.push(dir);
        }
    }
    String res = "";
    for (String dir : stack) {
        res = "/" + dir + res;
    }
    return res.isEmpty() ? "/" : res;
}
```
