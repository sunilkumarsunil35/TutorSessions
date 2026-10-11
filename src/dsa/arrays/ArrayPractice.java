package dsa.arrays;

import java.util.ArrayList;
import java.util.List;

/** More array practice: voting, matrices and in-place index tricks. */
public final class ArrayPractice {
    private ArrayPractice() {}

    /**
     * LC 169 Majority Element. Boyer-Moore voting: the majority survives pairwise cancelling.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int x : nums) {
            if (count == 0) {
                candidate = x;
            }
            count += x == candidate ? 1 : -1;
        }
        return candidate;
    }

    /**
     * LC 48 Rotate Image 90 degrees clockwise, in place. Transpose, then reverse each row.
     *
     * <p>Time O(n^2). Space O(1).
     */
    public static void rotate(int[][] m) {
        int n = m.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int t = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = t;
            }
        }
        for (int[] row : m) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                int t = row[l];
                row[l] = row[r];
                row[r] = t;
            }
        }
    }

    /**
     * LC 54 Spiral Matrix. Walk the outer ring, then shrink the four boundaries inwards.
     *
     * <p>Time O(rows * cols). Space O(1) besides the output.
     */
    public static List<Integer> spiralOrder(int[][] m) {
        List<Integer> out = new ArrayList<>();
        int top = 0;
        int bottom = m.length - 1;
        int left = 0;
        int right = m[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) {
                out.add(m[top][j]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                out.add(m[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    out.add(m[bottom][j]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    out.add(m[i][left]);
                }
                left++;
            }
        }
        return out;
    }

    /**
     * LC 73 Set Matrix Zeroes in O(1) extra space. Use the first row and column as markers, and
     * remember separately whether they themselves held a zero.
     *
     * <p>Time O(rows * cols). Space O(1).
     */
    public static void setZeroes(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;
        boolean firstRow = false;
        boolean firstCol = false;
        for (int j = 0; j < cols; j++) {
            firstRow |= m[0][j] == 0;
        }
        for (int i = 0; i < rows; i++) {
            firstCol |= m[i][0] == 0;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (m[i][j] == 0) {
                    m[i][0] = 0;
                    m[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (m[i][0] == 0 || m[0][j] == 0) {
                    m[i][j] = 0;
                }
            }
        }
        if (firstRow) {
            for (int j = 0; j < cols; j++) {
                m[0][j] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < rows; i++) {
                m[i][0] = 0;
            }
        }
    }

    /**
     * LC 31 Next Permutation. Find the rightmost i with a[i] < a[i + 1], swap a[i] with the
     * smallest larger value to its right, then reverse the suffix (which was descending).
     *
     * <p>Time O(n). Space O(1).
     */
    public static void nextPermutation(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = a.length - 1;
            while (a[j] <= a[i]) {
                j--;
            }
            swap(a, i, j);
        }
        for (int l = i + 1, r = a.length - 1; l < r; l++, r--) {
            swap(a, l, r);
        }
    }

    /**
     * LC 41 First Missing Positive. Cyclic placement: put each value v in [1, n] at index v - 1;
     * the first index that doesn't hold i + 1 gives the answer.
     *
     * <p>Time O(n) (each swap fixes one value). Space O(1).
     */
    public static int firstMissingPositive(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            while (a[i] > 0 && a[i] <= n && a[a[i] - 1] != a[i]) {
                swap(a, i, a[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
