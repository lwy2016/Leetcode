Description:

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

Solution:

The idea is that for the result distinct Interval, the latter one's start must > previous one's end.

```java
    public List<Interval> merge(List<Interval> intervals) {
        // sort starts & ends respectively
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        for (int i = 0; i < n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        List<Interval> res = new ArrayList<Interval>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            start = starts[i];
            while (i < n - 1 && starts[i + 1] <= ends[i]) {
                i++;
            }
            end = ends[i];
            res.add(new Interval(start, end));
        }
        return res;
    }
```