Description:

Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.

Example:

Input: 3
Output: [1,3,3,1]

Could you optimize your algorithm to use only O(k) extra space?


Solution:

要求用 O(K)的空间复杂度

每次在 list 的首位添加1， f(i)=f(i)+f(i+1)

```java
 	public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) return list;
        
        list.add(1);
        
        for (int i = 1; i <= rowIndex; i++) {
            list.add(0, 1); 
            for (int j = 1; j < i; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }
        
        return list;
    }
```


1st: 1=1
2nd: 6= 6 / 1
3rd: 15=6x5 / (1x2)
4th: 20=6x5x4 / (1x2x3)
5th: 15=6x5x4x3 / (1x2x3x4)
6th: 6 =6x5x4x3x2 / (1x2x3x4x5)
7th: 1 =6x5x4x3x2x1 / (1x2x3x4x5x6)

时间复杂度 O(n), 空间复杂度O(1)

```java
	public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) return list;
        
        list.add(1);
        long res = 1;
        int a = rowIndex, b = 1;
        
        for (int i = 1; i <= rowIndex; i++) {
            res *= a;
            res /= b;
            
            list.add((int)res);
            a--;
            b++;
        }
        
        return list;
    }
```