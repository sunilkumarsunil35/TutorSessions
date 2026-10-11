package dsa;

import dsa.hashing.HashingPractice;
import dsa.hashing.RandomizedSet;

final class HashingPracticeTest {
    static char[][] board(String... rows) {
        char[][] b = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            b[i] = rows[i].toCharArray();
        }
        return b;
    }

    static final String[] SUDOKU = {
        "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79",
    };

    static void run(Check c) {
        c.isTrue("containsDuplicate", HashingPractice.containsDuplicate(new int[] {1, 2, 3, 1}));
        c.isFalse("containsDuplicate none", HashingPractice.containsDuplicate(new int[] {1, 2, 3, 4}));
        c.isTrue("isValidSudoku", HashingPractice.isValidSudoku(board(SUDOKU)));
        String[] bad = SUDOKU.clone();
        bad[0] = "83..7....";
        c.isFalse("isValidSudoku column and box clash", HashingPractice.isValidSudoku(board(bad)));
        c.isFalse("canConstruct", HashingPractice.canConstruct("aa", "ab"));
        c.isTrue("canConstruct yes", HashingPractice.canConstruct("aa", "aab"));
        c.isTrue("isIsomorphic", HashingPractice.isIsomorphic("egg", "add"));
        c.isFalse("isIsomorphic foo bar", HashingPractice.isIsomorphic("foo", "bar"));
        c.isFalse("isIsomorphic two to one", HashingPractice.isIsomorphic("badc", "baba"));
        c.isTrue("wordPattern", HashingPractice.wordPattern("abba", "dog cat cat dog"));
        c.isFalse("wordPattern fish", HashingPractice.wordPattern("abba", "dog cat cat fish"));
        c.isFalse("wordPattern aaaa", HashingPractice.wordPattern("aaaa", "dog cat cat dog"));
        c.isFalse("wordPattern one word for two letters", HashingPractice.wordPattern("abba", "dog dog dog dog"));
        c.isFalse("wordPattern length", HashingPractice.wordPattern("aaa", "aa aa aa aa"));

        RandomizedSet set = new RandomizedSet();
        c.isTrue("RandomizedSet insert", set.insert(1));
        c.isFalse("RandomizedSet remove missing", set.remove(2));
        c.isTrue("RandomizedSet insert 2", set.insert(2));
        int r = set.getRandom();
        c.isTrue("RandomizedSet getRandom in set", r == 1 || r == 2);
        c.isTrue("RandomizedSet remove", set.remove(1));
        c.isFalse("RandomizedSet insert duplicate", set.insert(2));
        c.eq("RandomizedSet only value", 2, set.getRandom());
        c.isTrue("RandomizedSet remove last", set.remove(2));
        c.isTrue("RandomizedSet reinsert", set.insert(3));
        c.eq("RandomizedSet after reuse", 3, set.getRandom());
    }
}
