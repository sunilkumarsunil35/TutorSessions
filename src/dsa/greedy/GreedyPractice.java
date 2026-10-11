package dsa.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/** More greedy and interval practice. */
public final class GreedyPractice {
    private GreedyPractice() {}

    /**
     * LC 252 Meeting Rooms: can one person attend every meeting? Sort by start; no meeting may
     * start before the previous one ends.
     *
     * <p>Time O(n log n). Space O(n).
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        int[][] a = intervals.clone();
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        for (int i = 1; i < a.length; i++) {
            if (a[i][0] < a[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * LC 253 Meeting Rooms II: fewest rooms needed. Sweep sorted starts and ends; a meeting can
     * reuse a room whenever the earliest end is no later than its start.
     *
     * <p>Time O(n log n). Space O(n).
     */
    public static int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        for (int s = 0, e = 0; s < n; s++) {
            if (starts[s] < ends[e]) {
                rooms++;
            } else {
                e++;
            }
        }
        return rooms;
    }

    /**
     * LC 134 Gas Station. If total gas >= total cost there is an answer; whenever the running
     * tank goes negative, no station up to here can be the start, so try the next one.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    /**
     * LC 846 Hand of Straights. The smallest remaining card must start a group, so repeatedly
     * build a run upwards from it.
     *
     * <p>Time O(n log n). Space O(n).
     */
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int card : hand) {
            count.merge(card, 1, Integer::sum);
        }
        while (!count.isEmpty()) {
            int first = count.firstKey();
            for (int card = first; card < first + groupSize; card++) {
                Integer c = count.get(card);
                if (c == null) {
                    return false;
                }
                if (c == 1) {
                    count.remove(card);
                } else {
                    count.put(card, c - 1);
                }
            }
        }
        return true;
    }

    /**
     * LC 763 Partition Labels. A part can end once it reaches the last occurrence of every letter
     * inside it.
     *
     * <p>Time O(n). Space O(26).
     */
    public static List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> sizes = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                sizes.add(end - start + 1);
                start = i + 1;
            }
        }
        return sizes;
    }

    /**
     * LC 678 Valid Parenthesis String ('*' is '(', ')' or empty). Track the range of possible
     * open counts; fail if even the maximum goes negative.
     *
     * <p>Time O(n). Space O(1).
     */
    public static boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else {
                low--;
                high++;
            }
            if (high < 0) {
                return false;
            }
            low = Math.max(low, 0);
        }
        return low == 0;
    }

    /**
     * LC 1899 Merge Triplets to Form Target Triplet. Only triplets with no value above the
     * target's can be used; check that those cover each target value.
     *
     * <p>Time O(n). Space O(1).
     */
    public static boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean[] found = new boolean[3];
        for (int[] t : triplets) {
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                found[i] |= t[i] == target[i];
            }
        }
        return found[0] && found[1] && found[2];
    }

    /**
     * LC 57 Insert Interval into sorted, non-overlapping intervals. Copy those ending before it,
     * merge those overlapping it, copy the rest.
     *
     * <p>Time O(n). Space O(n).
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> out = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        while (i < n && intervals[i][1] < newInterval[0]) {
            out.add(intervals[i++]);
        }
        int start = newInterval[0];
        int end = newInterval[1];
        while (i < n && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        out.add(new int[] {start, end});
        while (i < n) {
            out.add(intervals[i++]);
        }
        return out.toArray(new int[0][]);
    }

    /**
     * LC 452 Minimum Number of Arrows to Burst Balloons. Sort by end and shoot at the end of the
     * first unburst balloon; it hits every balloon starting by then.
     *
     * <p>Time O(n log n). Space O(n).
     */
    public static int findMinArrowShots(int[][] points) {
        int[][] a = points.clone();
        Arrays.sort(a, (x, y) -> Integer.compare(x[1], y[1])); // not x - y: that overflows
        int arrows = 0;
        long arrowAt = Long.MIN_VALUE;
        for (int[] p : a) {
            if (p[0] > arrowAt) {
                arrows++;
                arrowAt = p[1];
            }
        }
        return arrows;
    }

    /**
     * LC 135 Candy. Two passes: left to right gives each child one more than a lower-rated left
     * neighbour, right to left fixes the right neighbours.
     *
     * <p>Time O(n). Space O(n).
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int total = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            total += candies[i];
        }
        return total;
    }

    /**
     * LC 1851 Minimum Interval to Include Each Query. Answer queries in increasing order: add
     * every interval that has started, drop those that have ended, and the heap top (smallest
     * size) is the answer.
     *
     * <p>Time O((n + q) log n + q log q). Space O(n + q).
     */
    public static int[] minInterval(int[][] intervals, int[] queries) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (x, y) -> Integer.compare(x[0], y[0]));
        Integer[] order = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (x, y) -> Integer.compare(queries[x], queries[y]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0])); // {size, end}
        int[] out = new int[queries.length];
        int next = 0;
        for (int qi : order) {
            int q = queries[qi];
            while (next < sorted.length && sorted[next][0] <= q) {
                heap.add(new int[] {sorted[next][1] - sorted[next][0] + 1, sorted[next][1]});
                next++;
            }
            while (!heap.isEmpty() && heap.peek()[1] < q) {
                heap.poll();
            }
            out[qi] = heap.isEmpty() ? -1 : heap.peek()[0];
        }
        return out;
    }
}
