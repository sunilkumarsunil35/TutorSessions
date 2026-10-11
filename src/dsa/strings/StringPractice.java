package dsa.strings;

import java.util.ArrayList;
import java.util.List;

/** More string practice: palindrome centres, encoding, parsing and digit arithmetic. */
public final class StringPractice {
    private StringPractice() {}

    /**
     * LC 647 Palindromic Substrings. Expand around each of the 2n - 1 centres (a character or
     * the gap between two).
     *
     * <p>Time O(n^2). Space O(1).
     */
    public static int countSubstrings(String s) {
        int count = 0;
        for (int c = 0; c < 2 * s.length() - 1; c++) {
            int l = c / 2;
            int r = l + c % 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                count++;
                l--;
                r++;
            }
        }
        return count;
    }

    /**
     * LC 271 Encode and Decode Strings. Prefix each string with its length and '#', so any
     * characters (including '#') survive.
     *
     * <p>Time O(total length). Space O(total length).
     */
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    /** Inverse of {@link #encode}. */
    public static List<String> decode(String s) {
        List<String> out = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int hash = s.indexOf('#', i);
            int length = Integer.parseInt(s.substring(i, hash));
            out.add(s.substring(hash + 1, hash + 1 + length));
            i = hash + 1 + length;
        }
        return out;
    }

    /**
     * LC 8 String to Integer (atoi). Skip spaces, read an optional sign, read digits, and clamp
     * to the int range before the multiplication can overflow.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int myAtoi(String s) {
        int i = 0;
        int n = s.length();
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i++) == '-' ? -1 : 1;
        }
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int d = s.charAt(i++) - '0';
            if (result > (Integer.MAX_VALUE - d) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + d;
        }
        return sign * result;
    }

    /**
     * LC 6 Zigzag Conversion. Append each character to its row, bouncing between the top and
     * bottom rows.
     *
     * <p>Time O(n). Space O(n).
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int row = 0;
        int step = 1;
        for (char c : s.toCharArray()) {
            rows[row].append(c);
            if (row == 0) {
                step = 1;
            } else if (row == numRows - 1) {
                step = -1;
            }
            row += step;
        }
        StringBuilder out = new StringBuilder();
        for (StringBuilder r : rows) {
            out.append(r);
        }
        return out.toString();
    }

    /**
     * LC 43 Multiply Strings. Digit i times digit j lands in positions i + j and i + j + 1 of
     * the result, like long multiplication on paper.
     *
     * <p>Time O(m * n). Space O(m + n).
     */
    public static String multiply(String a, String b) {
        int[] pos = new int[a.length() + b.length()];
        for (int i = a.length() - 1; i >= 0; i--) {
            for (int j = b.length() - 1; j >= 0; j--) {
                int sum = (a.charAt(i) - '0') * (b.charAt(j) - '0') + pos[i + j + 1];
                pos[i + j + 1] = sum % 10;
                pos[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int d : pos) {
            if (sb.length() > 0 || d != 0) {
                sb.append(d);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
