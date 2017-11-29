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

先对某个单词转成字符数组进行排序，将排序后的结果转成字符串，作为map的key值，

```java
public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> res = new ArrayList<List<String>>();
    if(strs.length == 0) return res;
    // map用来存放, 单词排序后的结果，如 "aacddd", 第二个参数用来存储原来的字符串存入的list
    HashMap<String, List<String>> map = new HashMap<String, List<String>>();

    for(String str : strs){  // 将每个词排序后，根据这个键值，找到哈希表中相应的列表，并添加进去
        char[] c = str.toCharArray();
        Arrays.sort(c);     // sort(int[] ) 对数组中的字母进行排序，如 'a', 'a', 'c', 'd', 'd', 'd'

        String key = new String(c);
        List<String> list = map.get(key);
        if(list == null){
            list = new ArrayList<String>();
        }
        list.add(str);   // list存储原数据，key为排序好的字符串
        map.put(key, list);
    }
    for(String key : map.keySet()){
        List<String> cur = map.get(key);
        Collections.sort(cur);   // 对List<String>中的字符串进行排序
        res.add(cur);
    }
    return res;
}
```


