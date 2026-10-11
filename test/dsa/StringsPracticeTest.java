package dsa;

import dsa.strings.StringPractice;
import java.util.List;

final class StringsPracticeTest {
    static void run(Check c) {
        c.eq("countSubstrings", 3, StringPractice.countSubstrings("abc"));
        c.eq("countSubstrings aaa", 6, StringPractice.countSubstrings("aaa"));
        for (List<String> strs : List.of(List.of("lint", "code", "love", "you"), List.of("", "a#b", "12#", "#"), List.<String>of())) {
            c.eq("encode/decode round trip", strs, StringPractice.decode(StringPractice.encode(strs)));
        }
        c.eq("myAtoi", 42, StringPractice.myAtoi("42"));
        c.eq("myAtoi spaces sign zeros", -42, StringPractice.myAtoi("   -042"));
        c.eq("myAtoi trailing letters", 1337, StringPractice.myAtoi("1337c0d3"));
        c.eq("myAtoi leading word", 0, StringPractice.myAtoi("words 987"));
        c.eq("myAtoi clamps low", Integer.MIN_VALUE, StringPractice.myAtoi("-91283472332"));
        c.eq("myAtoi clamps high", Integer.MAX_VALUE, StringPractice.myAtoi("2147483648"));
        c.eq("myAtoi max exactly", Integer.MAX_VALUE, StringPractice.myAtoi("2147483647"));
        c.eq("myAtoi empty", 0, StringPractice.myAtoi(""));
        c.eq("convert 3 rows", "PAHNAPLSIIGYIR", StringPractice.convert("PAYPALISHIRING", 3));
        c.eq("convert 4 rows", "PINALSIGYAHRPI", StringPractice.convert("PAYPALISHIRING", 4));
        c.eq("convert 1 row", "AB", StringPractice.convert("AB", 1));
        c.eq("convert more rows than letters", "AB", StringPractice.convert("AB", 5));
        c.eq("multiply", "56088", StringPractice.multiply("123", "456"));
        c.eq("multiply by zero", "0", StringPractice.multiply("0", "52"));
        c.eq("multiply big", "121932631112635269", StringPractice.multiply("123456789", "987654321"));
    }
}
