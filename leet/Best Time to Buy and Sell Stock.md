Description:

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
>Input: [7, 1, 5, 3, 6, 4]
Output: 5
max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

Example 2:
>Input: [7, 6, 4, 3, 1]
Output: 0
In this case, no transaction is done, i.e. max profit = 0.

Solution:
交易1次

I限制了只能买卖一次。于是要尽可能在最低点买入最高点抛出。这里的一个隐含的限制是抛出的时间必须在买入的时间之后。所以找整个数组的最大最小值之差的方法未必有效，因为很可能最大值出现在最小值之前。但是可以利用类似思路，在扫描数组的同时来更新一个当前最小值minPrice。这样能保证当扫到i时，minPrices必然是i之前的最小值。当扫到i时：

如果prices[i] < minPrice，则更新minPrice = prices[i]。并且该天不应该卖出。
如果prices[i] >= minPrice，则该天可能是最好的卖出时间，计算prices[i] - minPrice，并与当前的单笔最大利润比较更新。

```java
public int maxPrice(int[] prices){
	int max = 0, minPrice = prices[0];

	for(int i = 0; i < prices.length; i++){
		if(prices[i] < minPrice){
			minPrice = prices[i];
		} else {
			max = Math.max(max, prices[i] - minPrice);
		}
	}

	return max;
}
```