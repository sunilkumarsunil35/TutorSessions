package dsa.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 146 LRU Cache. A hash map finds nodes in O(1); a doubly linked list keeps them in use order
 * (most recent next to head), so the least recently used node is always just before tail.
 *
 * <p>get and put O(1).
 */
public class LRUCache {
    private static final class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(); // sentinels: no null checks at the ends
    private final Node tail = new Node();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        unlink(node);
        addFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            unlink(node);
            addFront(node);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;
            unlink(lru);
            map.remove(lru.key);
        }
        node = new Node();
        node.key = key;
        node.value = value;
        map.put(key, node);
        addFront(node);
    }

    private void unlink(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addFront(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }
}
