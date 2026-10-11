package dsa.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/** More heap practice: simulation, k closest, scheduling and k-way merges. */
public final class HeapPractice {
    private HeapPractice() {}

    /**
     * LC 1046 Last Stone Weight. Repeatedly smash the two heaviest stones (max-heap).
     *
     * <p>Time O(n log n). Space O(n).
     */
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) {
            heap.add(s);
        }
        while (heap.size() > 1) {
            int diff = heap.poll() - heap.poll();
            if (diff > 0) {
                heap.add(diff);
            }
        }
        return heap.isEmpty() ? 0 : heap.peek();
    }

    /**
     * LC 973 K Closest Points to Origin. Keep a max-heap (by squared distance) of the k closest
     * seen; anything farther than its top is dropped.
     *
     * <p>Time O(n log k). Space O(k).
     */
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Long.compare(dist(b), dist(a)));
        for (int[] p : points) {
            heap.add(p);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.toArray(new int[0][]);
    }

    private static long dist(int[] p) {
        return (long) p[0] * p[0] + (long) p[1] * p[1];
    }

    /**
     * LC 621 Task Scheduler. The most frequent task needs (maxCount - 1) gaps of n + 1 slots,
     * plus one slot for each task tied for the maximum. With many distinct tasks no idling is
     * needed, so the answer is never below the task count.
     *
     * <p>Time O(n). Space O(26).
     */
    public static int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int maxCount = 0;
        for (char t : tasks) {
            maxCount = Math.max(maxCount, ++count[t - 'A']);
        }
        int tiedForMax = 0;
        for (int c : count) {
            if (c == maxCount) {
                tiedForMax++;
            }
        }
        return Math.max(tasks.length, (maxCount - 1) * (n + 1) + tiedForMax);
    }

    /**
     * LC 767 Reorganize String so no two neighbours are equal. Impossible when one letter fills
     * more than half (rounded up). Otherwise fill even indices first, starting with the most
     * frequent letter, then the odd indices.
     *
     * <p>Time O(n). Space O(n).
     */
    public static String reorganizeString(String s) {
        int[] count = new int[26];
        int top = 0;
        for (char c : s.toCharArray()) {
            if (++count[c - 'a'] > count[top]) {
                top = c - 'a';
            }
        }
        if (count[top] > (s.length() + 1) / 2) {
            return "";
        }
        char[] out = new char[s.length()];
        int i = 0;
        for (int letter = top, done = 0; done < 26; letter = (letter + 1) % 26, done++) {
            while (count[letter]-- > 0) {
                if (i >= out.length) {
                    i = 1;
                }
                out[i] = (char) ('a' + letter);
                i += 2;
            }
        }
        return new String(out);
    }

    /**
     * LC 378 Kth Smallest Element in a Sorted Matrix. A k-way merge of the rows: the heap holds
     * the next candidate from each row.
     *
     * <p>Time O(k log n). Space O(n).
     */
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int r = 0; r < matrix.length; r++) {
            heap.add(new int[] {matrix[r][0], r, 0});
        }
        for (int i = 1; i < k; i++) {
            int[] top = heap.poll();
            if (top[2] + 1 < matrix[0].length) {
                heap.add(new int[] {matrix[top[1]][top[2] + 1], top[1], top[2] + 1});
            }
        }
        return heap.peek()[0];
    }
}
