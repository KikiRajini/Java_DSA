---

## 1. BFS vs. DFS: Core Differences

| Feature | BFS (Breadth-First Search) | DFS (Depth-First Search) |
| --- | --- | --- |
| **Strategy** | Level-by-level (Horizontal) | Branch-by-branch (Vertical) |
| **Data Structure** | **Queue** (FIFO) | **Stack** (LIFO) or Recursion |
| **Best For** | Shortest path, Level-order views | Pathfinding, Exhaustive search |
| **Space Complexity** | $O(w)$ (Max Width) | $O(h)$ (Max Height) |

---

## 2. Coding Approaches

### Breadth-First Search (Iterative)

BFS is almost always implemented iteratively using a `Queue`.

```java
public void bfs(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
        TreeNode current = queue.poll();
        // Process current node...
        if (current.left != null) queue.add(current.left);
        if (current.right != null) queue.add(current.right);
    }
}

```

---

### Depth-First Search (Two Ways)

#### Approach A: Recursive (Most Common)

```java
public void dfsRecursive(TreeNode root) {
    if (root == null) return;
    // Process node here (Pre-order)
    dfsRecursive(root.left);
    dfsRecursive(root.right);
}

```

#### Approach B: Iterative (Using a Stack)

```java
public void dfsIterative(TreeNode root) {
    if (root == null) return;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode current = stack.pop();
        // Process node...
        // Push Right before Left so Left is processed first
        if (current.right != null) stack.push(current.right);
        if (current.left != null) stack.push(current.left);
    }
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

### D. Check if Two Trees are Identical

* **Logic:** Check if current nodes match, then recursively check left-left and right-right.

### E. Balanced Binary Tree

* **Logic:** A tree is height-balanced if the depth of the two subtrees of every node never differs by more than 1.

---

## Summary for Documentation

* **BFS** is a "Search Party" walking in a line across a field.
* **DFS** is a "Diver" going to the bottom of the ocean before coming up for air.
* **Recursion** is the "Natural Language" of trees because trees are recursive data structures (every child is the root of its own smaller tree).

