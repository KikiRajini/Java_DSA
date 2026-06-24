package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result; // Return empty list, not null

        // Use a Queue for Breadth-First Search (BFS)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of nodes at the current level
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll(); // Remove from the front
                currentLevel.add(node.val);

                // Add children to the back of the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(currentLevel);
        }

        return result;
    }
}
