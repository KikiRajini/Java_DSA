---

## 1. BFS vs. DFS: Core Differences

| Feature | BFS (Breadth-First Search) | DFS (Depth-First Search) |
| --- | --- | --- |
| **Strategy** | Level-by-level (Horizontal) | Branch-by-branch (Vertical) |
| **Data Structure** | **Queue** (FIFO) | **stack** (LIFO) or Recursion |
| **Best For** | Shortest path, Level-order views | Pathfinding, Exhaustive search |
| **Space Complexity** | $O(w)$ (Max Width) | $O(h)$ (Max Height) |

---

## 2. Coding Approaches

### Breadth-First Search (Iterative)

BFS is almost always implemented iteratively using a `Queue`.

```java


class Solution {
    // Standard LeetCode TreeNode definition
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // Edge Case Safety Guard
        if (root == null) return result;

        // The Engine Line: First-In, First-Out (FIFO)
        Queue<TreeNode> queue = new LinkedList<>();

        // Initialize: Load the starting node (Level 0)
        queue.add(root);

        // OUTER LOOP: Processes Level-by-Level
        while (!queue.isEmpty()) {
            // Snapshot the exact number of elements on THIS current level
            int levelSize = queue.size();
            List<Integer> currentLevelData = new ArrayList<>();

            // INNER LOOP: Process all elements belonging to this current level ONLY
            for (int i = 0; i < levelSize; i++) {
                // Take the front node out of the queue line
                TreeNode currentNode = queue.poll();

                // Collect its value
                currentLevelData.add(currentNode.val);

                // Load the next level's children into the back of the queue
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            // Once the level size loop finishes, the entire current row is collected
            result.add(currentLevelData);
        }

        return result;
    }
}

```

---

### Depth-First Search (Two Ways)

#### Approach A: Recursive (Most Common)

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    // Standard LeetCode TreeNode definition
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Launch the recursive DFS tracking rocket
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        // RULE 1: THE BASE CASE (The Dead End Guard)
        // If we fall off the edge of a leaf node into a null space, turn back immediately.
        if (node == null) {
            return;
        }

        // 1. CHOOSE: Process the current node we are standing on
        result.add(node.val);

        // 2. EXPLORE DEEP LEFT: Dive completely down the left subtree
        dfs(node.left, result);

        // 3. EXPLORE DEEP RIGHT: Dive completely down the right subtree
        dfs(node.right, result);

        // 4. BACKTRACK: Automatically occurs when the method frame finishes executing 
        // and returns control to the parent caller up the stack frame.
    }
}
```

#### Approach B: Iterative (Using a stack)

```java
// Iterative Pre-Order Tree DFS
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode current = stack.pop();
        result.add(current.val);

        // Right child is pushed FIRST so that Left child is popped and processed first (LIFO)
        if (current.right != null) stack.push(current.right);
        if (current.left != null) stack.push(current.left);
    }
    return result;
}

```

---

## 3. The Three Traversal Patterns (DFS)

These patterns refer to **when** you process the current node relative to its children.

1. **Pre-Order (Node, Left, Right)**
* **Use case:** Exporting a tree structure to a file so it can be rebuilt exactly.


2. **In-Order (Left, Node, Right)**
* **Use case:** Retrieving elements from a **Binary Search Tree (BST)** in non-decreasing sorted order.


3. **Post-Order (Left, Right, Node)**
* **Use case:** Deleting a tree (you must delete children before the parent) or calculating folder sizes in a file system.



---

## 4. Common Interview Problems & Formulae

### A. Count Total Number of Nodes

* **Logic:** `1 + count(left) + count(right)`
* **Code:**
```java
public int countNodes(TreeNode root) {
    if (root == null) return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
}

```



### B. Find Maximum Depth (Height)

* **Logic:** `1 + Max(height(left), height(right))`
* **Code:**
```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

```



### C. Tree Maximum/Minimum

* **Logic:** Compare `root.val`, `findMax(left)`, and `findMax(right)`.

### D. Check if Two trees are Identical

* **Logic:** Check if current nodes match, then recursively check left-left and right-right.

### E. Balanced Binary Tree

* **Logic:** A tree is height-balanced if the depth of the two subtrees of every node never differs by more than 1.

---

## Summary for Documentation

* **BFS** is a "Search Party" walking in a line across a field.
* **DFS** is a "Diver" going to the bottom of the ocean before coming up for air.
* **Recursion** is the "Natural Language" of trees because trees are recursive data structures (every child is the root of its own smaller tree).

