# TutorSessions: DSA Preparation (Java)

A study plan and solved, tested classic problems for coding interviews. There are 16 topics and
214 problems and data structures: 69 core problems plus 145 practice problems. Every solution has a comment that explains the idea and its
time and space complexity. The tests need only the JDK.

## Run the tests

You need JDK 17 or newer.

```bash
sh run-tests.sh
```

This compiles everything with plain `javac` into `out/` and prints one line per topic:

```
ok   sorting
ok   arrays
...
588 passed, 0 failed
```

CI runs the same script on every push (`.github/workflows/ci.yml`).

## Layout

```
TutorSessions/
├── run-tests.sh                 compile + run everything
├── src/dsa/
│   ├── common/                  ListNode, TreeNode helpers
│   ├── sorting/ arrays/ strings/ hashing/ twopointers/ slidingwindow/
│   ├── binarysearch/ stacks/ linkedlist/ trees/ heaps/ graphs/
│   ├── backtracking/ dp/ greedy/ bits/
│   └── each topic: <Topic>Problems.java (core) + <Topic>Practice.java (practice)
└── test/dsa/                    one <Topic>Test.java per topic, plus AllTests and Check
```

**Practice tip:** pick a problem from the tables below and solve it on LeetCode yourself before
you open the solution here. Then compare: is yours simpler, or does it miss an edge case that a
test here covers?

## 8-week roadmap

Spend about 1–2 hours a day. Each week, re-solve two problems from earlier weeks without looking.

| Week | Topics                                                   | Goal                                                    |
| ---- | -------------------------------------------------------- | ------------------------------------------------------- |
| 1    | Big-O, arrays, strings, sorting                          | Analyse any loop's complexity; write merge/quick sort   |
| 2    | Hashing, two pointers                                    | Reach for a HashMap or a sorted scan by reflex          |
| 3    | Sliding window, binary search                            | Spot "contiguous subarray" and "monotonic answer" cues  |
| 4    | Stacks, linked lists                                     | Monotonic stack; pointer rewiring without bugs          |
| 5    | Trees, BST, tries                                        | Recursive DFS and level-order BFS from memory           |
| 6    | Heaps, graphs (BFS/DFS, topo sort, Dijkstra, union-find) | Model a problem as a graph                              |
| 7    | Backtracking, greedy, intervals, bits                    | Choose/explore/un-choose template; sort intervals first |
| 8    | Dynamic programming, then mixed mock tests               | State + recurrence + base case; 45-minute timed sets    |

## Problems by topic

LC = LeetCode problem number.

