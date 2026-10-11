package dsa;

import dsa.twopointers.TwoPointerPractice;
import java.util.List;

final class TwoPointersPracticeTest {
    static void run(Check c) {
        c.eq("twoSumSorted", new int[] {1, 2}, TwoPointerPractice.twoSumSorted(new int[] {2, 7, 11, 15}, 9));
        c.eq("twoSumSorted negatives", new int[] {1, 2}, TwoPointerPractice.twoSumSorted(new int[] {-1, 0}, -1));
        int[] z = {0, 1, 0, 3, 12};
        TwoPointerPractice.moveZeroes(z);
        c.eq("moveZeroes", new int[] {1, 3, 12, 0, 0}, z);
        c.eq("sortedSquares", new int[] {0, 1, 9, 16, 100}, TwoPointerPractice.sortedSquares(new int[] {-4, -1, 0, 3, 10}));
        c.eq("threeSumClosest", 2, TwoPointerPractice.threeSumClosest(new int[] {-1, 2, 1, -4}, 1));
        c.eq("threeSumClosest exact", 0, TwoPointerPractice.threeSumClosest(new int[] {0, 0, 0}, 1));
        c.eq("fourSum", List.of(List.of(-2, -1, 1, 2), List.of(-2, 0, 0, 2), List.of(-1, 0, 0, 1)),
                TwoPointerPractice.fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
        c.eq("fourSum duplicates", List.of(List.of(2, 2, 2, 2)), TwoPointerPractice.fourSum(new int[] {2, 2, 2, 2, 2}, 8));
        c.eq("fourSum overflow", List.of(),
                TwoPointerPractice.fourSum(new int[] {1000000000, 1000000000, 1000000000, 1000000000}, -294967296));
        c.isTrue("validPalindrome", TwoPointerPractice.validPalindrome("aba"));
        c.isTrue("validPalindrome one delete", TwoPointerPractice.validPalindrome("abca"));
        c.isFalse("validPalindrome no", TwoPointerPractice.validPalindrome("abc"));
        c.isTrue("validPalindrome delete right side", TwoPointerPractice.validPalindrome("cbbcc"));
    }
}
