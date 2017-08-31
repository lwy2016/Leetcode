Description:

Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:
```
[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
Note: All inputs will be in lower-case.

Solution:

将每个词排序后，根据这个键值，找到哈希表中相应的列表，并添加进去

```java
public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> res = new ArrayList<List<String>>();
    // if(strs.length == 0) return res;
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    for(String str : strs){  // 
        char[] c = str.toCharArray();
        Arrays.sort(c);
        String key = new String(c);
        List<String> list = map.get(key);
        if(list == null){
            list = new ArrayList<String>();
        }
        list.add(str);
        map.put(key, list);
    }
    for(String key:map.keySet()){
        List<String> cur = map.get(key);
        Collections.sort(cur);   // 对List<String>进行排序
        res.add(cur);
    }
    return res;
}
```


