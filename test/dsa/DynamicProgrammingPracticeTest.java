package dsa;

import dsa.dp.DynamicProgrammingPractice;
import java.util.List;

final class DynamicProgrammingPracticeTest {
    static void run(Check c) {
        c.eq("minCostClimbingStairs", 15, DynamicProgrammingPractice.minCostClimbingStairs(new int[] {10, 15, 20}));
        c.eq("minCostClimbingStairs 2", 6,
                DynamicProgrammingPractice.minCostClimbingStairs(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        c.eq("rob2", 3, DynamicProgrammingPractice.rob2(new int[] {2, 3, 2}));
        c.eq("rob2 2", 4, DynamicProgrammingPractice.rob2(new int[] {1, 2, 3, 1}));
        c.eq("rob2 one house", 5, DynamicProgrammingPractice.rob2(new int[] {5}));
        c.eq("minPathSum", 7, DynamicProgrammingPractice.minPathSum(new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        c.eq("minPathSum row", 6, DynamicProgrammingPractice.minPathSum(new int[][] {{1, 2, 3}}));
        c.eq("longestPalindrome", "bab", DynamicProgrammingPractice.longestPalindrome("babad"));
        c.eq("longestPalindrome even", "bb", DynamicProgrammingPractice.longestPalindrome("cbbd"));
        c.eq("longestPalindrome single", "a", DynamicProgrammingPractice.longestPalindrome("a"));
        c.eq("numDecodings", 2, DynamicProgrammingPractice.numDecodings("12"));
        c.eq("numDecodings 226", 3, DynamicProgrammingPractice.numDecodings("226"));
        c.eq("numDecodings leading zero", 0, DynamicProgrammingPractice.numDecodings("06"));
        c.eq("numDecodings 11106", 2, DynamicProgrammingPractice.numDecodings("11106"));
        c.eq("numDecodings bad zero", 0, DynamicProgrammingPractice.numDecodings("100"));
        c.eq("maxProduct", 6, DynamicProgrammingPractice.maxProduct(new int[] {2, 3, -2, 4}));
        c.eq("maxProduct zero", 0, DynamicProgrammingPractice.maxProduct(new int[] {-2, 0, -1}));
        c.eq("maxProduct two negatives", 24, DynamicProgrammingPractice.maxProduct(new int[] {-2, 3, -4}));
        c.isTrue("wordBreak", DynamicProgrammingPractice.wordBreak("leetcode", List.of("leet", "code")));
        c.isTrue("wordBreak reuse", DynamicProgrammingPractice.wordBreak("applepenapple", List.of("apple", "pen")));
        c.isFalse("wordBreak no", DynamicProgrammingPractice.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
        c.eq("numSquares 12", 3, DynamicProgrammingPractice.numSquares(12));
        c.eq("numSquares 13", 2, DynamicProgrammingPractice.numSquares(13));
        char[][] m = {"10100".toCharArray(), "10111".toCharArray(), "11111".toCharArray(), "10010".toCharArray()};
        c.eq("maximalSquare", 4, DynamicProgrammingPractice.maximalSquare(m));
        c.eq("maximalSquare 3x3", 9, DynamicProgrammingPractice.maximalSquare(
                new char[][] {"111".toCharArray(), "111".toCharArray(), "111".toCharArray()}));
        c.eq("maximalSquare none", 0, DynamicProgrammingPractice.maximalSquare(new char[][] {"0".toCharArray()}));
        c.isTrue("canPartition", DynamicProgrammingPractice.canPartition(new int[] {1, 5, 11, 5}));
        c.isFalse("canPartition no", DynamicProgrammingPractice.canPartition(new int[] {1, 2, 3, 5}));
        c.eq("change", 4, DynamicProgrammingPractice.change(5, new int[] {1, 2, 5}));
        c.eq("change impossible", 0, DynamicProgrammingPractice.change(3, new int[] {2}));
        c.eq("change exact", 1, DynamicProgrammingPractice.change(10, new int[] {10}));
        c.eq("findTargetSumWays", 5, DynamicProgrammingPractice.findTargetSumWays(new int[] {1, 1, 1, 1, 1}, 3));
        c.eq("findTargetSumWays one", 1, DynamicProgrammingPractice.findTargetSumWays(new int[] {1}, 1));
        c.eq("findTargetSumWays zeros", 4, DynamicProgrammingPractice.findTargetSumWays(new int[] {0, 0, 1}, 1));
        c.eq("findTargetSumWays unreachable", 0, DynamicProgrammingPractice.findTargetSumWays(new int[] {1}, -2));
        c.eq("maxProfitWithCooldown", 3, DynamicProgrammingPractice.maxProfitWithCooldown(new int[] {1, 2, 3, 0, 2}));
        c.eq("maxProfitWithCooldown single", 0, DynamicProgrammingPractice.maxProfitWithCooldown(new int[] {1}));
        c.isTrue("isInterleave", DynamicProgrammingPractice.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        c.isFalse("isInterleave no", DynamicProgrammingPractice.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        c.isTrue("isInterleave empty", DynamicProgrammingPractice.isInterleave("", "", ""));
        c.isFalse("isInterleave length", DynamicProgrammingPractice.isInterleave("a", "", "aa"));
        c.eq("longestIncreasingPath", 4, DynamicProgrammingPractice.longestIncreasingPath(new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        c.eq("longestIncreasingPath 2", 4, DynamicProgrammingPractice.longestIncreasingPath(new int[][] {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
        c.eq("numDistinct", 3, DynamicProgrammingPractice.numDistinct("rabbbit", "rabbit"));
        c.eq("numDistinct 2", 5, DynamicProgrammingPractice.numDistinct("babgbag", "bag"));
        c.eq("maxCoins", 167, DynamicProgrammingPractice.maxCoins(new int[] {3, 1, 5, 8}));
        c.eq("maxCoins 2", 10, DynamicProgrammingPractice.maxCoins(new int[] {1, 5}));
        c.isFalse("isMatch", DynamicProgrammingPractice.isMatch("aa", "a"));
        c.isTrue("isMatch star", DynamicProgrammingPractice.isMatch("aa", "a*"));
        c.isTrue("isMatch dot star", DynamicProgrammingPractice.isMatch("ab", ".*"));
        c.isTrue("isMatch zero copies", DynamicProgrammingPractice.isMatch("aab", "c*a*b"));
        c.isFalse("isMatch mississippi", DynamicProgrammingPractice.isMatch("mississippi", "mis*is*p*."));
        c.isTrue("isMatch empty", DynamicProgrammingPractice.isMatch("", "a*b*"));
    }
}
