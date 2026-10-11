package dsa.binarysearch;

/** More binary search: insertion points, rotated arrays, peaks and searching on the answer. */
public final class BinarySearchPractice {
    private BinarySearchPractice() {}

    /**
     * LC 35 Search Insert Position: the first index whose value is >= target.
     *
     * <p>Time O(log n). Space O(1).
     */
    public static int searchInsert(int[] nums, int target) {
        return BinarySearchProblems.lowerBound(nums, target);
    }

    /**
     * LC 69 Sqrt(x), rounded down. Find the largest m with m * m <= x (in long to avoid
     * overflow).
     *
     * <p>Time O(log x). Space O(1).
     */
    public static int mySqrt(int x) {
        long lo = 0;
        long hi = x;
        while (lo < hi) {
            long mid = (lo + hi + 1) >>> 1; // round up so lo = mid always makes progress
            if (mid * mid <= x) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return (int) lo;
    }

    /**
     * LC 74 Search a 2D Matrix (each row sorted, each row starts after the previous ends). Treat
     * it as one sorted array of rows * cols cells.
     *
     * <p>Time O(log(rows * cols)). Space O(1).
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int cols = matrix[0].length;
        int lo = 0;
        int hi = matrix.length * cols - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int v = matrix[mid / cols][mid % cols];
            if (v == target) {
                return true;
            } else if (v < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }

    /**
     * LC 153 Find Minimum in Rotated Sorted Array. If mid is bigger than the last element, the
     * minimum is to the right of mid; otherwise it is at mid or to its left.
     *
     * <p>Time O(log n). Space O(1).
     */
    public static int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    /**
     * LC 162 Find Peak Element. Walking uphill always reaches a peak, so move towards the larger
     * neighbour.
     *
     * <p>Time O(log n). Space O(1).
     */
    public static int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * LC 1011 Capacity To Ship Packages Within D Days. Binary search the capacity between the
     * heaviest package and the total weight; check each guess greedily.
     *
     * <p>Time O(n log(sum)). Space O(1).
     */
    public static int shipWithinDays(int[] weights, int days) {
        int lo = 0;
        int hi = 0;
        for (int w : weights) {
            lo = Math.max(lo, w);
            hi += w;
        }
        while (lo < hi) {
            int cap = (lo + hi) >>> 1;
            int needed = 1;
            int load = 0;
            for (int w : weights) {
                if (load + w > cap) {
                    needed++;
                    load = 0;
                }
                load += w;
            }
            if (needed <= days) {
                hi = cap;
            } else {
                lo = cap + 1;
            }
        }
        return lo;
    }

    /**
     * LC 4 Median of Two Sorted Arrays. Binary search a cut in the shorter array so the left
     * halves of both arrays together hold half the elements and every left value is <= every
     * right value.
     *
     * <p>Time O(log min(m, n)). Space O(1).
     */
    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        int m = a.length;
        int n = b.length;
        int lo = 0;
        int hi = m;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int j = (m + n + 1) / 2 - i;
            int aLeft = i == 0 ? Integer.MIN_VALUE : a[i - 1];
            int aRight = i == m ? Integer.MAX_VALUE : a[i];
            int bLeft = j == 0 ? Integer.MIN_VALUE : b[j - 1];
            int bRight = j == n ? Integer.MAX_VALUE : b[j];
            if (aLeft <= bRight && bLeft <= aRight) {
                if ((m + n) % 2 == 1) {
                    return Math.max(aLeft, bLeft);
                }
                return (Math.max(aLeft, bLeft) + (double) Math.min(aRight, bRight)) / 2;
            } else if (aLeft > bRight) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        throw new IllegalArgumentException("inputs must be sorted");
    }
}
