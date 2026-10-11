package dsa.stacks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** More stack practice: next greater element, nested decoding and expression parsing. */
public final class StackPractice {
    private StackPractice() {}

    /**
     * LC 496 Next Greater Element I. One monotonic-stack pass over nums2 records each value's
     * next greater value; then look up nums1.
     *
     * <p>Time O(n + m). Space O(n).
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> next = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int x : nums2) {
            while (!stack.isEmpty() && stack.peek() < x) {
                next.put(stack.pop(), x);
            }
            stack.push(x);
        }
        int[] out = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            out[i] = next.getOrDefault(nums1[i], -1);
        }
        return out;
    }

    /**
     * LC 503 Next Greater Element II (circular array). Loop over the array twice so each element
     * can see the ones before it.
     *
     * <p>Time O(n). Space O(n).
     */
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int x = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < x) {
                out[stack.pop()] = x;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return out;
    }

    /**
     * LC 853 Car Fleet. Process cars from closest to the target; a car that would arrive no
     * later than the fleet ahead joins it, otherwise it starts a new fleet.
     *
     * <p>Time O(n log n). Space O(n).
     */
    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(position[b], position[a]));
        int fleets = 0;
        double slowest = 0;
        for (int i : order) {
            double time = (double) (target - position[i]) / speed[i];
            if (time > slowest) {
                fleets++;
                slowest = time;
            }
        }
        return fleets;
    }

    /**
     * LC 394 Decode String, e.g. "3[a2[c]]" -> "accaccacc". On '[' save the current text and
     * repeat count; on ']' repeat the inner text and append it to the saved text.
     *
     * <p>Time O(output length). Space O(output length).
     */
    public static String decodeString(String s) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> saved = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                counts.push(k);
                saved.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                StringBuilder outer = saved.pop();
                outer.append(cur.toString().repeat(counts.pop()));
                cur = outer;
            } else {
                cur.append(c);
            }
        }
        return cur.toString();
    }

    /**
     * LC 71 Simplify Path. Split on '/', skip "" and ".", pop on "..", push anything else.
     *
     * <p>Time O(n). Space O(n).
     */
    public static String simplifyPath(String path) {
        List<String> stack = new ArrayList<>();
        for (String part : path.split("/")) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.remove(stack.size() - 1);
                }
            } else {
                stack.add(part);
            }
        }
        return "/" + String.join("/", stack);
    }

    /**
     * LC 227 Basic Calculator II (+ - * / and spaces, no brackets). Push signed terms; * and /
     * apply immediately to the previous term, so the stack sum is the answer.
     *
     * <p>Time O(n). Space O(n).
     */
    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (op) {
                    case '+' -> stack.push(num);
                    case '-' -> stack.push(-num);
                    case '*' -> stack.push(stack.pop() * num);
                    default -> stack.push(stack.pop() / num);
                }
                op = c;
                num = 0;
            }
        }
        int sum = 0;
        for (int x : stack) {
            sum += x;
        }
        return sum;
    }
}
