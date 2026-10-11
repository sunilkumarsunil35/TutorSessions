package dsa.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** More dynamic programming: 1-D, grid, knapsack variants, two-string and interval DP. */
public final class DynamicProgrammingPractice {
    private DynamicProgrammingPractice() {}

    /**
     * LC 746 Min Cost Climbing Stairs. cost to reach step i = cost[i] + min of the two steps
     * below; the top is just past the last step.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int minCostClimbingStairs(int[] cost) {
        int a = 0;
        int b = 0;
        for (int c : cost) {
            int next = c + Math.min(a, b);
            a = b;
            b = next;
        }
        return Math.min(a, b);
    }

    /**
     * LC 213 House Robber II (houses in a circle). The first and last houses can't both be
     * robbed, so take the better of skipping one or the other.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    private static int robRange(int[] nums, int from, int to) {
        int prev = 0;
        int cur = 0;
        for (int i = from; i <= to; i++) {
            int next = Math.max(cur, prev + nums[i]);
            prev = cur;
            cur = next;
        }
        return cur;
    }

    /**
     * LC 64 Minimum Path Sum (moving right or down). Each cell adds its value to the cheaper of
     * the cell above and the cell to the left.
     *
     * <p>Time O(rows * cols). Space O(cols).
     */
    public static int minPathSum(int[][] grid) {
        int cols = grid[0].length;
        int[] dp = new int[cols];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 && c == 0) {
                    dp[c] = grid[0][0];
                } else if (r == 0) {
                    dp[c] = dp[c - 1] + grid[r][c];
                } else if (c == 0) {
                    dp[c] += grid[r][c];
                } else {
                    dp[c] = Math.min(dp[c], dp[c - 1]) + grid[r][c];
                }
            }
        }
        return dp[cols - 1];
    }

    /**
     * LC 5 Longest Palindromic Substring. Expand around every centre; keep the longest (the
     * first one found wins ties).
     *
     * <p>Time O(n^2). Space O(1).
     */
    public static String longestPalindrome(String s) {
        int bestStart = 0;
        int bestLen = 0;
        for (int c = 0; c < 2 * s.length() - 1; c++) {
            int l = c / 2;
            int r = l + c % 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (r - l - 1 > bestLen) {
                bestLen = r - l - 1;
                bestStart = l + 1;
            }
        }
        return s.substring(bestStart, bestStart + bestLen);
    }

    /**
     * LC 91 Decode Ways ('A' = 1 ... 'Z' = 26). ways(i) = ways(i - 1) if the last digit is 1-9,
     * plus ways(i - 2) if the last two digits form 10-26.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int numDecodings(String s) {
        int prev2 = 1; // ways for the empty prefix
        int prev1 = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            int cur = 0;
            if (s.charAt(i - 1) != '0') {
                cur += prev1;
            }
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) {
                cur += prev2;
            }
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    /**
     * LC 152 Maximum Product Subarray. Track both the largest and smallest product ending here,
     * since a negative number swaps them.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int maxProduct(int[] nums) {
        int hi = nums[0];
        int lo = nums[0];
        int best = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) {
                int t = hi;
                hi = lo;
                lo = t;
            }
            hi = Math.max(x, hi * x);
            lo = Math.min(x, lo * x);
            best = Math.max(best, hi);
        }
        return best;
    }

    /**
     * LC 139 Word Break. dp[i] is true when s[0..i) splits into dictionary words: some earlier
     * true dp[j] followed by a word s[j..i).
     *
     * <p>Time O(n^2) substring checks. Space O(n).
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i && !dp[i]; j++) {
                dp[i] = dp[j] && dict.contains(s.substring(j, i));
            }
        }
        return dp[s.length()];
    }

    /**
     * LC 279 Perfect Squares: fewest squares summing to n. Unbounded knapsack over the squares.
     *
     * <p>Time O(n sqrt n). Space O(n).
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // all 1s
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * LC 221 Maximal Square. The largest square ending at a '1' is 1 + the smallest of the
     * squares ending above, left and diagonally up-left.
     *
     * <p>Time O(rows * cols). Space O(cols).
     */
    public static int maximalSquare(char[][] matrix) {
        int cols = matrix[0].length;
        int[] dp = new int[cols + 1];
        int best = 0;
        for (char[] row : matrix) {
            int diag = 0; // dp value up-left of the current cell
            for (int c = 1; c <= cols; c++) {
                int above = dp[c];
                dp[c] = row[c - 1] == '1' ? 1 + Math.min(diag, Math.min(above, dp[c - 1])) : 0;
                best = Math.max(best, dp[c]);
                diag = above;
            }
        }
        return best * best;
    }

    /**
     * LC 416 Partition Equal Subset Sum: is there a subset summing to half the total? 0/1
     * knapsack on booleans, iterating sums downwards.
     *
     * <p>Time O(n * sum). Space O(sum).
     */
    public static boolean canPartition(int[] nums) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }
        if (total % 2 == 1) {
            return false;
        }
        int half = total / 2;
        boolean[] can = new boolean[half + 1];
        can[0] = true;
        for (int x : nums) {
            for (int s = half; s >= x; s--) {
                can[s] |= can[s - x];
            }
        }
        return can[half];
    }

    /**
     * LC 518 Coin Change II: number of combinations making the amount. Loop coins on the outside
     * so each combination is counted once, not once per ordering.
     *
     * <p>Time O(amount * coins). Space O(amount).
     */
    public static int change(int amount, int[] coins) {
        int[] ways = new int[amount + 1];
        ways[0] = 1;
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                ways[a] += ways[a - coin];
            }
        }
        return ways[amount];
    }

    /**
     * LC 494 Target Sum. If P is the set given '+', sum(P) = (total + target) / 2, so count the
     * subsets with that sum.
     *
     * <p>Time O(n * sum). Space O(sum).
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }
        if (Math.abs(target) > total || (total + target) % 2 != 0) {
            return 0;
        }
        int goal = (total + target) / 2;
        int[] ways = new int[goal + 1];
        ways[0] = 1;
        for (int x : nums) {
            for (int s = goal; s >= x; s--) {
                ways[s] += ways[s - x];
            }
        }
        return ways[goal];
    }

    /**
     * LC 309 Best Time to Buy and Sell Stock with Cooldown. Three states per day: holding a
     * share, just sold (cooldown next), or resting with no share.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int maxProfitWithCooldown(int[] prices) {
        int hold = Integer.MIN_VALUE / 2;
        int sold = 0;
        int rest = 0;
        for (int p : prices) {
            int prevSold = sold;
            sold = hold + p;
            hold = Math.max(hold, rest - p);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(sold, rest);
    }

    /**
     * LC 97 Interleaving String. dp[i][j]: can s3[0..i+j) be formed from s1[0..i) and s2[0..j)?
     * Kept in one row.
     *
     * <p>Time O(n * m). Space O(m).
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else {
                    boolean fromS1 = i > 0 && dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    boolean fromS2 = j > 0 && dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    dp[j] = fromS1 || fromS2;
                }
            }
        }
        return dp[s2.length()];
    }

    /**
     * LC 329 Longest Increasing Path in a Matrix. DFS with memoisation: the best path from a cell
     * is 1 + the best from any larger neighbour. Strictly increasing paths can't loop, so no
     * visited set is needed.
     *
     * <p>Time O(rows * cols). Space O(rows * cols).
     */
    public static int longestIncreasingPath(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];
        int best = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                best = Math.max(best, lip(matrix, r, c, memo));
            }
        }
        return best;
    }

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int lip(int[][] m, int r, int c, int[][] memo) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }
        int best = 1;
        for (int[] d : DIRECTIONS) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nc >= 0 && nr < m.length && nc < m[0].length && m[nr][nc] > m[r][c]) {
                best = Math.max(best, 1 + lip(m, nr, nc, memo));
            }
        }
        memo[r][c] = best;
        return best;
    }

    /**
     * LC 115 Distinct Subsequences: how many ways t appears as a subsequence of s. Iterate t
     * backwards in one row so each s character is used once per position.
     *
     * <p>Time O(n * m). Space O(m).
     */
    public static int numDistinct(String s, String t) {
        long[] dp = new long[t.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = t.length(); j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return (int) dp[t.length()];
    }

    /**
     * LC 312 Burst Balloons. Interval DP: choose k as the LAST balloon burst in (i, j); its
     * neighbours are then the fixed walls i and j.
     *
     * <p>Time O(n^3). Space O(n^2).
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length + 2;
        int[] a = new int[n];
        a[0] = a[n - 1] = 1;
        System.arraycopy(nums, 0, a, 1, nums.length);
        int[][] dp = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + a[i] * a[k] * a[j] + dp[k][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * LC 10 Regular Expression Matching ('.' any char, '*' zero or more of the previous).
     * dp[i][j]: does s[0..i) match p[0..j)? A star either drops "x*" or eats one more matching
     * character.
     *
     * <p>Time O(n * m). Space O(n * m).
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    boolean eatsOne = (prev == '.' || prev == s.charAt(i - 1)) && dp[i - 1][j];
                    dp[i][j] = dp[i][j - 2] || eatsOne;
                } else {
                    dp[i][j] = (pc == '.' || pc == s.charAt(i - 1)) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
