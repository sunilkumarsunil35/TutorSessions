package dsa.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC 232 Implement Queue using Stacks. Push onto an inbox stack; pop from an outbox stack,
 * refilling it from the inbox (which reverses the order) only when it is empty.
 *
 * <p>Each operation O(1) amortised.
 */
public class MyQueue {
    private final Deque<Integer> in = new ArrayDeque<>();
    private final Deque<Integer> out = new ArrayDeque<>();

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        peek();
        return out.pop();
    }

    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
