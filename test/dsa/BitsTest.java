package dsa;

import dsa.bits.BitProblems;

final class BitsTest {
    static void run(Check c) {
        c.eq("singleNumber", 4, BitProblems.singleNumber(new int[] {4, 1, 2, 1, 2}));
        c.eq("hammingWeight", 3, BitProblems.hammingWeight(11));
        c.eq("hammingWeight negative", 31, BitProblems.hammingWeight(-3));
        c.eq("countBits", new int[] {0, 1, 1, 2, 1, 2}, BitProblems.countBits(5));
        c.eq("reverseBits", 964176192, BitProblems.reverseBits(43261596));
        c.eq("reverseBits sign bit", -1073741825, BitProblems.reverseBits(-3));
        c.eq("missingNumber", 2, BitProblems.missingNumber(new int[] {3, 0, 1}));
        c.eq("missingNumber 2", 8, BitProblems.missingNumber(new int[] {9, 6, 4, 2, 3, 5, 7, 0, 1}));
        c.eq("missingNumber last", 2, BitProblems.missingNumber(new int[] {0, 1}));
        c.isTrue("isPalindrome 121", BitProblems.isPalindrome(121));
        c.isTrue("isPalindrome 1221", BitProblems.isPalindrome(1221));
        c.isFalse("isPalindrome negative", BitProblems.isPalindrome(-121));
        c.isFalse("isPalindrome trailing zero", BitProblems.isPalindrome(10));
        c.isTrue("isPalindrome zero", BitProblems.isPalindrome(0));
        c.eq("plusOne", new int[] {1, 3, 0}, BitProblems.plusOne(new int[] {1, 2, 9}));
        c.eq("plusOne grows", new int[] {1, 0, 0}, BitProblems.plusOne(new int[] {9, 9}));
        c.isTrue("isHappy", BitProblems.isHappy(19));
        c.isFalse("isHappy no", BitProblems.isHappy(2));
        c.isTrue("isHappy one", BitProblems.isHappy(1));
        c.eq("reverse", 321, BitProblems.reverse(123));
        c.eq("reverse negative", -321, BitProblems.reverse(-123));
        c.eq("reverse trailing zero", 21, BitProblems.reverse(120));
        c.eq("reverse overflow", 0, BitProblems.reverse(1534236469));
        c.eq("reverse min int", 0, BitProblems.reverse(Integer.MIN_VALUE));
        c.eq("myPow", 1024.0, BitProblems.myPow(2.0, 10));
        c.eq("myPow negative", 0.25, BitProblems.myPow(2.0, -2));
        c.eq("myPow min int", 1.0, BitProblems.myPow(1.0, Integer.MIN_VALUE));
        c.eq("myPow zero exponent", 1.0, BitProblems.myPow(5.0, 0));
        c.eq("countPrimes", 4, BitProblems.countPrimes(10));
        c.eq("countPrimes small", 0, BitProblems.countPrimes(2));
        c.eq("countPrimes 3", 1, BitProblems.countPrimes(3));
        c.eq("countPrimes 100", 25, BitProblems.countPrimes(100));
        c.eq("getSum", 3, BitProblems.getSum(1, 2));
        c.eq("getSum negative", 1, BitProblems.getSum(-2, 3));
        c.eq("getSum both negative", -5, BitProblems.getSum(-2, -3));
    }
}
