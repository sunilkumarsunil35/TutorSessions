package dsa;

import dsa.arrays.ArrayPractice;
import java.util.List;

final class ArraysPracticeTest {
    static void run(Check c) {
        c.eq("majorityElement", 2, ArrayPractice.majorityElement(new int[] {2, 2, 1, 1, 1, 2, 2}));
        int[][] img = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayPractice.rotate(img);
        c.eq("rotate image", new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}, img);
        c.eq("spiralOrder", List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                ArrayPractice.spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        c.eq("spiralOrder column", List.of(1, 2, 3), ArrayPractice.spiralOrder(new int[][] {{1}, {2}, {3}}));
        int[][] z = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        ArrayPractice.setZeroes(z);
        c.eq("setZeroes", new int[][] {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}}, z);
        int[][] z2 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        ArrayPractice.setZeroes(z2);
        c.eq("setZeroes centre", new int[][] {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, z2);
        int[][][] perms = {{{1, 2, 3}, {1, 3, 2}}, {{3, 2, 1}, {1, 2, 3}}, {{1, 1, 5}, {1, 5, 1}}, {{1, 3, 2}, {2, 1, 3}}};
        for (int[][] p : perms) {
            int[] a = p[0].clone();
            ArrayPractice.nextPermutation(a);
            c.eq("nextPermutation", p[1], a);
        }
        c.eq("firstMissingPositive", 2, ArrayPractice.firstMissingPositive(new int[] {3, 4, -1, 1}));
        c.eq("firstMissingPositive none small", 1, ArrayPractice.firstMissingPositive(new int[] {7, 8, 9, 11, 12}));
        c.eq("firstMissingPositive all present", 3, ArrayPractice.firstMissingPositive(new int[] {1, 2, 0}));
        c.eq("firstMissingPositive duplicates", 2, ArrayPractice.firstMissingPositive(new int[] {1, 1}));
    }
}
