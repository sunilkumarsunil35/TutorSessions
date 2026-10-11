package dsa.bits;

/**
 * Math and bit manipulation. Handy facts: x ^ x = 0, x & (x - 1) clears the lowest set bit, and
 * >>> shifts in zeros (unsigned) while >> copies the sign bit.
 */
public final class BitProblems {
    private BitProblems() {}

    /**
     * LC 136 Single Number. XOR cancels every pair, leaving the single value.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int singleNumber(int[] nums) {
        int x = 0;
        for (int n : nums) {
            x ^= n;
        }
        return x;
    }

    /**
     * LC 191 Number of 1 Bits (n as unsigned). Each n &= n - 1 clears one set bit.
     *
     * <p>Time O(set bits). Space O(1).
     */
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

    /**
     * LC 338 Counting Bits for 0..n. bits(i) = bits(i / 2) + the lowest bit of i.
     *
     * <p>Time O(n). Space O(1) besides the output.
     */
    public static int[] countBits(int n) {
        int[] out = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            out[i] = out[i >> 1] + (i & 1);
        }
        return out;
    }

    /**
     * LC 190 Reverse Bits (32-bit unsigned). Shift bits out of n and into the result one at a
     * time.
     *
     * <p>Time O(32). Space O(1).
     */
    public static int reverseBits(int n) {
        int out = 0;
        for (int i = 0; i < 32; i++) {
            out = (out << 1) | (n & 1);
            n >>>= 1;
        }
        return out;
    }

    /**
     * LC 268 Missing Number in 0..n. XOR every index and value; everything present cancels.
     *
     * <p>Time O(n). Space O(1).
     */
    public static int missingNumber(int[] nums) {
        int x = nums.length;
        for (int i = 0; i < nums.length; i++) {
            x ^= i ^ nums[i];
        }
        return x;
    }

    /**
     * LC 9 Palindrome Number without converting to a string. Reverse only the second half of the
     * digits and compare it with the first half.
     *
     * <p>Time O(digits). Space O(1).
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int half = 0;
        while (x > half) {
            half = half * 10 + x % 10;
            x /= 10;
        }
        return x == half || x == half / 10; // half / 10 drops the middle digit of odd lengths
    }

    /**
     * LC 66 Plus One. Add from the right; a 9 becomes 0 and carries. All 9s grow by one digit.
     *
     * <p>Time O(n). Space O(1) unless the array grows.
     */
    public static int[] plusOne(int[] digits) {
        int[] d = digits.clone();
        for (int i = d.length - 1; i >= 0; i--) {
            if (d[i] < 9) {
                d[i]++;
                return d;
            }
            d[i] = 0;
        }
        int[] grown = new int[d.length + 1];
        grown[0] = 1;
        return grown;
    }

    /**
     * LC 202 Happy Number. Repeatedly summing squared digits either reaches 1 or loops; detect
     * the loop with Floyd's fast and slow pointers.
     *
     * <p>Time O(log n) per step. Space O(1).
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = squareDigits(n);
        while (fast != 1 && slow != fast) {
            slow = squareDigits(slow);
            fast = squareDigits(squareDigits(fast));
        }
        return fast == 1;
    }

    private static int squareDigits(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    /**
     * LC 7 Reverse Integer, returning 0 if the result overflows an int. Check before each
     * multiply-by-10.
     *
     * <p>Time O(digits). Space O(1).
     */
    public static int reverse(int x) {
        int out = 0;
        while (x != 0) {
            int d = x % 10;
            if (out > Integer.MAX_VALUE / 10 || (out == Integer.MAX_VALUE / 10 && d > 7)) {
                return 0;
            }
            if (out < Integer.MIN_VALUE / 10 || (out == Integer.MIN_VALUE / 10 && d < -8)) {
                return 0;
            }
            out = out * 10 + d;
            x /= 10;
        }
        return out;
    }

    /**
     * LC 50 Pow(x, n). Fast exponentiation by squaring; n is widened to long so -2^31 can be
     * negated.
     *
     * <p>Time O(log n). Space O(1).
     */
    public static double myPow(double x, int n) {
        long e = n;
        if (e < 0) {
            x = 1 / x;
            e = -e;
        }
        double result = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                result *= x;
            }
            x *= x;
            e >>= 1;
        }
        return result;
    }

    /**
     * LC 204 Count Primes below n. Sieve of Eratosthenes, crossing off multiples from p * p.
     *
     * <p>Time O(n log log n). Space O(n).
     */
    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] composite = new boolean[n];
        int count = 0;
        for (int p = 2; p < n; p++) {
            if (composite[p]) {
                continue;
            }
            count++;
            for (long m = (long) p * p; m < n; m += p) {
                composite[(int) m] = true;
            }
        }
        return count;
    }

    /**
     * LC 371 Sum of Two Integers without + or -. XOR adds without carries; AND shifted left is
     * the carry. Repeat until there is no carry.
     *
     * <p>Time O(32). Space O(1).
     */
    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }
        return a;
    }
}
