package dsa;

import dsa.binarysearch.BinarySearchPractice;
import dsa.binarysearch.TimeMap;

final class BinarySearchPracticeTest {
    static void run(Check c) {
        int[] a = {1, 3, 5, 6};
        c.eq("searchInsert found", 2, BinarySearchPractice.searchInsert(a, 5));
        c.eq("searchInsert middle", 1, BinarySearchPractice.searchInsert(a, 2));
        c.eq("searchInsert end", 4, BinarySearchPractice.searchInsert(a, 7));
        c.eq("mySqrt 8", 2, BinarySearchPractice.mySqrt(8));
        c.eq("mySqrt 4", 2, BinarySearchPractice.mySqrt(4));
        c.eq("mySqrt 0", 0, BinarySearchPractice.mySqrt(0));
        c.eq("mySqrt max int", 46340, BinarySearchPractice.mySqrt(Integer.MAX_VALUE));
        int[][] m = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        c.isTrue("searchMatrix", BinarySearchPractice.searchMatrix(m, 3));
        c.isFalse("searchMatrix missing", BinarySearchPractice.searchMatrix(m, 13));
        c.eq("findMin", 1, BinarySearchPractice.findMin(new int[] {3, 4, 5, 1, 2}));
        c.eq("findMin 2", 0, BinarySearchPractice.findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
        c.eq("findMin not rotated", 11, BinarySearchPractice.findMin(new int[] {11, 13, 15, 17}));
        c.eq("findPeakElement", 2, BinarySearchPractice.findPeakElement(new int[] {1, 2, 3, 1}));
        int[] peaks = {1, 2, 1, 3, 5, 6, 4};
        int p = BinarySearchPractice.findPeakElement(peaks);
        c.isTrue("findPeakElement is a peak", p == 1 || p == 5);
        c.eq("shipWithinDays", 15, BinarySearchPractice.shipWithinDays(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        c.eq("shipWithinDays 2", 6, BinarySearchPractice.shipWithinDays(new int[] {3, 2, 2, 4, 1, 4}, 3));
        c.eq("shipWithinDays one day", 6, BinarySearchPractice.shipWithinDays(new int[] {1, 2, 3}, 1));
        c.eq("median odd", 2.0, BinarySearchPractice.findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        c.eq("median even", 2.5, BinarySearchPractice.findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
        c.eq("median one empty", 1.0, BinarySearchPractice.findMedianSortedArrays(new int[] {}, new int[] {1}));
        c.eq("median interleaved", 4.5,
                BinarySearchPractice.findMedianSortedArrays(new int[] {1, 3, 5, 7}, new int[] {2, 4, 6, 8}));

        TimeMap tm = new TimeMap();
        tm.set("foo", "bar", 1);
        c.eq("TimeMap exact", "bar", tm.get("foo", 1));
        c.eq("TimeMap later", "bar", tm.get("foo", 3));
        tm.set("foo", "bar2", 4);
        c.eq("TimeMap newer", "bar2", tm.get("foo", 4));
        c.eq("TimeMap newest", "bar2", tm.get("foo", 5));
        c.eq("TimeMap too early", "", tm.get("foo", 0));
        c.eq("TimeMap missing key", "", tm.get("baz", 9));
    }
}
