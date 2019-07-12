## 动归

1. 三角形找一条从顶到底的最小路径		Triangle
2. 最大子数组和		Maximum Subarray
	f = Math.max(f + arr[i], arr[i]);
	max = Math.max(max, f);
3. 回文最小划分次数	Palindrome Partitioning II
	f[j][i] = c[i] == c[j] && (j + 1 > i - 1 || f[j+1][i-1])
	min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1)
4. 最佳时间买卖股票 	Best Time to Buy and Sell Stock III
	[0:i], [i:n-1] 先求左侧最大值，存到数组中，在求右侧最大值，并记录两者最大和
5. 判断字符串s3是否由s1,s2交叉存取组成 	Interleaving String  
	(n+1)
	(f[i][j] = s1[i-1] == s3[i+j-1] && f[i-1][j]) || (s2[j-1] == s3[i+j-1] && f[i][j-1])
6. 给定一个矩形表格，求从顶到底的最小和		Minimum Path Sum
	f[i][j] = min(f[i - 1][j], f[i][j - 1]) + arr[i][j]
7. 使两个字符串相等，最小的编辑次数  Edit Distance
	(n+1)
	f[i][j] = f[i-1][j-1]
	f[i][j] = min(f[i-1][j-1], f[i-1][j], f[i][j-1]) + 1
8. 求有多少种解码方式 	Decode Ways
	f[i] = f[i] + f[i - 2]
	f[i] = f[i] + f[i - 1]

