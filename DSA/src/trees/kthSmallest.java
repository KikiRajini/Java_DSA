package trees;

import java.util.Stack;

public class kthSmallest {
    // Global trackers to share state across recursive stack frames
    private int count = 0;
    private int result = 0;
    public int kthSmallest(TreeNode root, int k) {
        dfsInOrder(root, k);
        return result;
    }

    private void dfsInOrder(TreeNode node, int k) {
        // RULE 1: THE BASE CASE & EARLY PRUNING
        // Stop if we hit a null node OR if we already found our answer downstream
        if (node == null || count >= k) {
            return;
        }

        // 1. EXPLORE LEFT: Go as deep left as possible first (to the smallest numbers)
        dfsInOrder(node.left, k);

        // 2. CHOOSE / PROCESS PARENT: We are visiting the node now!
        count++; // Increment our counter because this is the next smallest number
        if (count == k) {
            result = node.val; // We found the Kth smallest element!
            return; // Backtrack immediately
        }

        // 3. EXPLORE RIGHT: Only check larger numbers if we haven't reached K yet
        dfsInOrder(node.right, k);
    }
}
