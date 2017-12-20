Description:

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.
>
   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

Solution:

这题如果不通过某种规律来做，其实是难的，难到你对他没有想法[捂脸]

看 Disscuss 区大牛对此题的详尽解析是找出 二叉排序树的构造规律

G(n)： the number of unique BST for a sequence of length n 长度为n的unique BST的个数
F(i, n), 1 <= i <= n: the number of unique BST, where the number is the root of BST, and the sequence ranges form 1 to n 

G(n) = F(1, n) + F(2, n) + ... + F(n, n)

其中G(0) = G(1) = 1 

如 F(3, 7) = number of left subtree unique BST * number of right subtree unique BST = G(2) * G(4)

即 F(i, n) = G(i - 1) * G(n - i)  1 <= i <= n 

故 G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 

```java
public int numTrees(int n) {
    int[] G = new int[n + 1];
    G[0] = G[1] = 1;

    for (int i = 2; i <= n; i++) {
        for (int j = 1; j <= i; j++) {
            G[i] += G[j - 1] * G[i - j];
        }
    }

    return G[n];
}
```