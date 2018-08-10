Question descripted:

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

---

根据题意需要考虑到以下几点：
1.链表l1或l2为空时，直接返回，这是边界条件
2.l1,l2可能长度不同，因此要注意处理某个链表剩余的高位
3.可能产生最高位的进位

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	ListNode dummy = new ListNode(-1);
	ListNode list = dummy; 
	int carry = 0;

	while (l1 != null || l2 != null) {
		if (l1 != null) {
			carry += l1.next;
			l1 = l1.next;
		}
		if (l2 != null) {
			carry += l2.val;
			l2 = l2.next;
		}

		list.next = new ListNode(carry % 10);
		list = list.next;
		carry /= 10;
	}

	list.next = carry == 1 ? new ListNode(1) : null;

	return dummy.next;
}
```