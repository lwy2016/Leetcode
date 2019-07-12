Description:

Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

Solution:

把 单链表 转成 数组，然后再转成二叉树

```java
	public TreeNode sortedListToBST(ListNode head) {
        int size = 0;
        ListNode list = head;
        
        while (list != null) {
            size++;
            list = list.next;
        }
        
        int[] nums = new int[size];
        list = head;
        int index = 0;
        while (list != null) {
            nums[index++] = list.val;
            list = list.next;
        }
        
        return helper(nums, 0, nums.length - 1);
    }
    
    public TreeNode helper(int[] nums, int l, int r) {
        if (l > r) return null;
        if (l == r) return new TreeNode(nums[l]);
        
        int mid = (r - l) / 2 + l;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, l, mid - 1);
        root.right = helper(nums, mid + 1, r);
        return root;
    }
```

递归，快慢指针找到中间节点，二分生成节点

```java
	public TreeNode sortedListToBST(ListNode head) {
        ListNode slow = head, fast = head, prev = head;
        
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        prev.next = null; 
        
        TreeNode root = new TreeNode(slow.val); 
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        
        return root; 
        
    }
```