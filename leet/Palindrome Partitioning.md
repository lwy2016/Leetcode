Description:

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return
>
[
  ["aa","b"],
  ["a","a","b"]
]

Solution:

```java
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (s == null || s.isEmpty()) return result;

        List<String> palindromes = new ArrayList<String>();
        dfs(s, 0, palindromes, result);

        return result;
    }

    private void dfs(String s, int pos, List<String> palindromes, List<List<String>> ret) {

        if (pos == s.length()) {
            ret.add(new ArrayList<String>(palindromes));
            return;
        }

        for (int i = pos + 1; i <= s.length(); i++) {
            String substr = s.substring(pos, i);
            // 直到是回文字符串的时候，
            if (!isPalindrome(substr)) {
                continue;
            }
            // 才将该回文字符子串添加到List中
            palindromes.add(substr);
            dfs(s, i, palindromes, ret);
            // 回溯，退出最后面的一个 回文数字符串
            palindromes.remove(palindromes.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) return false;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) return false;
        }

        return true;
    }
```