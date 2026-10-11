package dsa;

import dsa.slidingwindow.SlidingWindowPractice;
import java.util.List;

final class SlidingWindowPracticeTest {
    static void run(Check c) {
        c.eq("findMaxAverage", 12.75, SlidingWindowPractice.findMaxAverage(new int[] {1, 12, -5, -6, 50, 3}, 4));
        c.eq("findMaxAverage single", 5.0, SlidingWindowPractice.findMaxAverage(new int[] {5}, 1));
        c.eq("characterReplacement", 4, SlidingWindowPractice.characterReplacement("ABAB", 2));
        c.eq("characterReplacement 2", 4, SlidingWindowPractice.characterReplacement("AABABBA", 1));
        c.isTrue("checkInclusion", SlidingWindowPractice.checkInclusion("ab", "eidbaooo"));
        c.isFalse("checkInclusion no", SlidingWindowPractice.checkInclusion("ab", "eidboaoo"));
        c.isFalse("checkInclusion longer", SlidingWindowPractice.checkInclusion("abc", "ab"));
        c.eq("findAnagrams", List.of(0, 6), SlidingWindowPractice.findAnagrams("cbaebabacd", "abc"));
        c.eq("findAnagrams overlapping", List.of(0, 1, 2), SlidingWindowPractice.findAnagrams("abab", "ab"));
        c.eq("longestOnes", 6, SlidingWindowPractice.longestOnes(new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        c.eq("longestOnes no flips", 2, SlidingWindowPractice.longestOnes(new int[] {1, 1, 0, 1}, 0));
        c.eq("totalFruit", 3, SlidingWindowPractice.totalFruit(new int[] {1, 2, 1}));
        c.eq("totalFruit 2", 3, SlidingWindowPractice.totalFruit(new int[] {0, 1, 2, 2}));
        c.eq("totalFruit 3", 4, SlidingWindowPractice.totalFruit(new int[] {1, 2, 3, 2, 2}));
    }
}
