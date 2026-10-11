package dsa;

import dsa.heaps.HeapPractice;
import dsa.heaps.KthLargest;
import dsa.heaps.Twitter;
import java.util.Arrays;
import java.util.List;

final class HeapsPracticeTest {
    static void run(Check c) {
        KthLargest kth = new KthLargest(3, new int[] {4, 5, 8, 2});
        c.eq("KthLargest add 3", 4, kth.add(3));
        c.eq("KthLargest add 5", 5, kth.add(5));
        c.eq("KthLargest add 10", 5, kth.add(10));
        c.eq("KthLargest add 9", 8, kth.add(9));
        c.eq("KthLargest add 4", 8, kth.add(4));
        KthLargest fromEmpty = new KthLargest(1, new int[] {});
        c.eq("KthLargest from empty", -3, fromEmpty.add(-3));

        c.eq("lastStoneWeight", 1, HeapPractice.lastStoneWeight(new int[] {2, 7, 4, 1, 8, 1}));
        c.eq("lastStoneWeight none left", 0, HeapPractice.lastStoneWeight(new int[] {2, 2}));
        c.eq("kClosest", new int[][] {{-2, 2}}, HeapPractice.kClosest(new int[][] {{1, 3}, {-2, 2}}, 1));
        int[][] two = HeapPractice.kClosest(new int[][] {{3, 3}, {5, -1}, {-2, 4}}, 2);
        Arrays.sort(two, (a, b) -> Integer.compare(a[0], b[0]));
        c.eq("kClosest 2", new int[][] {{-2, 4}, {3, 3}}, two);
        char[] tasks = "AAABBB".toCharArray();
        c.eq("leastInterval", 8, HeapPractice.leastInterval(tasks, 2));
        c.eq("leastInterval no cooldown", 6, HeapPractice.leastInterval(tasks, 0));
        c.eq("leastInterval many kinds", 6, HeapPractice.leastInterval("ACABDB".toCharArray(), 1));
        c.eq("leastInterval idles", 16, HeapPractice.leastInterval("AAAAAABCDEFG".toCharArray(), 2));
        for (String s : List.of("aab", "aaabbc", "vvvlo", "abc", "a")) {
            c.isTrue("reorganizeString valid " + s, validReorganized(s, HeapPractice.reorganizeString(s)));
        }
        c.eq("reorganizeString impossible", "", HeapPractice.reorganizeString("aaab"));
        c.eq("kthSmallest matrix", 13, HeapPractice.kthSmallest(new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        c.eq("kthSmallest matrix first", 1, HeapPractice.kthSmallest(new int[][] {{1, 2}, {1, 3}}, 1));

        Twitter tw = new Twitter();
        tw.postTweet(1, 5);
        c.eq("Twitter own feed", List.of(5), tw.getNewsFeed(1));
        tw.follow(1, 2);
        tw.postTweet(2, 6);
        c.eq("Twitter follow", List.of(6, 5), tw.getNewsFeed(1));
        tw.unfollow(1, 2);
        c.eq("Twitter unfollow", List.of(5), tw.getNewsFeed(1));
        for (int i = 0; i < 12; i++) {
            tw.postTweet(3, 100 + i);
        }
        tw.follow(1, 3);
        List<Integer> feed = tw.getNewsFeed(1);
        c.eq("Twitter feed capped at 10", 10, feed.size());
        c.eq("Twitter newest first", 111, feed.get(0));
        tw.follow(4, 4);
        c.eq("Twitter self follow ignored", List.of(), tw.getNewsFeed(4));
    }

    private static boolean validReorganized(String in, String out) {
        if (out.length() != in.length()) {
            return false;
        }
        char[] a = in.toCharArray();
        char[] b = out.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        if (!Arrays.equals(a, b)) {
            return false;
        }
        for (int i = 1; i < out.length(); i++) {
            if (out.charAt(i) == out.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
