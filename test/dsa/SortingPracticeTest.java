package dsa;

import static dsa.common.ListNode.of;
import static dsa.common.ListNode.toList;

import dsa.sorting.SortingPractice;
import java.util.List;

final class SortingPracticeTest {
    static void run(Check c) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        SortingPractice.merge(nums1, 3, new int[] {2, 5, 6}, 3);
        c.eq("merge", new int[] {1, 2, 2, 3, 5, 6}, nums1);
        int[] empty1 = {0};
        SortingPractice.merge(empty1, 0, new int[] {1}, 1);
        c.eq("merge into empty", new int[] {1}, empty1);
        c.eq("largestNumber", "9534330", SortingPractice.largestNumber(new int[] {3, 30, 34, 5, 9}));
        c.eq("largestNumber zeros", "0", SortingPractice.largestNumber(new int[] {0, 0}));
        c.eq("hIndex", 3, SortingPractice.hIndex(new int[] {3, 0, 6, 1, 5}));
        c.eq("hIndex 2", 1, SortingPractice.hIndex(new int[] {1, 3, 1}));
        c.eq("insertionSortList", List.of(-1, 0, 3, 4, 5), toList(SortingPractice.insertionSortList(of(-1, 5, 3, 4, 0))));
    }
}
