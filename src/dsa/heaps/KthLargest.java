package dsa.heaps;

import java.util.PriorityQueue;

/**
 * LC 703 Kth Largest Element in a Stream. A min-heap of the k largest values; its top is the
 * kth largest.
 *
 * <p>add O(log k).
 */
public class KthLargest {
    private final int k;
    private final PriorityQueue<Integer> heap = new PriorityQueue<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        heap.add(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    }
}
