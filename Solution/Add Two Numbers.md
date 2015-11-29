Question descripted:

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

---

根据题意需要考虑到以下几点：
1.链表l1或l2为空时，直接返回，这是边界条件
2.l1,l2可能长度不同，因此要注意处理某个链表剩余的高位
3.可能产生最高位的进位

version1
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int val1 = l1.val;
		int val2 = l2.val;
		int next = (val1+val2>=10)?1:0;
		l1.val = val1+val2;
		ListNode ll = l1;
		l1 = l1.next;
		l2 = l2.next;
		while(null != l1){
			val1 = l1.val;
			val2 = l2.val;
			l1.val = (val1+val2+next>=10)?(val1+val2+next-10):(val1+val2+next);
			next = (val1+val2+next>=10)?1:0;
			l1 = l1.next;
			l2 = l2.next;
		}
		
		return ll;
    }
```

Runtime Error Message: 		Line 20: java.lang.NullPointerException
Last executed input:		[1,8]
							[0]
这个出错的原因就是会出现某一个链表为空，不设条件判断就会出现空指针异常


version2
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 将链表长度大表设为 l1, 短的设为 l2
        ListNode lll1 = l1;
		ListNode lll2 = l2;
		int n1 = 0;
		int n2 = 0;
		while(null != lll1){
			n1++;
			lll1 = lll1.next;
		}
		while(null != lll2){
			n2++;
			lll2 = lll2.next;
		}
		if(n1 < n2){
			ListNode temp = null;
			temp = l1;
			l1 = l2;
			l2 = temp;
		}
		
		int val1 = l1.val;
		int val2 = l2.val;
		int next = (val1+val2>=10)?1:0;
		l1.val = val1+val2;
		ListNode ll = l1;
		l1 = l1.next;
		l2 = l2.next;
		while(null != l1 && null != l2){
			val1 = l1.val;
			val2 = l2.val;
			l1.val = (val1+val2+next>=10)?(val1+val2+next-10):(val1+val2+next);
			next = (val1+val2+next>=10)?1:0;
			l1 = l1.next;
			l2 = l2.next;
		}
		
		return ll;
```
Input:		[5]
			[5]
Output:		[10]
Expected:	[0,1]
链表长度是否为空的问题虽然解决了，但是此时又出现了一个新问题，当5+5=10的时候如何去合成结果，首先要正确的判断出来，接着就要新增节点表示进位
由于节点为null的时候不能新增节点，所以在最高位有进位的时候再重新便利一次链表，
```java
if(ll.next != null){
	ll.next = new Node(1);
}
```

version3
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 将链表长度大表设为 l1, 短的设为 l2
        ListNode lll1 = l1;
		ListNode lll2 = l2;
		int n1 = 0;
		int n2 = 0;
		while(null != lll1){
			n1++;
			lll1 = lll1.next;
		}
		while(null != lll2){
			n2++;
			lll2 = lll2.next;
		}
		if(n1 < n2){
			ListNode temp = null;
			temp = l1;
			l1 = l2;
			l2 = temp;
		}
		
		int val1 = l1.val;
		int val2 = l2.val;
		l1.val = (val1+val2>=10)?(val1+val2-10):(val1+val2);
		int next = (val1+val2>=10)?1:0;
		ListNode ll = l1;
		ListNode lll = ll;
		l1 = l1.next;
		l2 = l2.next;
		while(null != l1 && null != l2){
			val1 = l1.val;
			val2 = l2.val;
			l1.val = (val1+val2+next>=10)?(val1+val2+next-10):(val1+val2+next);
			next = (val1+val2+next>=10)?1:0;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(null != l1){
			val1 = l1.val;
			l1.val = (val1+next>=10)?(val1+next-10):(val1+next);
			next = (val1+next>=10)?1:0;
			l1 = l1.next;
		}
		// 如何解决新增进位节点 成为解决本题的关键
		if(l1 == null && next == 1 ){
			while(ll.next != null){
				ll = ll.next;
			}
			ll.next = new ListNode(1);
		}
		
		return lll;
    }
```

version4
```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2){
		int carry = 0;
		ListNode newHead = new ListNode(0);
		ListNode p1 = l1;
		ListNode p2 = l2;
		ListNode p3 = newHead;
		
		while(p1 != null || p2 != null){
			if(p1 != null){
				carry += p1.val;
				p1 = p1.next;
			}
			if(p2 != null){
				carry += p2.val;
				p2 = p2.next;
			}
			
			p3.next = new ListNode(carry%10);
			p3 = p3.next;
			carry /= 10;
		}
		
		if(carry == 1){
			p3.next = new ListNode(1);
		}
		
		return newHead.next;
	}
```
