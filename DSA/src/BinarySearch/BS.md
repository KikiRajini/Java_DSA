**Binary Search:**
    
1. The "Big O" Nuances
    Average vs. Worst Case: Is BS is always O(logn).
    The Answer: Yes, for a sorted array. But if the data is in a Linked List, BS becomes O(n) because you can't jump to the middle in O(1) time—you have to traverse to it.
    Space Complexity: Always O(1).
    The iterative version (using a while loop) is O(1). The recursive version is O(logn) because of the call stack. FAANG prefers iterative for memory efficiency.

2. Hardware & Performance
Binary Search vs. B-Trees: Why do databases use B-Trees instead of just doing Binary Search on a giant file?
The Answer: Disk I/O. Binary search jumps all over the place, causing "cache misses" or slow disk seeks. B-Trees are optimized for "block access," reading a whole chunk of data into memory at once.

    Why is (left + right) / 2 bad?
    The Answer: Integer Overflow - JVM manages 32-bit integers.

3. Search Space Logic
 What is "Binary Search on Answer"? (Like the Koko Bananas problem).
 The Answer: It’s when the array isn't given, but the range of possible answers is sorted (e.g., speed can be 1 to 1 billion). You "guess" a middle answer and check if it's feasible.

    What if the array is infinitely long?
    The Answer: You use Exponential Search. You check indices 1,2,4,8,16... until you find a value larger than your target, then perform a standard Binary Search within that range.

4. Comparing to Other Algorithms
BS vs. Hash Table: If a Hash Table is O(1), why ever use O(logn) Binary Search?
The Answer:  1. Range Queries: A Hash Table can't find "all numbers between 10 and 50" efficiently.
             2. Order: BS works on sorted data; Hash Tables are unordered.
             3. Space: Hash Tables have overhead for buckets and collision handling; sorted arrays are compact.

5. The "Sorted" Assumption
What if the data is 99% sorted but one element is out of place?
The Answer: BS will fail. You must mention that the pre-condition for BS is strict monotonicity.

Can you use BS on a Bitonic Array? (An array that increases then decreases, e.g., [1, 3, 5, 4, 2]).
    The Answer: Yes! You can use BS to find the "peak" element first, then BS on the two halves.

**B Trees**

While a Binary Search Tree (BST) has at most two children, a B-Tree is a "Balanced" M-way tree that can have many children.

1. Why do we need them? (The Disk Problem)

In a FAANG interview, the "Senior" answer starts with Storage Hierarchy.

RAM is fast but small.

Disk (SSD/HDD) is slow but huge.

Databases are too big for RAM, so they live on Disk.

Binary Search is great in RAM, but on Disk, every "jump" (pointer) requires a Disk I/O operation. Disk I/O is thousands of times slower than RAM.

B-Trees solve this by being "short and fat" instead of "tall and thin." By having many children per node, they minimize the number of jumps needed to find data.

2. Key Properties of B-Trees

Self-Balancing: The tree always stays perfectly balanced. Every leaf node is at the exact same depth.

Sorted Keys: Within a single node, the keys are kept in sorted order.

Block-Friendly: A single node is designed to fit exactly into a "Disk Block" or "Page" (usually 4KB or 8KB). When the DB reads a node, it gets dozens or hundreds of keys in one single physical read.

3. How a Search Works

Imagine you are looking for Key 25:

You load the Root Node. It contains [10, 20, 30].

You do a Binary Search inside that node (in RAM) to find where 25 fits.

You see 25 is between 20 and 30. You follow the pointer to the child between them.

You load that Child Node from disk and repeat until you hit the leaf.

Because each node can hold hundreds of keys, a B-Tree can index billions of rows in only 3 or 4 levels. This means only 3 or 4 disk reads to find any record.

4. B-Tree vs. B+ Tree

This is a very common follow-up question.

B-Tree: Stores both the key and the actual data (or a pointer to it) in both internal and leaf nodes.

B+ Tree (Most Databases): Stores all actual data only in the leaf nodes. The internal nodes only store keys to guide the search.

The Plus: In a B+ Tree, leaf nodes are linked together in a Linked List. This makes "Range Queries" (e.g., SELECT * WHERE age BETWEEN 20 AND 30) incredibly fast because you just find the start and follow the list.