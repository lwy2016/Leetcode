Description:

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

Solution:


```java
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    int n = intervals.size() + 1;
    int[] starts = new int[n];
    int[] ends = new int[n];
    
    for (int i = 0; i < n - 1; i++) {
        starts[i] = intervals.get(i).start;
        ends[i] = intervals.get(i).end;
    }
    starts[n - 1] = newInterval.start;
    ends[n - 1] = newInterval.end;
    
    Arrays.sort(starts);
    Arrays.sort(ends);
    
    int start = 0;
    int end = 0;
    List<Interval> res = new ArrayList<Interval>();
    for (int i = 0; i < n; i++) {
        start = starts[i];
        while (i < n - 1 && starts[i+1] <= ends[i]) {
            i++;
        }
        end = ends[i];
        res.add(new Interval(start, end));
    }
    return res;
} 
 ```