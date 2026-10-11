package dsa.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/** More hashing practice: duplicates, counting and one-to-one mappings. */
public final class HashingPractice {
    private HashingPractice() {}

    /**
     * LC 217 Contains Duplicate. Set.add returns false for a value already present.
     *
     * <p>Time O(n). Space O(n).
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            if (!seen.add(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * LC 36 Valid Sudoku. Track which digits each row, column and 3x3 box has used; box index is
     * (r / 3) * 3 + c / 3.
     *
     * <p>Time O(81). Space O(81).
     */
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    continue;
                }
                int d = board[r][c] - '1';
                int b = (r / 3) * 3 + c / 3;
                if (rows[r][d] || cols[c][d] || boxes[b][d]) {
                    return false;
                }
                rows[r][d] = cols[c][d] = boxes[b][d] = true;
            }
        }
        return true;
    }

    /**
     * LC 383 Ransom Note. Count the magazine's letters, then spend them.
     *
     * <p>Time O(n + m). Space O(1).
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * LC 205 Isomorphic Strings. Two characters map to each other exactly when they were last
     * seen at the same position in both strings.
     *
     * <p>Time O(n). Space O(alphabet).
     */
    public static boolean isIsomorphic(String s, String t) {
        int[] lastS = new int[256];
        int[] lastT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (lastS[s.charAt(i)] != lastT[t.charAt(i)]) {
                return false;
            }
            lastS[s.charAt(i)] = lastT[t.charAt(i)] = i + 1;
        }
        return true;
    }

    /**
     * LC 290 Word Pattern. Same last-seen-position idea as isomorphic strings, with words.
     *
     * <p>Time O(n). Space O(n).
     */
    public static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Character, Integer> lastChar = new HashMap<>();
        Map<String, Integer> lastWord = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!Objects.equals(lastChar.put(pattern.charAt(i), i), lastWord.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }
}
