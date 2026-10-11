package dsa.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * LC 380 Insert Delete GetRandom O(1). Values live in a list (for random access) and a map holds
 * each value's index. To remove, move the last value into the removed slot.
 *
 * <p>All operations O(1) on average.
 */
public class RandomizedSet {
    private final List<Integer> values = new ArrayList<>();
    private final Map<Integer, Integer> index = new HashMap<>();
    private final Random random = new Random();

    public boolean insert(int val) {
        if (index.containsKey(val)) {
            return false;
        }
        index.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        Integer i = index.remove(val);
        if (i == null) {
            return false;
        }
        int last = values.remove(values.size() - 1);
        if (i < values.size()) {
            values.set(i, last);
            index.put(last, i);
        }
        return true;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
