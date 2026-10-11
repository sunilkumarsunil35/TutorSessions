package dsa;

import dsa.stacks.MyQueue;
import dsa.stacks.StackPractice;

final class StacksPracticeTest {
    static void run(Check c) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        c.eq("MyQueue peek", 1, q.peek());
        c.eq("MyQueue pop", 1, q.pop());
        q.push(3);
        c.eq("MyQueue pop after push", 2, q.pop());
        c.isFalse("MyQueue not empty", q.empty());
        c.eq("MyQueue last", 3, q.pop());
        c.isTrue("MyQueue empty", q.empty());

        c.eq("nextGreaterElement", new int[] {-1, 3, -1},
                StackPractice.nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2}));
        c.eq("nextGreaterElements circular", new int[] {2, -1, 2}, StackPractice.nextGreaterElements(new int[] {1, 2, 1}));
        c.eq("nextGreaterElements 2", new int[] {2, 3, 4, -1, 4}, StackPractice.nextGreaterElements(new int[] {1, 2, 3, 4, 3}));
        c.eq("carFleet", 3, StackPractice.carFleet(12, new int[] {10, 8, 0, 5, 3}, new int[] {2, 4, 1, 1, 3}));
        c.eq("carFleet one car", 1, StackPractice.carFleet(10, new int[] {3}, new int[] {3}));
        c.eq("carFleet all merge", 1, StackPractice.carFleet(100, new int[] {0, 2, 4}, new int[] {4, 2, 1}));
        c.eq("decodeString", "aaabcbc", StackPractice.decodeString("3[a]2[bc]"));
        c.eq("decodeString nested", "accaccacc", StackPractice.decodeString("3[a2[c]]"));
        c.eq("decodeString tail", "abcabccdcdcdef", StackPractice.decodeString("2[abc]3[cd]ef"));
        c.eq("decodeString two digits", "aaaaaaaaaa", StackPractice.decodeString("10[a]"));
        c.eq("simplifyPath", "/home", StackPractice.simplifyPath("/home/"));
        c.eq("simplifyPath double slash", "/home/foo", StackPractice.simplifyPath("/home//foo/"));
        c.eq("simplifyPath above root", "/", StackPractice.simplifyPath("/../"));
        c.eq("simplifyPath dots", "/c", StackPractice.simplifyPath("/a/./b/../../c/"));
        c.eq("simplifyPath three dots is a name", "/.../b/d", StackPractice.simplifyPath("/.../a/../b/c/../d/./"));
        c.eq("calculate", 7, StackPractice.calculate("3+2*2"));
        c.eq("calculate division", 1, StackPractice.calculate(" 3/2 "));
        c.eq("calculate spaces", 5, StackPractice.calculate(" 3+5 / 2 "));
        c.eq("calculate minus", 13, StackPractice.calculate("14-3/2"));
        c.eq("calculate single", 42, StackPractice.calculate("42"));
    }
}
