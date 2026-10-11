package dsa;

import static dsa.common.ListNode.of;
import static dsa.common.ListNode.toList;

import dsa.common.ListNode;
import dsa.linkedlist.LRUCache;
import dsa.linkedlist.LinkedListPractice;
import dsa.linkedlist.LinkedListPractice.RandomNode;
import java.util.List;

final class LinkedListPracticeTest {
    static void run(Check c) {
        ListNode common = of(8, 4, 5);
        ListNode a = of(4, 1);
        a.next.next = common;
        ListNode b = of(5, 6, 1);
        b.next.next.next = common;
        c.isTrue("getIntersectionNode", LinkedListPractice.getIntersectionNode(a, b) == common);
        c.isTrue("getIntersectionNode none", LinkedListPractice.getIntersectionNode(of(2, 6, 4), of(1, 5)) == null);
        c.isTrue("isPalindrome list", LinkedListPractice.isPalindrome(of(1, 2, 2, 1)));
        c.isTrue("isPalindrome odd", LinkedListPractice.isPalindrome(of(1, 2, 3, 2, 1)));
        c.isFalse("isPalindrome no", LinkedListPractice.isPalindrome(of(1, 2)));

        ListNode cyclic = of(3, 2, 0, -4);
        cyclic.next.next.next.next = cyclic.next;
        c.isTrue("detectCycle start", LinkedListPractice.detectCycle(cyclic) == cyclic.next);
        ListNode self = of(1);
        self.next = self;
        c.isTrue("detectCycle self loop", LinkedListPractice.detectCycle(self) == self);
        c.isTrue("detectCycle none", LinkedListPractice.detectCycle(of(1, 2)) == null);

        c.eq("addTwoNumbers", List.of(7, 0, 8), toList(LinkedListPractice.addTwoNumbers(of(2, 4, 3), of(5, 6, 4))));
        c.eq("addTwoNumbers carry", List.of(8, 9, 9, 9, 0, 0, 0, 1),
                toList(LinkedListPractice.addTwoNumbers(of(9, 9, 9, 9, 9, 9, 9), of(9, 9, 9, 9))));

        ListNode even = of(1, 2, 3, 4);
        LinkedListPractice.reorderList(even);
        c.eq("reorderList even", List.of(1, 4, 2, 3), toList(even));
        ListNode odd = of(1, 2, 3, 4, 5);
        LinkedListPractice.reorderList(odd);
        c.eq("reorderList odd", List.of(1, 5, 2, 4, 3), toList(odd));

        // [[7,null],[13,0],[11,4],[10,2],[1,0]]
        int[] vals = {7, 13, 11, 10, 1};
        Integer[] randoms = {null, 0, 4, 2, 0};
        RandomNode[] nodes = new RandomNode[5];
        for (int i = 0; i < 5; i++) {
            nodes[i] = new RandomNode(vals[i]);
        }
        for (int i = 0; i < 5; i++) {
            nodes[i].next = i < 4 ? nodes[i + 1] : null;
            nodes[i].random = randoms[i] == null ? null : nodes[randoms[i]];
        }
        RandomNode copy = LinkedListPractice.copyRandomList(nodes[0]);
        RandomNode[] copies = new RandomNode[5];
        RandomNode cur = copy;
        for (int i = 0; i < 5; i++, cur = cur.next) {
            copies[i] = cur;
            c.isTrue("copyRandomList new node", cur != nodes[i]);
            c.eq("copyRandomList value", vals[i], cur.val);
        }
        c.isTrue("copyRandomList length", cur == null);
        for (int i = 0; i < 5; i++) {
            c.isTrue("copyRandomList random", copies[i].random == (randoms[i] == null ? null : copies[randoms[i]]));
            c.isTrue("copyRandomList original restored", nodes[i].next == (i < 4 ? nodes[i + 1] : null));
        }

        c.eq("findDuplicate", 2, LinkedListPractice.findDuplicate(new int[] {1, 3, 4, 2, 2}));
        c.eq("findDuplicate 2", 3, LinkedListPractice.findDuplicate(new int[] {3, 1, 3, 4, 2}));
        c.eq("findDuplicate many copies", 3, LinkedListPractice.findDuplicate(new int[] {3, 3, 3, 3, 3}));
        c.eq("reverseKGroup 2", List.of(2, 1, 4, 3, 5), toList(LinkedListPractice.reverseKGroup(of(1, 2, 3, 4, 5), 2)));
        c.eq("reverseKGroup 3", List.of(3, 2, 1, 4, 5), toList(LinkedListPractice.reverseKGroup(of(1, 2, 3, 4, 5), 3)));
        c.eq("reverseKGroup exact", List.of(2, 1, 4, 3), toList(LinkedListPractice.reverseKGroup(of(1, 2, 3, 4), 2)));

        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        c.eq("LRU get", 1, lru.get(1));
        lru.put(3, 3); // evicts 2
        c.eq("LRU evicted", -1, lru.get(2));
        lru.put(4, 4); // evicts 1
        c.eq("LRU evicted 2", -1, lru.get(1));
        c.eq("LRU kept 3", 3, lru.get(3));
        c.eq("LRU kept 4", 4, lru.get(4));
        lru.put(3, 30); // update refreshes 3
        lru.put(5, 5); // evicts 4
        c.eq("LRU update value", 30, lru.get(3));
        c.eq("LRU update refreshes", -1, lru.get(4));
    }
}
