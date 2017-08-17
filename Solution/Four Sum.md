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
    
    for ( int i = 0, L =  nums.length; i < L - 3; i++){
        int m = i+1;
        while( m < L - 2 ){
            int j = m + 1, k = L - 1;
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
逻辑清晰的解法
```java
public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0, L=nums.length; i<L-3; i++) {
            if(nums[i]<<2 > target) return list; // return immediately  最小值的4倍超出 taget值，计算结束
            for(int m=L-1; m>i+2; m--) {        // j 在 1,2,3....length-2之间
                if(nums[m]<<2 < target) break; // break immediately   最大值的4倍小于target，结束本循环
                int rem = target-nums[i]-nums[m];   // rem 为中间两个数字的和
                int j = i+1, k=m-1;
                while(j < k) {
                    int sum = nums[j] + nums[k];
                    if(sum>rem) --k;    // sum > rem 说明两者之和过大，需要变小
                    else if(sum<rem) ++j;
                    else {
                        list.add(Arrays.asList(nums[i],nums[j],nums[k],nums[m]));
                        while(++j<=k && nums[j-1]==nums[j]) continue; // avoid duplicate results
                        while(--k>=j && nums[k]==nums[k+1]) continue; // avoid duplicate results
                    }
                }
                while(m>=1 && nums[m]==nums[m-1]) --m; // skip inner loop
            }
            while(i<L-1 && nums[i]==nums[i+1]) ++i; // skip outer loop
        }
        return list;
    }
```
打败所有人的解法
```java
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (nums == null || len < 4)
            return res;

        Arrays.sort(nums);

        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && z == nums[i - 1])// avoid duplicate
                continue;
            if (z + 3 * max < target) // z is too small
                continue;
            if (4 * z > target) // z is too large
                break;
            if (4 * z == target) { // z is the boundary
                if (i + 3 < len && nums[i + 3] == z)
                    res.add(Arrays.asList(z, z, z, z));
                break;
            }

            threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
        }

        return res;
    }

    /*
     * Find all possible distinguished three numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, the three numbers))
     */
    public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
            int z1) {
        if (low + 1 >= high)
            return;

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target)
            return;

        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[i];
            if (i > low && z == nums[i - 1]) // avoid duplicate
                continue;
            if (z + 2 * max < target) // z is too small
                continue;

            if (3 * z > target) // z is too large
                break;

            if (3 * z == target) { // z is the boundary
                if (i + 1 < high && nums[i + 2] == z)
                    fourSumList.add(Arrays.asList(z1, z, z, z));
                break;
            }

            twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
        }

    }

    /*
     * Find all possible distinguished two numbers adding up to the target
     * in sorted array nums[] between indices low and high. If there are,
     * add all of them into the ArrayList fourSumList, using
     * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
     */
    public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
            int z1, int z2) {

        if (low >= high)
            return;

        if (2 * nums[low] > target || 2 * nums[high] < target)
            return;

        int i = low, j = high, sum, x;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

                x = nums[i];
                while (++i < j && x == nums[i]) // avoid duplicate
                    ;
                x = nums[j];
                while (i < --j && x == nums[j]) // avoid duplicate
                    ;
            }
            if (sum < target)
                i++;
            if (sum > target)
                j--;
        }
        return;
    }
```