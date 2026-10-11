package dsa.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/** More graph practice: grids, multi-source BFS, topological order, MSTs and shortest paths. */
public final class GraphPractice {
    private GraphPractice() {}

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /** Graph node for LC 133. */
    public static class Node {
        public int val;
        public List<Node> neighbors = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * LC 695 Max Area of Island. Flood-fill each island, counting cells. Modifies the grid.
     *
     * <p>Time O(rows * cols). Space O(rows * cols) for the stack in the worst case.
     */
    public static int maxAreaOfIsland(int[][] grid) {
        int best = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] != 1) {
                    continue;
                }
                int area = 0;
                CellStack stack = new CellStack();
                stack.push(r, c);
                grid[r][c] = 0;
                while (!stack.isEmpty()) {
                    int[] cell = stack.pop();
                    area++;
                    for (int[] d : DIRECTIONS) {
                        int nr = cell[0] + d[0];
                        int nc = cell[1] + d[1];
                        if (inside(grid.length, grid[0].length, nr, nc) && grid[nr][nc] == 1) {
                            grid[nr][nc] = 0;
                            stack.push(nr, nc);
                        }
                    }
                }
                best = Math.max(best, area);
            }
        }
        return best;
    }

    /** A tiny stack of grid cells. */
    private static final class CellStack {
        private final ArrayDeque<int[]> items = new ArrayDeque<>();

        void push(int r, int c) {
            items.push(new int[] {r, c});
        }

        int[] pop() {
            return items.pop();
        }

        boolean isEmpty() {
            return items.isEmpty();
        }
    }

    private static boolean inside(int rows, int cols, int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    /**
     * LC 547 Number of Provinces (adjacency matrix). Union every connected pair and count what's
     * left.
     *
     * <p>Time O(n^2 * α(n)). Space O(n).
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        int provinces = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1 && uf.union(i, j)) {
                    provinces--;
                }
            }
        }
        return provinces;
    }

    /**
     * LC 133 Clone Graph. BFS, creating each copy the first time its original is seen; the map
     * from original to copy also serves as the visited set.
     *
     * <p>Time O(V + E). Space O(V).
     */
    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> copies = new HashMap<>();
        copies.put(node, new Node(node.val));
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nb : cur.neighbors) {
                if (!copies.containsKey(nb)) {
                    copies.put(nb, new Node(nb.val));
                    queue.add(nb);
                }
                copies.get(cur).neighbors.add(copies.get(nb));
            }
        }
        return copies.get(node);
    }

    /**
     * LC 994 Rotting Oranges. Multi-source BFS from every rotten orange at once; each BFS round
     * is one minute.
     *
     * <p>Time O(rows * cols). Space O(rows * cols).
     */
    public static int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2) {
                    queue.add(new int[] {r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }
        int minutes = 0;
        while (!queue.isEmpty() && fresh > 0) {
            minutes++;
            for (int size = queue.size(); size > 0; size--) {
                int[] cell = queue.poll();
                for (int[] d : DIRECTIONS) {
                    int nr = cell[0] + d[0];
                    int nc = cell[1] + d[1];
                    if (inside(grid.length, grid[0].length, nr, nc) && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        queue.add(new int[] {nr, nc});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }

    /**
     * LC 417 Pacific Atlantic Water Flow. Instead of flowing down from every cell, flow uphill
     * from each ocean's border; answer cells reached from both oceans (row-major order).
     *
     * <p>Time O(rows * cols). Space O(rows * cols).
     */
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        Queue<int[]> pq = new ArrayDeque<>();
        Queue<int[]> aq = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            mark(pacific, pq, r, 0);
            mark(atlantic, aq, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            mark(pacific, pq, 0, c);
            mark(atlantic, aq, rows - 1, c);
        }
        climb(heights, pacific, pq);
        climb(heights, atlantic, aq);
        List<List<Integer>> out = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    out.add(List.of(r, c));
                }
            }
        }
        return out;
    }

    private static void mark(boolean[][] seen, Queue<int[]> queue, int r, int c) {
        if (!seen[r][c]) {
            seen[r][c] = true;
            queue.add(new int[] {r, c});
        }
    }

    private static void climb(int[][] h, boolean[][] seen, Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRECTIONS) {
                int nr = cell[0] + d[0];
                int nc = cell[1] + d[1];
                if (inside(h.length, h[0].length, nr, nc) && !seen[nr][nc] && h[nr][nc] >= h[cell[0]][cell[1]]) {
                    mark(seen, queue, nr, nc);
                }
            }
        }
    }

    /**
     * LC 130 Surrounded Regions. Any 'O' connected to the border survives: mark those first,
     * flip every other 'O' to 'X', then restore the marked cells.
     *
     * <p>Time O(rows * cols). Space O(rows * cols).
     */
    public static void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if ((r == 0 || c == 0 || r == rows - 1 || c == cols - 1) && board[r][c] == 'O') {
                    board[r][c] = '#';
                    queue.add(new int[] {r, c});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRECTIONS) {
                int nr = cell[0] + d[0];
                int nc = cell[1] + d[1];
                if (inside(rows, cols, nr, nc) && board[nr][nc] == 'O') {
                    board[nr][nc] = '#';
                    queue.add(new int[] {nr, nc});
                }
            }
        }
        for (char[] row : board) {
            for (int c = 0; c < cols; c++) {
                row[c] = row[c] == '#' ? 'O' : 'X';
            }
        }
    }

    /**
     * LC 210 Course Schedule II. Kahn's algorithm, returning the order courses were taken, or an
     * empty array when a cycle makes it impossible.
     *
     * <p>Time O(V + E). Space O(V + E).
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> next = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            next.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            next.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int[] order = new int[numCourses];
        int taken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[taken++] = course;
            for (int n : next.get(course)) {
                if (--indegree[n] == 0) {
                    queue.add(n);
                }
            }
        }
        return taken == numCourses ? order : new int[0];
    }

    /**
     * LC 261 Graph Valid Tree. A tree on n nodes has exactly n - 1 edges and no cycle.
     *
     * <p>Time O(E * α(n)). Space O(n).
     */
    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] e : edges) {
            if (!uf.union(e[0], e[1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * LC 684 Redundant Connection. The first edge joining two already-connected nodes closes the
     * cycle (nodes are 1..n).
     *
     * <p>Time O(n * α(n)). Space O(n).
     */
    public static int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length + 1);
        for (int[] e : edges) {
            if (!uf.union(e[0], e[1])) {
                return e;
            }
        }
        return new int[0];
    }

    /**
     * LC 1584 Min Cost to Connect All Points (Manhattan distance). Prim's algorithm on the dense
     * complete graph: repeatedly add the closest outside point.
     *
     * <p>Time O(n^2). Space O(n).
     */
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] inTree = new boolean[n];
        dist[0] = 0;
        int total = 0;
        for (int added = 0; added < n; added++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!inTree[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }
            inTree[u] = true;
            total += dist[u];
            for (int v = 0; v < n; v++) {
                if (!inTree[v]) {
                    int d = Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1]);
                    dist[v] = Math.min(dist[v], d);
                }
            }
        }
        return total;
    }

    /**
     * LC 787 Cheapest Flights Within K Stops. Bellman-Ford limited to k + 1 rounds (k stops means
     * k + 1 flights); each round relaxes from the previous round's prices only.
     *
     * <p>Time O(k * E). Space O(n).
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] price = new int[n];
        Arrays.fill(price, Integer.MAX_VALUE);
        price[src] = 0;
        for (int round = 0; round <= k; round++) {
            int[] next = price.clone();
            for (int[] f : flights) {
                if (price[f[0]] != Integer.MAX_VALUE && price[f[0]] + f[2] < next[f[1]]) {
                    next[f[1]] = price[f[0]] + f[2];
                }
            }
            price = next;
        }
        return price[dst] == Integer.MAX_VALUE ? -1 : price[dst];
    }

    /**
     * LC 127 Word Ladder. BFS over words; neighbours differ by one letter. Removing a word from
     * the set when it is queued marks it visited.
     *
     * <p>Time O(n * L * 26). Space O(n * L).
     */
    public static int ladderLength(String begin, String end, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(end)) {
            return 0;
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.add(begin);
        for (int steps = 1; !queue.isEmpty(); steps++) {
            for (int size = queue.size(); size > 0; size--) {
                String word = queue.poll();
                if (word.equals(end)) {
                    return steps;
                }
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String candidate = new String(chars);
                        if (words.remove(candidate)) {
                            queue.add(candidate);
                        }
                    }
                    chars[i] = original;
                }
            }
        }
        return 0;
    }

    /**
     * LC 778 Swim in Rising Water. Dijkstra where a path's cost is its highest cell, not its sum.
     *
     * <p>Time O(n^2 log n). Space O(n^2).
     */
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] seen = new boolean[n][n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.add(new int[] {grid[0][0], 0, 0});
        seen[0][0] = true;
        while (true) {
            int[] top = heap.poll();
            if (top[1] == n - 1 && top[2] == n - 1) {
                return top[0];
            }
            for (int[] d : DIRECTIONS) {
                int nr = top[1] + d[0];
                int nc = top[2] + d[1];
                if (inside(n, n, nr, nc) && !seen[nr][nc]) {
                    seen[nr][nc] = true;
                    heap.add(new int[] {Math.max(top[0], grid[nr][nc]), nr, nc});
                }
            }
        }
    }

    /**
     * LC 332 Reconstruct Itinerary (smallest lexical order, using every ticket). Hierholzer's
     * algorithm: always take the smallest remaining destination; an airport is added to the
     * front of the route once it has no tickets left.
     *
     * <p>Time O(E log E). Space O(E).
     */
    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> t : tickets) {
            graph.computeIfAbsent(t.get(0), k -> new PriorityQueue<>()).add(t.get(1));
        }
        LinkedList<String> route = new LinkedList<>();
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        while (!stack.isEmpty()) {
            PriorityQueue<String> out = graph.get(stack.peek());
            if (out != null && !out.isEmpty()) {
                stack.push(out.poll());
            } else {
                route.addFirst(stack.pop());
            }
        }
        return route;
    }

    /**
     * LC 269 Alien Dictionary. The first differing letter of each adjacent word pair gives an
     * edge; topologically sort the letters. Returns "" if the order is impossible (a cycle, or a
     * word listed before its own prefix).
     *
     * <p>Time O(total letters). Space O(alphabet^2).
     */
    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> next = new LinkedHashMap<>();
        Map<Character, Integer> indegree = new LinkedHashMap<>();
        for (String w : words) {
            for (char c : w.toCharArray()) {
                next.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }
        for (int i = 0; i + 1 < words.length; i++) {
            String a = words[i];
            String b = words[i + 1];
            if (a.length() > b.length() && a.startsWith(b)) {
                return "";
            }
            for (int j = 0; j < Math.min(a.length(), b.length()); j++) {
                if (a.charAt(j) != b.charAt(j)) {
                    if (next.get(a.charAt(j)).add(b.charAt(j))) {
                        indegree.merge(b.charAt(j), 1, Integer::sum);
                    }
                    break;
                }
            }
        }
        Queue<Character> queue = new ArrayDeque<>();
        indegree.forEach((c, d) -> {
            if (d == 0) {
                queue.add(c);
            }
        });
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            order.append(c);
            for (char n : next.get(c)) {
                if (indegree.merge(n, -1, Integer::sum) == 0) {
                    queue.add(n);
                }
            }
        }
        return order.length() == indegree.size() ? order.toString() : "";
    }
}
