package dsa.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LC 981 Time Based Key-Value Store. Timestamps arrive in increasing order per key, so each key's
 * history is already sorted; get binary searches for the last timestamp <= the one asked for.
 *
 * <p>set O(1). get O(log n).
 */
public class TimeMap {
    private final Map<String, List<Integer>> times = new HashMap<>();
    private final Map<String, List<String>> values = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        times.computeIfAbsent(key, k -> new ArrayList<>()).add(timestamp);
        values.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public String get(String key, int timestamp) {
        List<Integer> ts = times.get(key);
        if (ts == null) {
            return "";
        }
        int lo = 0;
        int hi = ts.size(); // first index with time > timestamp
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (ts.get(mid) <= timestamp) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == 0 ? "" : values.get(key).get(lo - 1);
    }
}
