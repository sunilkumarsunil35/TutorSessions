package dsa;

import static dsa.common.TreeNode.fromLevelOrder;

import dsa.common.TreeNode;
import dsa.trees.Codec;
import dsa.trees.TreePractice;
import dsa.trees.TreeProblems;
import dsa.trees.WordDictionary;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class TreesPracticeTest {
    static void run(Check c) {
        c.eq("invertTree", List.of(List.of(4), List.of(7, 2), List.of(9, 6, 3, 1)),
                TreeProblems.levelOrder(TreePractice.invertTree(fromLevelOrder(4, 2, 7, 1, 3, 6, 9))));
        c.isTrue("isSameTree", TreePractice.isSameTree(fromLevelOrder(1, 2, 3), fromLevelOrder(1, 2, 3)));
        c.isFalse("isSameTree shape", TreePractice.isSameTree(fromLevelOrder(1, 2), fromLevelOrder(1, null, 2)));
        c.eq("diameterOfBinaryTree", 3, TreePractice.diameterOfBinaryTree(fromLevelOrder(1, 2, 3, 4, 5)));
        c.eq("diameter not through root", 4,
                TreePractice.diameterOfBinaryTree(fromLevelOrder(1, 2, null, 3, 4, 5, null, null, 6)));
        c.isTrue("isBalanced", TreePractice.isBalanced(fromLevelOrder(3, 9, 20, null, null, 15, 7)));
        c.isFalse("isBalanced no", TreePractice.isBalanced(fromLevelOrder(1, 2, 2, 3, 3, null, null, 4, 4)));
        c.isTrue("isSubtree", TreePractice.isSubtree(fromLevelOrder(3, 4, 5, 1, 2), fromLevelOrder(4, 1, 2)));
        c.isFalse("isSubtree extra node",
                TreePractice.isSubtree(fromLevelOrder(3, 4, 5, 1, 2, null, null, null, null, 0), fromLevelOrder(4, 1, 2)));
        c.isTrue("hasPathSum", TreePractice.hasPathSum(fromLevelOrder(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1), 22));
        c.isFalse("hasPathSum no", TreePractice.hasPathSum(fromLevelOrder(1, 2, 3), 5));
        c.isFalse("hasPathSum needs a leaf", TreePractice.hasPathSum(fromLevelOrder(1, 2), 1));

        TreeNode bst = fromLevelOrder(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        c.eq("lcaBST split", 6, TreePractice.lowestCommonAncestorBST(bst, TreeNode.find(bst, 2), TreeNode.find(bst, 8)).val);
        c.eq("lcaBST ancestor", 2, TreePractice.lowestCommonAncestorBST(bst, TreeNode.find(bst, 2), TreeNode.find(bst, 4)).val);
        c.eq("rightSideView", List.of(1, 3, 4), TreePractice.rightSideView(fromLevelOrder(1, 2, 3, null, 5, null, 4)));
        c.eq("rightSideView left deeper", List.of(1, 3, 4), TreePractice.rightSideView(fromLevelOrder(1, 2, 3, 4)));
        c.eq("goodNodes", 4, TreePractice.goodNodes(fromLevelOrder(3, 1, 4, 3, null, 1, 5)));
        c.eq("goodNodes 2", 3, TreePractice.goodNodes(fromLevelOrder(3, 3, null, 4, 2)));
        c.eq("kthSmallest", 1, TreePractice.kthSmallest(fromLevelOrder(3, 1, 4, null, 2), 1));
        c.eq("kthSmallest 3", 3, TreePractice.kthSmallest(fromLevelOrder(5, 3, 6, 2, 4, null, null, 1), 3));
        c.eq("buildTree", List.of(List.of(3), List.of(9, 20), List.of(15, 7)),
                TreeProblems.levelOrder(TreePractice.buildTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7})));
        c.eq("maxPathSum", 6, TreePractice.maxPathSum(fromLevelOrder(1, 2, 3)));
        c.eq("maxPathSum bends", 42, TreePractice.maxPathSum(fromLevelOrder(-10, 9, 20, null, null, 15, 7)));
        c.eq("maxPathSum negative", -3, TreePractice.maxPathSum(fromLevelOrder(-3)));

        Codec codec = new Codec();
        TreeNode t = fromLevelOrder(1, 2, 3, null, null, 4, 5);
        c.eq("Codec round trip", TreeProblems.levelOrder(t), TreeProblems.levelOrder(codec.deserialize(codec.serialize(t))));
        c.isTrue("Codec same shape", TreePractice.isSameTree(t, codec.deserialize(codec.serialize(t))));
        c.isTrue("Codec empty", codec.deserialize(codec.serialize(null)) == null);
        TreeNode neg = fromLevelOrder(-1, null, -20);
        c.isTrue("Codec negatives", TreePractice.isSameTree(neg, codec.deserialize(codec.serialize(neg))));

        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        c.isFalse("WordDictionary pad", dict.search("pad"));
        c.isTrue("WordDictionary bad", dict.search("bad"));
        c.isTrue("WordDictionary .ad", dict.search(".ad"));
        c.isTrue("WordDictionary b..", dict.search("b.."));
        c.isFalse("WordDictionary too long", dict.search("b..."));
        c.isFalse("WordDictionary prefix", dict.search("ba"));

        char[][] board = {"oaan".toCharArray(), "etae".toCharArray(), "ihkr".toCharArray(), "iflv".toCharArray()};
        List<String> found = new ArrayList<>(TreePractice.findWords(board, new String[] {"oath", "pea", "eat", "rain"}));
        Collections.sort(found);
        c.eq("findWords", List.of("eat", "oath"), found);
        char[][] small = {"ab".toCharArray(), "cd".toCharArray()};
        c.eq("findWords none", List.of(), TreePractice.findWords(small, new String[] {"abcb"}));
        c.eq("findWords board restored", "ab", new String(small[0]));
    }
}
