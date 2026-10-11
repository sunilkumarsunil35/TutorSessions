package dsa.heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LC 355 Design Twitter. Each user keeps their own tweets (newest first, at most 10 needed); a
 * news feed merges the 10 newest from the user and everyone they follow with a min-heap.
 *
 * <p>postTweet O(1). getNewsFeed O(f * 10 * log 10) for f followees.
 */
public class Twitter {
    private int clock = 0;
    private final Map<Integer, LinkedList<int[]>> tweets = new HashMap<>(); // {time, tweetId}
    private final Map<Integer, Set<Integer>> following = new HashMap<>();

    public void postTweet(int userId, int tweetId) {
        LinkedList<int[]> list = tweets.computeIfAbsent(userId, k -> new LinkedList<>());
        list.addFirst(new int[] {clock++, tweetId});
        if (list.size() > 10) {
            list.removeLast();
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> users = new HashSet<>(following.getOrDefault(userId, Set.of()));
        users.add(userId);
        PriorityQueue<int[]> newest = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        for (int u : users) {
            for (int[] t : tweets.getOrDefault(u, new LinkedList<>())) {
                newest.add(t);
                if (newest.size() > 10) {
                    newest.poll();
                }
            }
        }
        List<Integer> feed = new ArrayList<>();
        while (!newest.isEmpty()) {
            feed.add(0, newest.poll()[1]);
        }
        return feed;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            following.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = following.get(followerId);
        if (set != null) {
            set.remove(followeeId);
        }
    }
}
