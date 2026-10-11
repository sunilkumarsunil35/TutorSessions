package dsa;

import dsa.backtracking.BacktrackingPractice;
import java.util.List;

final class BacktrackingPracticeTest {
    static void run(Check c) {
        c.eq("combine", List.of(List.of(1, 2), List.of(1, 3), List.of(1, 4), List.of(2, 3), List.of(2, 4), List.of(3, 4)),
                BacktrackingPractice.combine(4, 2));
        c.eq("combine all", List.of(List.of(1)), BacktrackingPractice.combine(1, 1));
        c.eq("subsetsWithDup", List.of(List.of(), List.of(1), List.of(1, 2), List.of(1, 2, 2), List.of(2), List.of(2, 2)),
                BacktrackingPractice.subsetsWithDup(new int[] {2, 1, 2}));
        c.eq("combinationSum2", List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6)),
                BacktrackingPractice.combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
        c.eq("combinationSum2 2", List.of(List.of(1, 2, 2), List.of(5)),
                BacktrackingPractice.combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
        c.eq("permuteUnique", List.of(List.of(1, 1, 2), List.of(1, 2, 1), List.of(2, 1, 1)),
                BacktrackingPractice.permuteUnique(new int[] {1, 2, 1}));
        c.eq("generateParenthesis", List.of("((()))", "(()())", "(())()", "()(())", "()()()"),
                BacktrackingPractice.generateParenthesis(3));
        c.eq("generateParenthesis 1", List.of("()"), BacktrackingPractice.generateParenthesis(1));
        c.eq("letterCombinations", List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
                BacktrackingPractice.letterCombinations("23"));
        c.eq("letterCombinations empty", List.of(), BacktrackingPractice.letterCombinations(""));
        c.eq("letterCombinations 7 has 4", 4, BacktrackingPractice.letterCombinations("7").size());
        char[][] board = {"ABCE".toCharArray(), "SFCS".toCharArray(), "ADEE".toCharArray()};
        c.isTrue("exist", BacktrackingPractice.exist(board, "ABCCED"));
        c.isTrue("exist 2", BacktrackingPractice.exist(board, "SEE"));
        c.isFalse("exist reuse", BacktrackingPractice.exist(board, "ABCB"));
        c.eq("exist restores board", "ABCE", new String(board[0]));
        c.eq("partition", List.of(List.of("a", "a", "b"), List.of("aa", "b")), BacktrackingPractice.partition("aab"));
        c.eq("partition single", List.of(List.of("a")), BacktrackingPractice.partition("a"));
        c.eq("solveNQueens 4", List.of(List.of(".Q..", "...Q", "Q...", "..Q."), List.of("..Q.", "Q...", "...Q", ".Q..")),
                BacktrackingPractice.solveNQueens(4));
        c.eq("solveNQueens 1", List.of(List.of("Q")), BacktrackingPractice.solveNQueens(1));
        c.eq("solveNQueens 8 count", 92, BacktrackingPractice.solveNQueens(8).size());

        char[][] sudoku = HashingPracticeTest.board(HashingPracticeTest.SUDOKU);
        BacktrackingPractice.solveSudoku(sudoku);
        c.eq("solveSudoku", HashingPracticeTest.board(
                "534678912", "672195348", "198342567", "859761423", "426853791",
                "713924856", "961537284", "287419635", "345286179"), sudoku);
    }
}
