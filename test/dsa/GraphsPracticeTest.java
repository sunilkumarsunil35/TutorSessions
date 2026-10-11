package dsa;

import dsa.graphs.GraphPractice;
import dsa.graphs.GraphPractice.Node;
import java.util.List;

final class GraphsPracticeTest {
    static char[][] grid(String... rows) {
        char[][] g = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            g[i] = rows[i].toCharArray();
        }
        return g;
    }

    static void run(Check c) {
        c.eq("maxAreaOfIsland", 4, GraphPractice.maxAreaOfIsland(new int[][] {{1, 1, 0, 0}, {0, 1, 0, 1}, {0, 1, 0, 1}, {0, 0, 0, 1}}));
        c.eq("maxAreaOfIsland none", 0, GraphPractice.maxAreaOfIsland(new int[][] {{0, 0}, {0, 0}}));
        c.eq("findCircleNum", 2, GraphPractice.findCircleNum(new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        c.eq("findCircleNum separate", 3, GraphPractice.findCircleNum(new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));

        Node[] g = {new Node(1), new Node(2), new Node(3), new Node(4)};
        int[][] adj = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        for (int i = 0; i < 4; i++) {
            for (int j : adj[i]) {
                g[i].neighbors.add(g[j]);
            }
        }
        Node clone = GraphPractice.cloneGraph(g[0]);
        c.isTrue("cloneGraph new node", clone != g[0]);
        c.eq("cloneGraph value", 1, clone.val);
        c.eq("cloneGraph neighbours", 2, clone.neighbors.size());
        c.isTrue("cloneGraph neighbour copied", clone.neighbors.get(0) != g[1] && clone.neighbors.get(0).val == 2);
        c.isTrue("cloneGraph cycle kept", clone.neighbors.get(0).neighbors.get(0) == clone);
        c.isTrue("cloneGraph shared node", clone.neighbors.get(0).neighbors.get(1) == clone.neighbors.get(1).neighbors.get(1));
        c.isTrue("cloneGraph null", GraphPractice.cloneGraph(null) == null);

        c.eq("orangesRotting", 4, GraphPractice.orangesRotting(new int[][] {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        c.eq("orangesRotting unreachable", -1, GraphPractice.orangesRotting(new int[][] {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        c.eq("orangesRotting none fresh", 0, GraphPractice.orangesRotting(new int[][] {{0, 2}}));
        c.eq("pacificAtlantic",
                List.of(List.of(0, 4), List.of(1, 3), List.of(1, 4), List.of(2, 2), List.of(3, 0), List.of(3, 1), List.of(4, 0)),
                GraphPractice.pacificAtlantic(new int[][] {
                    {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}}));
        char[][] board = grid("XXXX", "XOOX", "XXOX", "XOXX");
        GraphPractice.solve(board);
        c.eq("solve surrounded", grid("XXXX", "XXXX", "XXXX", "XOXX"), board);
        char[][] edge = grid("OO", "OO");
        GraphPractice.solve(edge);
        c.eq("solve border survives", grid("OO", "OO"), edge);

        c.eq("findOrder", new int[] {0, 1}, GraphPractice.findOrder(2, new int[][] {{1, 0}}));
        c.eq("findOrder diamond", new int[] {0, 1, 2, 3}, GraphPractice.findOrder(4, new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
        c.eq("findOrder cycle", new int[0], GraphPractice.findOrder(2, new int[][] {{1, 0}, {0, 1}}));
        c.isTrue("validTree", GraphPractice.validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
        c.isFalse("validTree cycle", GraphPractice.validTree(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
        c.isFalse("validTree disconnected", GraphPractice.validTree(4, new int[][] {{0, 1}, {2, 3}}));
        c.isTrue("validTree single node", GraphPractice.validTree(1, new int[][] {}));
        c.eq("findRedundantConnection", new int[] {2, 3}, GraphPractice.findRedundantConnection(new int[][] {{1, 2}, {1, 3}, {2, 3}}));
        c.eq("findRedundantConnection 2", new int[] {1, 4},
                GraphPractice.findRedundantConnection(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
        c.eq("minCostConnectPoints", 20, GraphPractice.minCostConnectPoints(new int[][] {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
        c.eq("minCostConnectPoints 2", 18, GraphPractice.minCostConnectPoints(new int[][] {{3, 12}, {-2, 5}, {-4, 1}}));
        c.eq("minCostConnectPoints one", 0, GraphPractice.minCostConnectPoints(new int[][] {{0, 0}}));
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        c.eq("findCheapestPrice", 700, GraphPractice.findCheapestPrice(4, flights, 0, 3, 1));
        int[][] tri = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        c.eq("findCheapestPrice one stop", 200, GraphPractice.findCheapestPrice(3, tri, 0, 2, 1));
        c.eq("findCheapestPrice no stops", 500, GraphPractice.findCheapestPrice(3, tri, 0, 2, 0));
        c.eq("findCheapestPrice unreachable", -1, GraphPractice.findCheapestPrice(3, new int[][] {{0, 1, 5}}, 0, 2, 2));
        List<String> words = List.of("hot", "dot", "dog", "lot", "log", "cog");
        c.eq("ladderLength", 5, GraphPractice.ladderLength("hit", "cog", words));
        c.eq("ladderLength missing end", 0, GraphPractice.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log")));
        c.eq("swimInWater", 3, GraphPractice.swimInWater(new int[][] {{0, 2}, {1, 3}}));
        c.eq("swimInWater spiral", 16, GraphPractice.swimInWater(new int[][] {
            {0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}}));
        c.eq("findItinerary", List.of("JFK", "MUC", "LHR", "SFO", "SJC"), GraphPractice.findItinerary(List.of(
                List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"))));
        c.eq("findItinerary lexical", List.of("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"), GraphPractice.findItinerary(List.of(
                List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"), List.of("ATL", "JFK"), List.of("ATL", "SFO"))));
        c.eq("findItinerary dead end first", List.of("JFK", "NRT", "JFK", "KUL"), GraphPractice.findItinerary(List.of(
                List.of("JFK", "KUL"), List.of("JFK", "NRT"), List.of("NRT", "JFK"))));
        c.eq("alienOrder", "wertf", GraphPractice.alienOrder(new String[] {"wrt", "wrf", "er", "ett", "rftt"}));
        c.eq("alienOrder two", "zx", GraphPractice.alienOrder(new String[] {"z", "x"}));
        c.eq("alienOrder cycle", "", GraphPractice.alienOrder(new String[] {"z", "x", "z"}));
        c.eq("alienOrder prefix after word", "", GraphPractice.alienOrder(new String[] {"abc", "ab"}));
    }
}
