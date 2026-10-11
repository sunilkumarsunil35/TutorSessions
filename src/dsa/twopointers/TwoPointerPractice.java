package dsa.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** More two-pointer practice on sorted arrays and strings. */
public final class TwoPointerPractice {
    private TwoPointerPractice() {}

    /**
     * LC 167 Two Sum II (sorted input, 1-indexed answer). Move the left pointer up if the sum is
     * too small, the right one down if too big.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int[] twoSumSorted(int[] numbers, int target) {
        int lo = 0;
        int hi = numbers.length - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum == target) {
                return new int[] {lo + 1, hi + 1};
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * LC 283 Move Zeroes, keeping the order of the other numbers. Copy non-zeros forward, then
     * zero-fill the tail.
     *
     * <p>Time O(n). Space O(1).
     */
    public static void moveZeroes(int[] nums) {
        int write = 0;
        for (int x : nums) {
            if (x != 0) {
                nums[write++] = x;
            }
        }
        while (write < nums.length) {
            nums[write++] = 0;
        }
    }

    /**
     * LC 977 Squares of a Sorted Array. The largest square is at one of the two ends, so fill the
     * output from the back.
     *
     * <p>Time O(n). Space O(1) besides the output.
     */
    public static int[] sortedSquares(int[] nums) {
        int[] out = new int[nums.length];
        int lo = 0;
        int hi = nums.length - 1;
        for (int k = nums.length - 1; k >= 0; k--) {
            if (Math.abs(nums[lo]) > Math.abs(nums[hi])) {
                out[k] = nums[lo] * nums[lo++];
            } else {
                out[k] = nums[hi] * nums[hi--];
            }
        }
        return out;
    }

    /**
     * LC 16 3Sum Closest. Like 3Sum, but keep the sum nearest the target.
     *
     * <p>Time O(n^2). Space O(1) besides sorting.
     */
    public static int threeSumClosest(int[] nums, int target) {
        int[] a = nums.clone();
        Arrays.sort(a);
        int best = a[0] + a[1] + a[2];
        for (int i = 0; i < a.length - 2; i++) {
            int lo = i + 1;
            int hi = a.length - 1;
            while (lo < hi) {
                int sum = a[i] + a[lo] + a[hi];
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum < target) {
                    lo++;
                } else if (sum > target) {
                    hi--;
                } else {
                    return sum;
                }
            }
        }
        return best;
    }

    /**
     * LC 18 4Sum. Two nested loops plus a two-pointer scan; skip equal values to avoid duplicate
     * quadruplets and add in long to avoid overflow.
     *
     * <p>Time O(n^3). Space O(1) besides sorting and the output.
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) {
                    continue;
                }
                int lo = j + 1;
                int hi = n - 1;
                while (lo < hi) {
                    long sum = (long) a[i] + a[j] + a[lo] + a[hi];
                    if (sum < target) {
                        lo++;
                    } else if (sum > target) {
                        hi--;
                    } else {
                        out.add(List.of(a[i], a[j], a[lo], a[hi]));
                        while (lo < hi && a[lo] == a[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && a[hi] == a[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    }
                }
            }
        }
        return out;
    }

    /**
     * LC 680 Valid Palindrome II (delete at most one character). On the first mismatch, try
     * skipping either side.
     *
     * <p>Time O(n). Space O(1).
     */
    public static boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
