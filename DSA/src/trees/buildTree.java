package trees;

public class buildTree {
    int preIdx = 0;
    int inIdx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, null);
    }

    private TreeNode dfs(int[] preorder, int[] inorder,Integer limit){
        if(preIdx>=preorder.length){return null;}//completed tree
        if(limit!=null && inorder[inIdx]==limit){inIdx++; return null;}//subtree completed
        TreeNode root = new TreeNode(preorder[preIdx]);
        preIdx++;
        root.left = dfs(preorder, inorder,root.val);
        root.right = dfs(preorder, inorder,limit);
        return root;
    }
}

/*


### Implementation 1: The "Divide and Conquer" Boundary Split (`HashMap`)

**Core Logic:** This approach treats the `inorder` array as a physical workspace window bounded by `left` and `right` indices. The `preorder` stream provides the nodes in birth order. When a root node is pulled from `preorder`, its location (`mid`) inside `inorder` acts as a spatial cleaver, defining two isolated sub-windows for the subsequent recursive calls.

```java
import java.util.HashMap;
import java.util.Map;

class SolutionHashMap {
    // =========================================================================
    // STATE VARIABLES
    // =========================================================================

    // Global iterator pointer tracking our linear progression through the preorder stream.
    // It moves left-to-right strictly as nodes are instantiated.
    private int preorderIndex = 0;

    // Lookup table cache maps [Node Value -> Inorder Array Index].
    // This allows us to find the "cleaver split point" in O(1) time instead of
    // running an O(N) linear scan at every single recursive frame.
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    // =========================================================================
    // MAIN ENTRY ENGINE
    // =========================================================================
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Step 1: Pre-populate the indexing map.
        // This gives us instantaneous geographical coordinates of nodes inside the inorder sequence.
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Step 2: Fire up the DFS builder covering the full initial window boundary (0 to N-1).
        return dfs(preorder, 0, inorder.length - 1);
    }

    // =========================================================================
    // RECURSIVE DFS ENGINE
    // =========================================================================
    private TreeNode dfs(int[] preorder, int left, int right) {
        // BASE CASE / GUARD: Window Collision Check
        // If the 'left' index boundary passes the 'right' index boundary, it means
        // the available window space for this subtree has collapsed to size zero.
        // Therefore, this branch is empty and we return null.
        if (left > right) {
            return null;
        }

        // 1. CHOOSE (Node Birth)
        // By structural definition, the current element pointed to by preorderIndex
        // is guaranteed to be the absolute root node of the current subtree.
        int rootValue = preorder[preorderIndex];
        TreeNode root = new TreeNode(rootValue);

        // Increment the birth stream index so the next sub-room handles the next child.
        preorderIndex++;

        // 2. LOCATE THE GEOGRAPHICAL CLEAVER
        // Fetch the exact index location of this root value within the inorder list.
        int mid = inorderMap.get(rootValue);

        // 3. EXPLORE LEFT SUBTREE
        // All nodes belonging to the left side of this root sit strictly between
        // the current left boundary and right before the 'mid' index element.
        root.left = dfs(preorder, left, mid - 1);

        // 4. EXPLORE RIGHT SUBTREE
        // All nodes belonging to the right side of this root sit strictly between
        // one element past the 'mid' index up to the current right boundary.
        root.right = dfs(preorder, mid + 1, right);

        // Return the fully wired local node back up to its respective parent room
        return root;
    }
}

```

---

### Implementation 2: The "Checklist Validation" Method (`Limit` Optimization)

**Core Logic:** This approach treats the two arrays as synchronized real-time data streams using two independent pointer counters. `preorder` dictates when a node is **born**, while `inorder` dictates exactly when a node **finishes building its left-side architecture**. By passing down a dynamic `limit` value, the parent node establishes a structural boundary wall that tells the recursive child engine exactly when to halt left-side production.

```java
class SolutionLimit {
    // =========================================================================
    // STATE VARIABLES
    // =========================================================================

    // Monotonically increasing pointer tracking the next node to be born from the preorder stream.
    private int preIdx = 0;

    // Monotonically increasing pointer tracking our position along the inorder checklist.
    private int inIdx = 0;

    // =========================================================================
    // MAIN ENTRY ENGINE
    // =========================================================================
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Launch the builder using an impossible structural value (Integer.MAX_VALUE)
        // as the initial root boundary wall (Infinity).
        return dfs(preorder, inorder, Integer.MAX_VALUE);
    }

    // =========================================================================
    // RECURSIVE DFS ENGINE
    // =========================================================================
    private TreeNode dfs(int[] preorder, int[] inorder, int limit) {
        // BASE CASE 1: Global Stream Exhaustion
        // If we have successfully instantiated every single node present in the
        // preorder stream, the execution is entirely done. Stop immediately.
        if (preIdx >= preorder.length) {
            return null;
        }

        // BASE CASE 2: The Checklist Limit Boundary Guard
        // Check if the next expected target value on our inorder checklist matches
        // the limit wall imposed by our current recursive parent room.
        // If true, it means the current subtree has finished building its left architecture.
        if (inorder[inIdx] == limit) {
            inIdx++; // Tick off/consume this checklist item since it is now verified
            return null; // Collapse this execution frame and return back to the parent
        }

        // 1. CHOOSE (Node Birth)
        // Instantiate the local root node using the current active preorder element.
        // Pre-increment ensures the next execution steps down the line automatically.
        TreeNode root = new TreeNode(preorder[preIdx++]);

        // 2. EXPLORE LEFT BRANCH
        // Build out the left subtree structures. Crucially, the limit constraint passed down
        // to this room is the current root's own value. The left branch will continuously
        // build until it reads an element in 'inorder' that matches this root value.
        root.left = dfs(preorder, inorder, root.val);

        // 3. EXPLORE RIGHT BRANCH
        // Once the left side finishes and collapses, we shift to building the right subtree.
        // The right branch inherits the exact same global limit boundary that this
        // current room was restricted by.
        root.right = dfs(preorder, inorder, limit);

        // Return the completed wired node object back to the caller frame
        return root;
    }
}

```

---

### 📚 Core Architectural Summary for Documentation

| Feature Detail | `HashMap` Boundary Split Approach | Pointer `Limit` Validation Approach |
| --- | --- | --- |
| **Index Window Management** | Tracks explicit `[left, right]` windows representing physical ranges inside the array. | Uses no spatial boundaries; relies on stream pointers `preIdx` and `inIdx`. |
| **Subtree Slicing Signal** | Uses the value of `mid` found in the `HashMap` to split arrays mathematically. | Uses a dynamic `limit` integer variable representing a structural boundary wall. |
| **Allocation Cost** | Incurs standard heap overhead due to creating a `HashMap` data structure. | Zero auxiliary data structure allocation; operates purely on raw array elements. |
| **Memory Blueprint** | $\mathcal{O}(N)$ Extra Storage Space for the map lookup cache. | $\mathcal{O}(1)$ Extra Storage Space (highly optimized footprint). |
 */
