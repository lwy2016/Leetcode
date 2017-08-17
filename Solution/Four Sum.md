Description:

Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target

Note: The solution set must not contain duplicate quadruplets.

>For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]


Solution:
下面的代码相当于用了3层循环，尽管能Accepted，但是时间复杂度过大，
```java
public List<List<Integer>> fourSum(int[] nums, int target) {
        
    List<List<Integer>> list = new ArrayList<List<Integer>>();
    
    Arrays.sort(nums);
    
    for ( int i = 0; i < nums.length - 3; i++){
        int m = i+1;
        while( m < nums.length - 2 ){
            int j = m + 1, k = nums.length - 1;
            while( j < k ){
                if( nums[i] + nums[m] + nums[j] + nums[k] == target){ // 存在res == target 则返回该target
                    list.add(Arrays.asList(nums[i], nums[m], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (nums[i] + nums[m] + nums[j] + nums[k] > target){   // res > target 
                    
                    k--;
                } else if (nums[i] + nums[m] + nums[j] + nums[k] < target){  // res < target 
                    
                    j++;
                }
                
            }
            m++;
        }
    }
    Set set  =   new  HashSet();    // 去除 list 中重复的元素
    List newList  =   new  ArrayList(); 
    for (Iterator  iter  =  list.iterator(); iter.hasNext();)   { 
         Object element  =  iter.next(); 
         if  (set.add(element)) 
            newList.add(element); 
     } 
     list.clear(); 
     list.addAll(newList); 
    return list;
}	
```
