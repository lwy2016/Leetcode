Description:

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

杨辉三角

Solution:

```java
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList<Integer>();
        
        if (numRows < 1) return res;
        
        res.add(Arrays.asList(1));
        
        for (int i = 1; i < numRows; i++) {
            list.add(1);
            for (int j = 1; j <= i; j++) {
                list.add(res.get(i - 1).get(j - 1) + (j == i ? 0 : res.get(i - 1).get(j)));
            }
            
            res.add(new ArrayList<Integer>(list));
            list.clear();
        }
        
        return res;
    }
```