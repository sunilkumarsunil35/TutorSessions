package dsa.sorting;

import dsa.common.ListNode;
import java.util.Arrays;

/** More sorting practice: merging, custom comparators and bucket counting. */
public final class SortingPractice {
    private SortingPractice() {}

    /**
     * LC 88 Merge Sorted Array, in place. Fill nums1 from the back so nothing unread is
     * overwritten.
     *
     * <p>Time O(m + n). Space O(1).
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (j >= 0) {
            nums1[k--] = i >= 0 && nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        }
    }

    /**
     * LC 179 Largest Number. Put a before b when a + b reads larger than b + a.
     *
     * <p>Time O(n log n * digits). Space O(n).
     */
    public static String largestNumber(int[] nums) {
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, (a, b) -> (b + a).compareTo(a + b));
        if (s[0].equals("0")) {
            return "0";
        }
        return String.join("", s);
    }

    /**
     * LC 274 H-Index. Counting sort into n + 1 buckets (citations above n count as n), then walk
     * down until at least h papers have h citations.
     *
     * <p>Time O(n). Space O(n).
     */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int c : citations) {
            bucket[Math.min(c, n)]++;
        }
        int papers = 0;
        for (int h = n; h >= 0; h--) {
            papers += bucket[h];
            if (papers >= h) {
                return h;
            }
        }
        return 0;
    }

    /**
     * LC 147 Insertion Sort List. Take nodes one by one and splice each into place in a growing
     * sorted list.
     *
     * <p>Time O(n^2). Space O(1).
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val <= cur.val) {
                prev = prev.next;
            }
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
        }
        return dummy.next;
    }
}
