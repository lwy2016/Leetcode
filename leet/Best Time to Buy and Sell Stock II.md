Description:

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Solution:
交易多次

II并没有限制总的买卖次数，只限制了当天只能买或卖。所以可以采用greedy的方法，来获得所有可能的正利润。以如下序列说明：

2 1 3 4 5 4 2 8 7

只要prices[i] - prices[i-1]>0，我们就在第i-1天买入，第i天抛出。这样可以包括所有可能赚取利润的区间。

```java
public int maxProfit(int[] prices) {
    if(prices.length == 0) return 0;
    int count = 0, price = prices[0];
    
    for(int i = 0; i < prices.length; i++){
        if(price > prices[i]){
            price = prices[i];
        } else {
            count += prices[i] - price; 
            price = prices[i];
        }
    }
    
    return count;
}
```