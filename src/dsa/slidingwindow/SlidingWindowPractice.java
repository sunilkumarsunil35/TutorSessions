package dsa.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** More sliding-window practice: fixed-size windows and windows with a budget. */
public final class SlidingWindowPractice {
    private SlidingWindowPractice() {}

    /**
     * LC 643 Maximum Average Subarray I (window of size k). Slide by adding the new element and
     * subtracting the one that leaves.
     *
     * <p>Time O(n). Space O(1).
     */
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int best = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            best = Math.max(best, sum);
        }
        return best / (double) k;
    }

    /**
     * LC 424 Longest Repeating Character Replacement. A window is valid when length - (count of
     * its most common letter) <= k. The window never needs to shrink by more than one, so a
     * stale maximum count is fine.
     *
     * <p>Time O(n). Space O(26).
     */
    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0;
        int left = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(right) - 'A']);
            if (right - left + 1 - maxCount > k) {
                count[s.charAt(left++) - 'A']--;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    /**
     * LC 567 Permutation in String. Slide a window of |s1| over s2 and compare letter counts.
     *
     * <p>Time O(26 * n). Space O(26).
     */
    public static boolean checkInclusion(String s1, String s2) {
        return !anagramStarts(s2, s1, true).isEmpty();
    }

    /**
     * LC 438 Find All Anagrams in a String. Same fixed window as LC 567, collecting every match.
     *
     * <p>Time O(26 * n). Space O(26).
     */
    public static List<Integer> findAnagrams(String s, String p) {
        return anagramStarts(s, p, false);
    }

    private static List<Integer> anagramStarts(String s, String p, boolean firstOnly) {
        List<Integer> out = new ArrayList<>();
        if (p.length() > s.length()) {
            return out;
        }
        int[] need = new int[26];
        int[] window = new int[26];
        for (char c : p.toCharArray()) {
            need[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            window[s.charAt(i) - 'a']++;
            if (i >= p.length()) {
                window[s.charAt(i - p.length()) - 'a']--;
            }
            if (i >= p.length() - 1 && Arrays.equals(need, window)) {
                out.add(i - p.length() + 1);
                if (firstOnly) {
                    break;
                }
            }
        }
        return out;
    }

    /**
     * LC 1004 Max Consecutive Ones III (flip at most k zeros). Shrink while the window holds more
     * than k zeros.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int longestOnes(int[] nums, int k) {
        int zeros = 0;
        int left = 0;
        int best = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (nums[left++] == 0) {
                    zeros--;
                }
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    /**
     * LC 904 Fruit Into Baskets: the longest window with at most two distinct values.
     *
     * <p>Time O(n). Space O(1) (at most three map entries).
     */
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int best = 0;
        for (int right = 0; right < fruits.length; right++) {
            count.merge(fruits[right], 1, Integer::sum);
            while (count.size() > 2) {
                if (count.merge(fruits[left], -1, Integer::sum) == 0) {
                    count.remove(fruits[left]);
                }
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
