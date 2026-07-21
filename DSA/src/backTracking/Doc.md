Introduction to Backtracking

At its core, backtracking is an algorithmic technique used to find all (or some) solutions to a problem by incrementally building candidates and abandoning a candidate ("backtracking") as soon as it is determined that it cannot lead to a valid final solution.
Think of it as a methodical, programmatic way of executing a brute-force search via trial and error.

How It Works Under the Hood: The Decision Tree
When you write a backtracking algorithm, the computer builds a virtual State Space Tree (or Decision Tree) in its memory.
Nodes represent the current state of your solution (e.g., the items currently in your backpack, or your current position in a maze).
Branches represent the choices available to you from that state.
Leaf Nodes are either valid solutions or dead ends.
The program uses Depth-First Search (DFS) to dive as deep as possible down one branch. If it hits a dead end (violates a problem constraint) or successfully finds a solution, it "pops" the current state off the recursion stack, moves back up to the parent node, and explores the next branch.

The 3-Step Backtracking Blueprint
void backtrack(State currentState) {
// 1. BASE CASE / WIN CONDITION
if (currentState is a valid solution) {
saveToResults(currentState);
return;
}

    // 2. LOOP THROUGH CHOICES
    for (Choice choice : availableChoices) {
        if (choice is valid) {
            
            makeChoice(choice);          // STEP A: "Choose" (Step forward)
            backtrack(nextState);        // STEP B: "Explore" (Recurse deeper)
            undoChoice(choice);          // STEP C: "Un-choose" (Backtrack/Clean up)
        }
    }
}

When should you use Backtracking?
You can instantly identify a backtracking problem if the prompt asks you to:
"Generate all possible combinations / permutations..."
"Find all valid paths to solve..."
"Return all subsets..."
It is uniquely suited for scenarios where you must exhaustively search a large combination space, but can eliminate entire branches of invalid choices early on (a process called pruning) to save time.

Pattern 1: Combinations & Subsets (Order-Independent)
The Core Concept: Finding groups of elements where order does not matter (e.g., [1, 2] is identical to [2, 1]).
The Structural Signal: You must use a forward-moving startIndex parameter (i + 1). This acts as a one-way gate preventing the code from looking backward and generating duplicates.
Reference Code (LeetCode 78: Subsets)

    public class SubsetsPattern {
    public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> allSubsets = new ArrayList<>();
    if (nums == null) return allSubsets;
    generate(0, nums, new ArrayList<>(), allSubsets);
    return allSubsets;
    }

    private void generate(int startIndex, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Snapshot current valid path  
       result.add(new ArrayList<>(current));
        for (int i = startIndex; i < nums.length; i++) {
            current.add(nums[i]);                     // 1. Choose
            generate(i + 1, nums, current, result);   // 2. Explore (i + 1 prevents backtracking duplicates)
            current.remove(current.size() - 1);       // 3. Backtrack
        }
    }
}

Your final output list will be populated in this chronological order:
[] (Captured right at the start)
[1]
[1, 2]
[1, 2, 3]
[1, 3]
[2]
[2, 3]
[3]

Pattern 2: Permutations (Order-Dependent)
The Core Concept: Arranging elements where ordering matters fundamentally (e.g., [1, 2, 3] vs [1, 3, 2]).
The Structural Signal: You do not pass a startIndex. The loop always begins at 0 because you need to look back at previously skipped numbers. Instead, you manage constraint boundaries using a boolean[] visited lookup table.
Reference Code (LeetCode 46: Permutations)
//The visited array is the memory guard of your code. Its entire purpose is to prevent the algorithm from picking up the exact same physical element twice in a single permutation path.
public class PermutationsPattern {
public List<List<Integer>> permute(int[] nums) {
List<List<Integer>> result = new ArrayList<>();
if (nums == null || nums.length == 0) return result;
generate(nums, new boolean[nums.length], new ArrayList<>(), result);
return result;
}

    private void generate(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        // Base case: Full permutation completed
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue; // Guard clause: skip already selected elements
            current.add(nums[i]);       // 1. Choose
            visited[i] = true;
            generate(nums, visited, current, result); // 2. Explore
            
            visited[i] = false;         // 3. Backtrack
            current.remove(current.size() - 1);
        }
    }
}

Your final output list will be populated in this exact chronological order:
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 1, 2]
[3, 2, 1]

Pattern 3: Constraint Satisfaction & Bound Counters
The Core Concept: Building string or sequence outcomes based on mathematical balancing parameters rather than structural loops over an array index.
The Structural Signal: Recursion relies purely on dynamic conditional branches (if statements tracking inventory levels) and a StringBuilder to modify state strings in-place.
Reference Code (LeetCode 22: Generate Parentheses)

