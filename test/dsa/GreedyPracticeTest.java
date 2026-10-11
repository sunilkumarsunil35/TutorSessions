package dsa;

import dsa.greedy.GreedyPractice;
import java.util.List;

final class GreedyPracticeTest {
    static void run(Check c) {
        c.isFalse("canAttendMeetings", GreedyPractice.canAttendMeetings(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
        c.isTrue("canAttendMeetings yes", GreedyPractice.canAttendMeetings(new int[][] {{7, 10}, {2, 4}}));
        c.isTrue("canAttendMeetings back to back", GreedyPractice.canAttendMeetings(new int[][] {{1, 5}, {5, 8}}));
        c.eq("minMeetingRooms", 2, GreedyPractice.minMeetingRooms(new int[][] {{0, 30}, {5, 10}, {15, 20}}));
        c.eq("minMeetingRooms one", 1, GreedyPractice.minMeetingRooms(new int[][] {{7, 10}, {2, 4}}));
        c.eq("minMeetingRooms back to back", 1, GreedyPractice.minMeetingRooms(new int[][] {{1, 5}, {5, 8}}));
        c.eq("minMeetingRooms all overlap", 3, GreedyPractice.minMeetingRooms(new int[][] {{1, 10}, {2, 9}, {3, 8}}));
        c.eq("canCompleteCircuit", 3, GreedyPractice.canCompleteCircuit(new int[] {1, 2, 3, 4, 5}, new int[] {3, 4, 5, 1, 2}));
        c.eq("canCompleteCircuit impossible", -1, GreedyPractice.canCompleteCircuit(new int[] {2, 3, 4}, new int[] {3, 4, 3}));
        c.isTrue("isNStraightHand", GreedyPractice.isNStraightHand(new int[] {1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        c.isFalse("isNStraightHand no", GreedyPractice.isNStraightHand(new int[] {1, 2, 3, 4, 5}, 4));
        c.isTrue("isNStraightHand two runs", GreedyPractice.isNStraightHand(new int[] {1, 2, 4, 5}, 2));
        c.isFalse("isNStraightHand gap", GreedyPractice.isNStraightHand(new int[] {1, 2, 4, 6}, 2));
        c.eq("partitionLabels", List.of(9, 7, 8), GreedyPractice.partitionLabels("ababcbacadefegdehijhklij"));
        c.eq("partitionLabels one", List.of(10), GreedyPractice.partitionLabels("eccbbbbdec"));
        c.isTrue("checkValidString", GreedyPractice.checkValidString("()"));
        c.isTrue("checkValidString star open", GreedyPractice.checkValidString("(*)"));
        c.isTrue("checkValidString star close", GreedyPractice.checkValidString("(*))"));
        c.isFalse("checkValidString order", GreedyPractice.checkValidString(")("));
        c.isFalse("checkValidString too many open", GreedyPractice.checkValidString("(((*)"));
        c.isTrue("mergeTriplets", GreedyPractice.mergeTriplets(new int[][] {{2, 5, 3}, {1, 8, 4}, {1, 7, 5}}, new int[] {2, 7, 5}));
        c.isFalse("mergeTriplets no", GreedyPractice.mergeTriplets(new int[][] {{3, 4, 5}, {4, 5, 6}}, new int[] {3, 2, 5}));
        c.eq("insert", new int[][] {{1, 5}, {6, 9}}, GreedyPractice.insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5}));
        c.eq("insert spanning", new int[][] {{1, 2}, {3, 10}, {12, 16}},
                GreedyPractice.insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8}));
        c.eq("insert into empty", new int[][] {{5, 7}}, GreedyPractice.insert(new int[][] {}, new int[] {5, 7}));
        c.eq("insert at end", new int[][] {{1, 5}, {6, 8}}, GreedyPractice.insert(new int[][] {{1, 5}}, new int[] {6, 8}));
        c.eq("findMinArrowShots", 2, GreedyPractice.findMinArrowShots(new int[][] {{10, 16}, {2, 8}, {1, 6}, {7, 12}}));
        c.eq("findMinArrowShots touching", 2, GreedyPractice.findMinArrowShots(new int[][] {{1, 2}, {2, 3}, {3, 4}, {4, 5}}));
        c.eq("findMinArrowShots extremes", 2,
                GreedyPractice.findMinArrowShots(new int[][] {{-2147483646, -2147483645}, {2147483646, 2147483647}}));
        c.eq("candy", 5, GreedyPractice.candy(new int[] {1, 0, 2}));
        c.eq("candy equal neighbours", 4, GreedyPractice.candy(new int[] {1, 2, 2}));
        c.eq("candy peaks", 7, GreedyPractice.candy(new int[] {1, 3, 2, 2, 1}));
        c.eq("minInterval", new int[] {3, 3, 1, 4},
                GreedyPractice.minInterval(new int[][] {{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[] {2, 3, 4, 5}));
        c.eq("minInterval 2", new int[] {2, -1, 4, 6},
                GreedyPractice.minInterval(new int[][] {{2, 3}, {2, 5}, {1, 8}, {20, 25}}, new int[] {2, 19, 5, 22}));
    }
}