| Topic                | Problems (LC #)                                                                                                                         | Source                                                                                                     |
| -------------------- | --------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| Sorting              | Merge sort, Quick sort, Counting sort, Sort Colors (75)                                                                                 | [Sorting.java](src/dsa/sorting/Sorting.java)                                                               |
| Arrays               | Maximum Subarray (53), Best Time to Buy and Sell Stock (121), Product of Array Except Self (238), Rotate Array (189)                    | [ArrayProblems.java](src/dsa/arrays/ArrayProblems.java)                                                    |
| Strings              | Valid Palindrome (125), Valid Anagram (242), Longest Common Prefix (14), Reverse Words (151), strStr with KMP (28)                      | [StringProblems.java](src/dsa/strings/StringProblems.java)                                                 |
| Hashing              | Two Sum (1), Group Anagrams (49), Longest Consecutive Sequence (128), Subarray Sum Equals K (560)                                       | [HashingProblems.java](src/dsa/hashing/HashingProblems.java)                                               |
| Two pointers         | Remove Duplicates (26), 3Sum (15), Container With Most Water (11), Trapping Rain Water (42)                                             | [TwoPointerProblems.java](src/dsa/twopointers/TwoPointerProblems.java)                                     |
| Sliding window       | Longest Substring Without Repeating (3), Minimum Size Subarray Sum (209), Minimum Window Substring (76), Sliding Window Maximum (239)   | [SlidingWindowProblems.java](src/dsa/slidingwindow/SlidingWindowProblems.java)                             |
| Binary search        | Binary Search (704), lower/upper bound, Search Range (34), Search in Rotated Array (33), Koko Eating Bananas (875)                      | [BinarySearchProblems.java](src/dsa/binarysearch/BinarySearchProblems.java)                                |
| Stacks               | Valid Parentheses (20), Evaluate RPN (150), Daily Temperatures (739), Largest Rectangle in Histogram (84), Min Stack (155)              | [StackProblems.java](src/dsa/stacks/StackProblems.java), [MinStack.java](src/dsa/stacks/MinStack.java)     |
| Linked lists         | Reverse List (206), Merge Two Sorted Lists (21), Linked List Cycle (141), Middle of List (876), Remove Nth From End (19)                | [LinkedListProblems.java](src/dsa/linkedlist/LinkedListProblems.java)                                      |
| Trees and tries      | Max Depth (104), Inorder Traversal (94), Level Order (102), Validate BST (98), Lowest Common Ancestor (236), Trie (208)                 | [TreeProblems.java](src/dsa/trees/TreeProblems.java), [Trie.java](src/dsa/trees/Trie.java)                 |
| Heaps                | Kth Largest (215), Top K Frequent (347), Merge k Sorted Lists (23), Median from Data Stream (295)                                       | [HeapProblems.java](src/dsa/heaps/HeapProblems.java), [MedianFinder.java](src/dsa/heaps/MedianFinder.java) |
| Graphs               | Number of Islands (200), Course Schedule (207), Network Delay Time / Dijkstra (743), Connected Components / Union-Find (323)            | [GraphProblems.java](src/dsa/graphs/GraphProblems.java), [UnionFind.java](src/dsa/graphs/UnionFind.java)   |
| Backtracking         | Subsets (78), Permutations (46), Combination Sum (39), N-Queens II (52)                                                                 | [BacktrackingProblems.java](src/dsa/backtracking/BacktrackingProblems.java)                                |
| Dynamic programming  | Climbing Stairs (70), House Robber (198), Unique Paths (62), Coin Change (322), LIS (300), LCS (1143), Edit Distance (72), 0/1 Knapsack | [DynamicProgramming.java](src/dsa/dp/DynamicProgramming.java)                                              |
| Greedy and intervals | Jump Game (55), Jump Game II (45), Merge Intervals (56), Non-overlapping Intervals (435)                                                | [GreedyProblems.java](src/dsa/greedy/GreedyProblems.java)                                                  |

## Practice problems

145 more problems, one `<Topic>Practice.java` per topic (design problems get their own class).
Each has the same idea-and-complexity comment and its own tests.

| Topic                | Problems (LC #)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Source                                                                                                                                                 |
| -------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Sorting              | Merge Sorted Array (88), Largest Number (179), H-Index (274), Insertion Sort List (147)                                                                                                                                                                                                                                                                                                                                                                                                                                   | [SortingPractice.java](src/dsa/sorting/SortingPractice.java)                                                                                           |
| Arrays               | Majority Element (169), Rotate Image (48), Spiral Matrix (54), Set Matrix Zeroes (73), Next Permutation (31), First Missing Positive (41)                                                                                                                                                                                                                                                                                                                                                                                 | [ArrayPractice.java](src/dsa/arrays/ArrayPractice.java)                                                                                                |
| Strings              | Palindromic Substrings (647), Encode and Decode Strings (271), String to Integer (atoi) (8), Zigzag Conversion (6), Multiply Strings (43)                                                                                                                                                                                                                                                                                                                                                                                 | [StringPractice.java](src/dsa/strings/StringPractice.java)                                                                                             |
| Hashing              | Contains Duplicate (217), Valid Sudoku (36), Ransom Note (383), Isomorphic Strings (205), Word Pattern (290), Insert Delete GetRandom O(1) (380)                                                                                                                                                                                                                                                                                                                                                                          | [HashingPractice.java](src/dsa/hashing/HashingPractice.java), [RandomizedSet.java](src/dsa/hashing/RandomizedSet.java)                                 |
| Two pointers         | Two Sum II - Input Array Is Sorted (167), Move Zeroes (283), Squares of a Sorted Array (977), 3Sum Closest (16), 4Sum (18), Valid Palindrome II (680)                                                                                                                                                                                                                                                                                                                                                                     | [TwoPointerPractice.java](src/dsa/twopointers/TwoPointerPractice.java)                                                                                 |
| Sliding window       | Maximum Average Subarray I (643), Longest Repeating Character Replacement (424), Permutation in String (567), Find All Anagrams in a String (438), Max Consecutive Ones III (1004), Fruit Into Baskets (904)                                                                                                                                                                                                                                                                                                              | [SlidingWindowPractice.java](src/dsa/slidingwindow/SlidingWindowPractice.java)                                                                         |
| Binary search        | Search Insert Position (35), Sqrt(x) (69), Search a 2D Matrix (74), Find Minimum in Rotated Sorted Array (153), Find Peak Element (162), Time Based Key-Value Store (981), Capacity To Ship Packages Within D Days (1011), Median of Two Sorted Arrays (4)                                                                                                                                                                                                                                                                | [BinarySearchPractice.java](src/dsa/binarysearch/BinarySearchPractice.java), [TimeMap.java](src/dsa/binarysearch/TimeMap.java)                         |
| Stacks               | Implement Queue using Stacks (232), Next Greater Element I (496), Next Greater Element II (503), Car Fleet (853), Decode String (394), Simplify Path (71), Basic Calculator II (227)                                                                                                                                                                                                                                                                                                                                      | [StackPractice.java](src/dsa/stacks/StackPractice.java), [MyQueue.java](src/dsa/stacks/MyQueue.java)                                                   |
| Linked lists         | Intersection of Two Linked Lists (160), Palindrome Linked List (234), Linked List Cycle II (142), Add Two Numbers (2), Reorder List (143), Copy List with Random Pointer (138), Find the Duplicate Number (287), LRU Cache (146), Reverse Nodes in k-Group (25)                                                                                                                                                                                                                                                           | [LinkedListPractice.java](src/dsa/linkedlist/LinkedListPractice.java), [LRUCache.java](src/dsa/linkedlist/LRUCache.java)                               |
| Trees and tries      | Invert Binary Tree (226), Same Tree (100), Diameter of Binary Tree (543), Balanced Binary Tree (110), Subtree of Another Tree (572), Path Sum (112), Lowest Common Ancestor of a BST (235), Binary Tree Right Side View (199), Count Good Nodes in Binary Tree (1448), Kth Smallest Element in a BST (230), Construct Binary Tree from Preorder and Inorder (105), Design Add and Search Words (211), Binary Tree Maximum Path Sum (124), Serialize and Deserialize Binary Tree (297), Word Search II (212)               | [TreePractice.java](src/dsa/trees/TreePractice.java), [Codec.java](src/dsa/trees/Codec.java), [WordDictionary.java](src/dsa/trees/WordDictionary.java) |
| Heaps                | Kth Largest Element in a Stream (703), Last Stone Weight (1046), K Closest Points to Origin (973), Task Scheduler (621), Reorganize String (767), Kth Smallest Element in a Sorted Matrix (378), Design Twitter (355)                                                                                                                                                                                                                                                                                                     | [HeapPractice.java](src/dsa/heaps/HeapPractice.java), [KthLargest.java](src/dsa/heaps/KthLargest.java), [Twitter.java](src/dsa/heaps/Twitter.java)     |
| Graphs               | Max Area of Island (695), Number of Provinces (547), Clone Graph (133), Rotting Oranges (994), Pacific Atlantic Water Flow (417), Surrounded Regions (130), Course Schedule II (210), Graph Valid Tree (261), Redundant Connection (684), Min Cost to Connect All Points (1584), Cheapest Flights Within K Stops (787), Word Ladder (127), Swim in Rising Water (778), Reconstruct Itinerary (332), Alien Dictionary (269)                                                                                                | [GraphPractice.java](src/dsa/graphs/GraphPractice.java)                                                                                                |
| Backtracking         | Combinations (77), Subsets II (90), Combination Sum II (40), Permutations II (47), Generate Parentheses (22), Letter Combinations of a Phone Number (17), Word Search (79), Palindrome Partitioning (131), N-Queens (51), Sudoku Solver (37)                                                                                                                                                                                                                                                                              | [BacktrackingPractice.java](src/dsa/backtracking/BacktrackingPractice.java)                                                                            |
| Dynamic programming  | Min Cost Climbing Stairs (746), House Robber II (213), Minimum Path Sum (64), Longest Palindromic Substring (5), Decode Ways (91), Maximum Product Subarray (152), Word Break (139), Perfect Squares (279), Maximal Square (221), Partition Equal Subset Sum (416), Coin Change II (518), Target Sum (494), Best Time to Buy and Sell Stock with Cooldown (309), Interleaving String (97), Longest Increasing Path in a Matrix (329), Distinct Subsequences (115), Burst Balloons (312), Regular Expression Matching (10) | [DynamicProgrammingPractice.java](src/dsa/dp/DynamicProgrammingPractice.java)                                                                          |
| Greedy and intervals | Meeting Rooms (252), Gas Station (134), Hand of Straights (846), Partition Labels (763), Valid Parenthesis String (678), Merge Triplets to Form Target Triplet (1899), Insert Interval (57), Meeting Rooms II (253), Minimum Number of Arrows to Burst Balloons (452), Candy (135), Minimum Interval to Include Each Query (1851)                                                                                                                                                                                         | [GreedyPractice.java](src/dsa/greedy/GreedyPractice.java)                                                                                              |
| Math and bits        | Single Number (136), Number of 1 Bits (191), Counting Bits (338), Reverse Bits (190), Missing Number (268), Palindrome Number (9), Plus One (66), Happy Number (202), Reverse Integer (7), Pow(x, n) (50), Count Primes (204), Sum of Two Integers (371)                                                                                                                                                                                                                                                                  | [BitProblems.java](src/dsa/bits/BitProblems.java)                                                                                                      |

## Pattern cheat sheet

Map clues in the problem statement to a technique.

| If the problem says…                                 | Try                                        |
| ---------------------------------------------------- | ------------------------------------------ |
| Sorted array, find a pair/triplet                    | Two pointers                               |
| Longest/shortest contiguous subarray or substring    | Sliding window                             |
| Subarray sum with negative numbers                   | Prefix sums + HashMap                      |
| "Have I seen this before?", counting, grouping       | HashMap / HashSet                          |
| Sorted input, or "minimum X such that…" is monotonic | Binary search (on the index or the answer) |
| Next greater/smaller element, matching brackets      | Stack (monotonic stack)                    |
| Top k, k-th largest, merge k sorted, running median  | Heap (PriorityQueue)                       |
| Shortest path, unweighted                            | BFS                                        |
| Shortest path, non-negative weights                  | Dijkstra                                   |
| Dependencies / ordering                              | Topological sort                           |
| Are these connected? Merging groups                  | Union-find                                 |
| All combinations / permutations / subsets            | Backtracking                               |
| Count ways, min/max cost, overlapping subproblems    | Dynamic programming                        |
| Intervals                                            | Sort by start (merge) or end (scheduling)  |
| Prefix lookups over many words                       | Trie                                       |

## Complexity reference

| Operation               | Array | ArrayList      | LinkedList   | HashMap/Set | TreeMap/Set | PriorityQueue |
| ----------------------- | ----- | -------------- | ------------ | ----------- | ----------- | ------------- |
| Access by index         | O(1)  | O(1)           | O(n)         | –           | –           | –             |
| Search                  | O(n)  | O(n)           | O(n)         | O(1) avg    | O(log n)    | O(n)          |
| Insert/remove at end    | –     | O(1) amortised | O(1)         | O(1) avg    | O(log n)    | O(log n)      |
| Insert/remove in middle | –     | O(n)           | O(1) at node | –           | –           | –             |
| Min/max                 | O(n)  | O(n)           | O(n)         | O(n)        | O(log n)    | O(1) peek     |

| Algorithm      | Best    | Average | Worst   | Space | Stable |
| -------------- | ------- | ------- | ------- | ----- | ------ |
| Merge sort     | n log n | n log n | n log n | n     | Yes    |
| Quick sort     | n log n | n log n | n²      | log n | No     |
| Heap sort      | n log n | n log n | n log n | 1     | No     |
| Insertion sort | n       | n²      | n²      | 1     | Yes    |
| Counting sort  | n + k   | n + k   | n + k   | k     | Yes    |

Rough limits for a 1-second judge (about 10⁸ simple operations):

| n       | Acceptable complexity |
| ------- | --------------------- |
| ≤ 12    | O(n!)                 |
| ≤ 25    | O(2ⁿ)                 |
| ≤ 500   | O(n³)                 |
| ≤ 5,000 | O(n²)                 |
| ≤ 10⁶   | O(n log n)            |
| ≤ 10⁸   | O(n)                  |

## Java interview tips

- Use `ArrayDeque` for stacks and queues, not `Stack` or `LinkedList`.
- `PriorityQueue` is a min-heap. Pass `Collections.reverseOrder()` for a max-heap.
- Compute the midpoint as `(lo + hi) >>> 1` or `lo + (hi - lo) / 2` to avoid int overflow.
- Compare with `Integer.compare(a, b)` in comparators, not `a - b`, which can overflow.
- Compare boxed `Integer` values with `.equals`. `==` only works reliably for -128 to 127.
- Use `long` for sums and products that can exceed about 2.1 × 10⁹.
- Use `StringBuilder` to build strings in a loop.

## In the interview

1. Restate the problem and ask about the input: size, negatives, duplicates, empty input.
2. Work through a small example by hand.
3. Say the brute force and its complexity, then improve it.
4. Code it, talking through the choices.
5. Test it with your example and the edge cases: empty, one element, all equal, very large values.
6. State the final time and space complexity.

## Progress tracker

`tracker/index.html` is a progress tracker for all 214 problems above, with links to each
LeetCode page and solution. You can open it on a phone or a
computer. Mark each problem To do, Attempted, Solved or Revise, write notes, and see your roadmap
week, streak and topic progress.