public class ConstraintPattern {
public List<String> generateParenthesis(int n) {
List<String> result = new ArrayList<>();
build(new StringBuilder(), n, n, result);
return result;
}

    private void build(StringBuilder current, int openLeft, int closeLeft, List<String> result) {
        // Base Case: String is completely built
        if (openLeft == 0 && closeLeft == 0) {
            result.add(current.toString());
            return;
        }

        // Branch 1: Place an opening bracket if inventory exists
        if (openLeft > 0) {
            current.append('(');
            build(current, openLeft - 1, closeLeft, result);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // Branch 2: Place a closing bracket ONLY if it creates a valid balance
        if (closeLeft > openLeft) {
            current.append(')');
            build(current, openLeft, closeLeft - 1, result);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}

Pattern 4: Graph/Matrix Exploration (2D Grids)
The Core Concept: Navigating paths across multi-dimensional coordinate spaces (up, down, left, right).
The Structural Signal: You eliminate tracking variables for a localized direction matrix offset array ([][] DIRECTIONS) alongside temporary grid character substitution to prevent internal loop revisiting errors.
Reference Code (LeetCode 79: Word Search)
public class Solution {
private int ROWS, COLS;

    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int i) {
        if (i == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r >= ROWS || c >= COLS ||
            board[r][c] != word.charAt(i) || board[r][c] == '#') {
            return false;
        }

        board[r][c] = '#';
        boolean res = dfs(board, word, r + 1, c, i + 1) ||
                      dfs(board, word, r - 1, c, i + 1) ||
                      dfs(board, word, r, c + 1, i + 1) ||
                      dfs(board, word, r, c - 1, i + 1);
        board[r][c] = word.charAt(i);
        return res;
    }
}



**_Cheat Sheet:_**


**Rule 1: Vertical Movement (Index Passing)**

Question it answers: "Am I allowed to reuse the exact same number from the array over and over again?"

This is controlled entirely by what you pass down into the recursive dfs(...) call.

Passing i → Infinite Reuse Allowed
Code: dfs(i, ...)
Used in: Combination Sum I

Passing i + 1 → Single Use Only
Code: dfs(i + 1, ...)
Used in: Subsets I & II, Combination Sum II

**Rule 2: Loop Starting Point (Order Control)**
Question it answers: "Do [1, 2] and [2, 1] count as two different answers, or are they the same?"

This controls whether your scanner is allowed to look backward to elements behind it.

Starting at i = startIndex → Order Does Not Matter (Combinations/Subsets)
Code: for (int i = startIndex; i < nums.length; i++)

Starting at i = 0 → Order Matters (Permutations)
Code: for (int i = 0; i < nums.length; i++)

**Rule 3: State Tracking (Vertical History Guard)**
Question it answers: "If I start my loop at 0 every time (Rule 2), how do I stop a path from picking the exact same physical slot twice?"
This keeps your active paths legally clean when scanning the whole array.
The Guard Clause: if (visited[i]) continue;
Plain English: Used in Permutations. Since you start looping from 0 in every room, the scanner will naturally run across numbers you already picked higher up in the tree. This boolean checkbox array acts as a vertical shield—it lets the loop look backward, but instantly blocks it from stealing a physical element that is already sitting inside your backpack.

**Rule 4: Horizontal Pruning (Handling Twin Inputs)**
Question it answers: "The input array has identical duplicate numbers (like [2, 2, 5]). How do I stop them from creating duplicate answers?"

This separates using numbers together down a path versus starting copycat branches sideways.
nOte: Arrays.sort(nums); is required for below clause to work 
The Guard Clause: if (i > startIndex && nums[i] == nums[i - 1]) continue;
Requirement: The input array must be sorted first (Arrays.sort(nums)).
Plain English: * If i == startIndex, you are making your first choice in this room. You are diving vertically deeper down a single path. Twins are welcome to be used together (e.g., [2, 2]).
If i > startIndex, you have backtracked, stepped off the starting line, and are moving horizontally to try a sideways alternative option. If this alternative option is an identical twin (nums[i] == nums[i-1]), it is a copycat. Skip it (continue;) because the first twin already explored everything this value could possibly offer at this position.

**Rule 5: Early Stopping (Performance Optimization)**
Question it answers: "I'm looking for sums that add up to a target. When do I give up on a loop?"
This saves massive amounts of time by shutting down dead paths instantly.
The Guard Clause: if (total + nums[i] > target) return;
Requirement: The input array must be sorted first.

