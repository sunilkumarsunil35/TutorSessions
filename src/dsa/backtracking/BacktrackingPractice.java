package dsa.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** More backtracking: duplicates handling, grids, partitions and constraint solving. */
public final class BacktrackingPractice {
    private BacktrackingPractice() {}

    /**
     * LC 77 Combinations: all k-number choices from 1..n.
     *
     * <p>Time O(k * C(n, k)). Space O(k).
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> out = new ArrayList<>();
        combine(1, n, k, new ArrayList<>(), out);
        return out;
    }

    private static void combine(int start, int n, int k, List<Integer> path, List<List<Integer>> out) {
        if (path.size() == k) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n - (k - path.size()) + 1; i++) { // leave room for the rest
            path.add(i);
            combine(i + 1, n, k, path, out);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LC 90 Subsets II (input may repeat). Sort, then at each depth skip a value equal to the one
     * just tried.
     *
     * <p>Time O(n * 2^n). Space O(n).
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        subsetsWithDup(a, 0, new ArrayList<>(), out);
        return out;
    }

    private static void subsetsWithDup(int[] a, int start, List<Integer> path, List<List<Integer>> out) {
        out.add(new ArrayList<>(path));
        for (int i = start; i < a.length; i++) {
            if (i > start && a[i] == a[i - 1]) {
                continue;
            }
            path.add(a[i]);
            subsetsWithDup(a, i + 1, path, out);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LC 40 Combination Sum II (each number used once, no duplicate combinations). Same skip
     * rule as Subsets II, plus stop once a number exceeds what remains.
     *
     * <p>Time O(2^n). Space O(n).
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] a = candidates.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        combinationSum2(a, 0, target, new ArrayList<>(), out);
        return out;
    }

    private static void combinationSum2(int[] a, int start, int remaining, List<Integer> path, List<List<Integer>> out) {
        if (remaining == 0) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length && a[i] <= remaining; i++) {
            if (i > start && a[i] == a[i - 1]) {
                continue;
            }
            path.add(a[i]);
            combinationSum2(a, i + 1, remaining - a[i], path, out);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LC 47 Permutations II (input may repeat). Sort; use an equal value only if its previous
     * copy is already in the path, so equal values always appear in one fixed order.
     *
     * <p>Time O(n * n!). Space O(n).
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        permuteUnique(a, new boolean[a.length], new ArrayList<>(), out);
        return out;
    }

    private static void permuteUnique(int[] a, boolean[] used, List<Integer> path, List<List<Integer>> out) {
        if (path.size() == a.length) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (used[i] || (i > 0 && a[i] == a[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            path.add(a[i]);
            permuteUnique(a, used, path, out);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /**
     * LC 22 Generate Parentheses. Add '(' while any remain; add ')' only while it would close an
     * open one.
     *
     * <p>Time O(4^n / sqrt(n)) (the Catalan number). Space O(n).
     */
    public static List<String> generateParenthesis(int n) {
        List<String> out = new ArrayList<>();
        parens(new StringBuilder(), 0, 0, n, out);
        return out;
    }

    private static void parens(StringBuilder sb, int open, int close, int n, List<String> out) {
        if (sb.length() == 2 * n) {
            out.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            parens(sb, open + 1, close, n, out);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            parens(sb, open, close + 1, n, out);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * LC 17 Letter Combinations of a Phone Number. One recursion level per digit, one branch per
     * letter on that key.
     *
     * <p>Time O(4^n * n). Space O(n).
     */
    public static List<String> letterCombinations(String digits) {
        List<String> out = new ArrayList<>();
        if (!digits.isEmpty()) {
            letters(digits, 0, new StringBuilder(), out);
        }
        return out;
    }

    private static void letters(String digits, int i, StringBuilder sb, List<String> out) {
        if (i == digits.length()) {
            out.add(sb.toString());
            return;
        }
        for (char c : KEYS[digits.charAt(i) - '0'].toCharArray()) {
            sb.append(c);
            letters(digits, i + 1, sb, out);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * LC 79 Word Search. DFS from each cell, marking the path so a cell isn't reused.
     *
     * <p>Time O(cells * 3^L). Space O(L).
     */
    public static boolean exist(char[][] board, String word) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (exist(board, word, 0, r, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exist(char[][] b, String word, int i, int r, int c) {
        if (i == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] != word.charAt(i)) {
            return false;
        }
        char saved = b[r][c];
        b[r][c] = '#';
        boolean found = exist(b, word, i + 1, r + 1, c) || exist(b, word, i + 1, r - 1, c)
                || exist(b, word, i + 1, r, c + 1) || exist(b, word, i + 1, r, c - 1);
        b[r][c] = saved;
        return found;
    }

    /**
     * LC 131 Palindrome Partitioning. Choose each palindromic prefix and partition the rest.
     *
     * <p>Time O(n * 2^n). Space O(n).
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> out = new ArrayList<>();
        partition(s, 0, new ArrayList<>(), out);
        return out;
    }

    private static void partition(String s, int start, List<String> path, List<List<String>> out) {
        if (start == s.length()) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (isPalindrome(s, start, end - 1)) {
                path.add(s.substring(start, end));
                partition(s, end, path, out);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * LC 51 N-Queens: every board, as rows of '.' and 'Q'. Same column/diagonal bookkeeping as
     * N-Queens II, keeping each queen's column.
     *
     * <p>Time O(n!). Space O(n).
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> out = new ArrayList<>();
        queens(0, n, new int[n], new boolean[n], new boolean[2 * n], new boolean[2 * n], out);
        return out;
    }

    private static void queens(int row, int n, int[] colOf, boolean[] cols, boolean[] diag, boolean[] anti,
            List<List<String>> out) {
        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int r = 0; r < n; r++) {
                char[] line = new char[n];
                Arrays.fill(line, '.');
                line[colOf[r]] = 'Q';
                board.add(new String(line));
            }
            out.add(board);
            return;
        }
        for (int c = 0; c < n; c++) {
            int d = row - c + n;
            int a = row + c;
            if (cols[c] || diag[d] || anti[a]) {
                continue;
            }
            colOf[row] = c;
            cols[c] = diag[d] = anti[a] = true;
            queens(row + 1, n, colOf, cols, diag, anti, out);
            cols[c] = diag[d] = anti[a] = false;
        }
    }

    /**
     * LC 37 Sudoku Solver, in place. Fill empty cells in order, trying each digit allowed by its
     * row, column and box; undo on a dead end.
     *
     * <p>Time exponential in the worst case, fast on real puzzles. Space O(81).
     */
    public static void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] != '.') {
                    int d = board[r][c] - '0';
                    rows[r][d] = cols[c][d] = boxes[(r / 3) * 3 + c / 3][d] = true;
                }
            }
        }
        sudoku(board, 0, rows, cols, boxes);
    }

    private static boolean sudoku(char[][] b, int cell, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (cell == 81) {
            return true;
        }
        int r = cell / 9;
        int c = cell % 9;
        if (b[r][c] != '.') {
            return sudoku(b, cell + 1, rows, cols, boxes);
        }
        int box = (r / 3) * 3 + c / 3;
        for (int d = 1; d <= 9; d++) {
            if (rows[r][d] || cols[c][d] || boxes[box][d]) {
                continue;
            }
            b[r][c] = (char) ('0' + d);
            rows[r][d] = cols[c][d] = boxes[box][d] = true;
            if (sudoku(b, cell + 1, rows, cols, boxes)) {
                return true;
            }
            rows[r][d] = cols[c][d] = boxes[box][d] = false;
            b[r][c] = '.';
        }
        return false;
    }
}
