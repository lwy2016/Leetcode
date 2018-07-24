Description:

Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh"

Solution:

```
public String reverseString(String s) { 
    char[] c = s.toCharArray();
    for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
    return new String(c);
}
```
