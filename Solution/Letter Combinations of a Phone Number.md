Desciption:

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

![leetcode-17](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-17.png)

```
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

Solution:

backtracking 回溯

```java
public List<String> letterCombinations(String digits) {
    List<String> list = new ArrayList<String>();
    String[] charMap = new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    if(digits == null || digits.length() == 0) return list;
    getLetter(list, charMap, new char[digits.length()], digits, 0);
    
    return list;
}

public void getLetter(List<String> list,String[] charMap, char[] charStr, String digits, int index){  
    if(index == digits.length()){
        list.add(new String(charStr));
//      list.add(new String(sb.toString()));
        return ;   // return 运用的技巧
    }
    int x = digits.charAt(index) - '0';
    for(int i = 0; i < charMap[x].length(); i++){
        charStr[index] = charMap[x].charAt(i);
        getLetter(list, charMap, charStr, digits, index+1);
        // 使用 StringBuilder这样写
//      int sbLen = sb.length();
//      getLetter(list, charMap, sb.append(charMap[x].charAt(i)), digits, index+1);
//      sb.setLength(sbLen);
    }
}
```

