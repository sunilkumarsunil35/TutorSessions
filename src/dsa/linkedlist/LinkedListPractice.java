package dsa.linkedlist;

import dsa.common.ListNode;

/** More linked-list practice: intersections, cycles, reordering and group reversal. */
public final class LinkedListPractice {
    private LinkedListPractice() {}

    /** Node with an extra random pointer, for LC 138. */
    public static class RandomNode {
        public int val;
        public RandomNode next;
        public RandomNode random;

        public RandomNode(int val) {
            this.val = val;
        }
    }

    /**
     * LC 160 Intersection of Two Linked Lists. Each pointer walks its own list then the other;
     * both cover a + b + c steps, so they meet at the intersection (or both reach null).
     *
     * <p>Time O(n + m). Space O(1).
     */
    public static ListNode getIntersectionNode(ListNode a, ListNode b) {
        ListNode pa = a;
        ListNode pb = b;
        while (pa != pb) {
            pa = pa == null ? b : pa.next;
            pb = pb == null ? a : pb.next;
        }
        return pa;
    }

    /**
     * LC 234 Palindrome Linked List. Find the middle, reverse the second half, compare the
     * halves. Modifies the list.
     *
     * <p>Time O(n). Space O(1).
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode second = LinkedListProblems.reverseList(LinkedListProblems.middleNode(head));
        for (ListNode a = head, b = second; b != null; a = a.next, b = b.next) {
            if (a.val != b.val) {
                return false;
            }
        }
        return true;
    }

    /**
     * LC 142 Linked List Cycle II: return the node where the cycle starts. After slow and fast
     * meet, a pointer from the head and one from the meeting point reach the start together.
     *
     * <p>Time O(n). Space O(1).
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                for (ListNode p = head; p != slow; p = p.next) {
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * LC 2 Add Two Numbers (digits stored in reverse order). Add digit by digit with a carry.
     *
     * <p>Time O(max(n, m)). Space O(1) besides the output.
     */
    public static ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next;
    }

    /**
     * LC 143 Reorder List: L0, Ln, L1, Ln-1, ... Split at the middle, reverse the second half,
     * then weave the halves together.
     *
     * <p>Time O(n). Space O(1).
     */
    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = LinkedListProblems.reverseList(slow.next);
        slow.next = null;
        for (ListNode first = head; second != null; ) {
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;
            first.next = second;
            second.next = nextFirst;
            first = nextFirst;
            second = nextSecond;
        }
    }

    /**
     * LC 138 Copy List with Random Pointer without a map. Insert each copy right after its
     * original, so original.random.next is the copy's random; then split the lists apart.
     *
     * <p>Time O(n). Space O(1) besides the copy.
     */
    public static RandomNode copyRandomList(RandomNode head) {
        for (RandomNode n = head; n != null; n = n.next.next) {
            RandomNode copy = new RandomNode(n.val);
            copy.next = n.next;
            n.next = copy;
        }
        for (RandomNode n = head; n != null; n = n.next.next) {
            n.next.random = n.random == null ? null : n.random.next;
        }
        RandomNode dummy = new RandomNode(0);
        RandomNode tail = dummy;
        for (RandomNode n = head; n != null; n = n.next) {
            tail.next = n.next;
            tail = tail.next;
            n.next = n.next.next;
        }
        return dummy.next;
    }

    /**
     * LC 287 Find the Duplicate Number (n + 1 values in [1, n], read-only, O(1) space). Treat
     * i -> nums[i] as a linked list; the duplicate is where its cycle starts (Floyd).
     *
     * <p>Time O(n). Space O(1).
     */
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * LC 25 Reverse Nodes in k-Group. For each full group of k, reverse it in place and stitch
     * it between the node before it and the node after it. A short final group stays as is.
     *
     * <p>Time O(n). Space O(1).
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;
        while (true) {
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) {
                break;
            }
            ListNode groupNext = kth.next;
            ListNode prev = groupNext;
            ListNode cur = groupPrev.next;
            while (cur != groupNext) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            ListNode firstOfGroup = groupPrev.next; // now the group's last node
            groupPrev.next = kth;
            groupPrev = firstOfGroup;
        }
        return dummy.next;
    }
}
