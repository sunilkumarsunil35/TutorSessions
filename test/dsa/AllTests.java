package dsa;

/** Runs every topic's tests. Exits with status 1 if anything fails, so CI goes red. */
public final class AllTests {
    public static void main(String[] args) {
        Check c = new Check();
        c.suite("sorting", () -> SortingTest.run(c));
        c.suite("arrays", () -> ArraysTest.run(c));
        c.suite("strings", () -> StringsTest.run(c));
        c.suite("hashing", () -> HashingTest.run(c));
        c.suite("two pointers", () -> TwoPointersTest.run(c));
        c.suite("sliding window", () -> SlidingWindowTest.run(c));
        c.suite("binary search", () -> BinarySearchTest.run(c));
        c.suite("stacks", () -> StacksTest.run(c));
        c.suite("linked lists", () -> LinkedListTest.run(c));
        c.suite("trees and tries", () -> TreesTest.run(c));
        c.suite("heaps", () -> HeapsTest.run(c));
        c.suite("graphs", () -> GraphsTest.run(c));
        c.suite("backtracking", () -> BacktrackingTest.run(c));
        c.suite("dynamic programming", () -> DynamicProgrammingTest.run(c));
        c.suite("greedy and intervals", () -> GreedyTest.run(c));
        c.suite("sorting practice", () -> SortingPracticeTest.run(c));
        c.suite("arrays practice", () -> ArraysPracticeTest.run(c));
        c.suite("strings practice", () -> StringsPracticeTest.run(c));
        c.suite("hashing practice", () -> HashingPracticeTest.run(c));
        c.suite("two pointers practice", () -> TwoPointersPracticeTest.run(c));
        c.suite("sliding window practice", () -> SlidingWindowPracticeTest.run(c));
        c.suite("binary search practice", () -> BinarySearchPracticeTest.run(c));
        c.suite("stacks practice", () -> StacksPracticeTest.run(c));
        c.suite("linked lists practice", () -> LinkedListPracticeTest.run(c));
        c.suite("trees and tries practice", () -> TreesPracticeTest.run(c));
        c.suite("heaps practice", () -> HeapsPracticeTest.run(c));
        c.suite("graphs practice", () -> GraphsPracticeTest.run(c));
        c.suite("backtracking practice", () -> BacktrackingPracticeTest.run(c));
        c.suite("dynamic programming practice", () -> DynamicProgrammingPracticeTest.run(c));
        c.suite("greedy and intervals practice", () -> GreedyPracticeTest.run(c));
        c.suite("math and bits", () -> BitsTest.run(c));
        System.out.println(c.summary());
        if (c.failed() > 0) {
            System.exit(1);
        }
    }
}
