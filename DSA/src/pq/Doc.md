pq - Priority Queue - Logical concept - Its a queue that stores element based on priroity. 
Heap - Physical structure used to implement pq.

1. The Core Mechanism: What is a Heap?
   A Heap is a specialized, complete binary tree that satisfies the Heap Property. There are two types:
   Min-Heap: The value of the parent node is always less than or equal to the values of its children. This means the absolute smallest element is always sitting at the very top (the root).
   Max-Heap: The value of the parent node is always greater than or equal to the values of its children. This means the absolute largest element is always sitting at the root.

The Array Trick (How it lives in memory)
Even though we visualize a Heap as a tree structure with left and right pointers, Java does not use a node object structure to store it. Instead, it packs a heap into a flat, contiguous primitive array because it is mathematically structured.
If a parent node is sitting at index i in the array, you can find its family instantly using index arithmetic:
Left Child Index: 2 * i + 1
Right Child Index: 2 * i + 2
Parent Index: (i - 1) / 2
Because of this array layout, moving up and down the tree takes direct mathematical operations, making it highly performant.

2. Time Complexity: Why Use a Heap?
   If you tried to maintain a sorted ArrayList to pull the minimum element every time, inserting a new element would force you to shift elements around, taking O(N) time.
   A Heap uses a process called Heapify (bubbling elements up or down to fix broken rules) which allows it to maintain order efficiently:
   Peek (Find Min/Max): O(1) — It just looks at index 0 of the array.
   Offer/Add (Insertion): O(logN) — Adds to the end of the array and bubbles up.
   Poll/Remove (Extract Min/Max): O(logN) — Removes index 0, moves the last element to the top, and bubbles it down.

3. Priority Queue in Java (java.util.PriorityQueue)
   Java provides a built-in, ready-to-use implementation of a Min-Heap via the PriorityQueue class. By default, if you don't configure anything, it sorts elements in their natural ascending order (integers from smallest to largest, strings alphabetically).
  
Default Min-Heap Example

public class Main {
public static void main(String[] args) {
// By default, this creates a Min-Heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add elements in random order
        minHeap.offer(40);
        minHeap.offer(10);
        minHeap.offer(30);
        minHeap.offer(20);

        // Peek looks at the top element without removing it
        System.out.println("Smallest element: " + minHeap.peek()); // Outputs 10

        // Pulling elements out one by one
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); 
        }
        // Outputs: 10 20 30 40 (Always comes out sorted!)
    }
}

4. Customizing the Order (Max-Heaps and Objects)
   If you are working on an interval scheduling algorithm, a greedy coordinate optimization problem, or simply need a Max-Heap, you have to supply a custom Comparator to redefine how Java determines priority.
  
5. Option A: Creating a Max-Heap
   You can use Collections.reverseOrder() to flip the default natural sorting into descending order:
  

PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(10);
maxHeap.offer(50);
maxHeap.offer(25);

System.out.println(maxHeap.poll()); // Outputs 50 (Largest first)

Option B: Handling Custom Objects (e.g., Interval Tracking)
Imagine you are solving a greedy problem tracking line intervals where each interval has a start and an end time, and you want to prioritize intervals that end earliest:
Java
import java.util.PriorityQueue;

class Interval {
int start;
int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
public static void main(String[] args) {
// Lambda Expression: compares the 'end' times of two intervals
// (a, b) -> a.end - b.end creates a Min-Heap based on the end time
PriorityQueue<Interval> intervalPQ = new PriorityQueue<>((a, b) -> a.end - b.end);

        intervalPQ.offer(new Interval(1, 5));
        intervalPQ.offer(new Interval(3, 4));
        intervalPQ.offer(new Interval(2, 6));

        // The interval ending at 4 will bubble up to the top automatically
        System.out.println("Earliest ending interval ends at: " + intervalPQ.peek().end); // Outputs 4
    }
}


How does Heap work?
To understand how a Heap is physically built out of a disorganized jumble of numbers, we have to look at the two main operations a Priority Queue uses to keep its structure intact: siftUp (when adding an element) and siftDown (when removing an element).
Let's walk through an example of building a Min-Heap from scratch by adding numbers one by one: 40, 10, 30, and 15.
Step 1: Add 40
The array is empty. We insert 40 at the very first slot.
Array Layout: [40]
Tree View:
Plaintext
40 (Index 0)
Heap Check: There are no children, so the parent-child rule is perfectly valid.
Step 2: Add 10
Whenever you add a new element to a heap, you always append it to the very end of the array (which translates to the next available spot in the binary tree from left to right).
Array Layout: [40, 10]
Tree View:
Plaintext
40 (Index 0)
/
10 (Index 1)
🚨 The Rule is Broken! (siftUp executes)
This is a Min-Heap, but the child (10) is smaller than its parent (40). The heap must fix itself using siftUp:
It looks at the parent index of 10 using our index formula: (1 - 1) / 2 = 0.
It compares the values: 10 < 40.
It swaps them in the array.
Final Array Layout: [10, 40]
Final Tree View:
Plaintext
10 (Index 0)
/
40 (Index 1)
Step 3: Add 30
We append 30 to the next open slot at the end of the array.
Array Layout: [10, 40, 30]
Tree View:
Plaintext
10 (Index 0)
/  \
40    30 (Index 2)
Heap Check:
The new child is 30 at index 2. Its parent is at index (2 - 1) / 2 = 0 (value 10). Is the parent smaller than the child? Yes, 10 < 30. The rule is valid. No swaps needed!
Step 4: Add 15
We append 15 to the end of the array.
Array Layout: [10, 40, 30, 15]
Tree View:
Plaintext
10 (Index 0)
/  \
40    30
/
15 (Index 3)
🚨 The Rule is Broken Again! (siftUp executes)
Look at the branch on the left: the child 15 (index 3) is smaller than its parent 40 (index 1). We must bubble it up:
First Swap: We swap 15 and 40.
Array Layout becomes: [10, 15, 30, 40]
Tree View becomes:
Plaintext
10 (Index 0)
/  \
15    30
/
40 (Index 3)
Check Next Parent: Now 15 is sitting at index 1. Its new parent is at index (1 - 1) / 2 = 0 (value 10). Is 10 < 15? Yes. The bubbling stops.
Our heap is fully built and perfectly stable!
What happens when you REMOVE an element? (poll())
A Priority Queue only allows you to remove the element at the very top (index 0). You aren't allowed to hack into the middle of the tree. When you call .poll() to extract the minimum element, it triggers a process called siftDown:
Using our final array [10, 15, 30, 40], let's remove the 10:
The Extraction: The server extracts 10 from index 0. This leaves a gaping hole at the root of the tree.
The Replacement: To keep the tree perfectly balanced without leaving empty array spaces, the heap takes the very last element in the array (40) and yanks it up to fill index 0.
Array Layout temporarily looks like: [40, 15, 30]
Tree View temporarily looks like:
Plaintext
40 (Index 0)
/  \
15    30
The siftDown Correction: Now, 40 is sitting at the top, which completely violates the Min-Heap property. The server looks at its two children (15 and 30), picks the smallest child (15), and swaps places with it.
Final Array Layout: [15, 40, 30]
Final Tree View:
Plaintext
15 (Index 0)
/  \
40    30
The heap has restored its top-to-bottom rule completely standalone, and the new minimum element (15) has successfully bubbled up to index 0, ready for the next time your code requests it.



Why ArrayDeque is almost always better:
No Node Garbage Collection: linkedList has to allocate a new Node object every time you add an element, and destroy it when you remove it. This creates major garbage collection overhead. ArrayDeque just updates array indices.
Cache Efficiency: Because arrays occupy contiguous memory blocks, modern CPU caches can load ArrayDeque elements instantly. linkedList requires "pointer-chasing" across random memory addresses, causing cache misses.
When to use linkedList instead? Only use it if you absolutely must store null elements (which ArrayDeque prohibits), or if you are doing heavy insertions/deletions in the middle of the list while iterating, which a standard queue shouldn't be doing anyway.


When looking specifically at Java's PriorityQueue, there is no functional difference between add() and offer().
Under the hood, the PriorityQueue class implements add(e) by directly calling offer(e) and returning its result:
Java
public boolean add(E e) {
return offer(e);
}

The Big Picture: Why do both exist?
The two methods exist because they originate from different interfaces. The distinction only becomes real when you deal with capacity-restricted (bounded) queues (like an ArrayBlockingQueue with a fixed size of 10):
Method	Source Interface	Behavior When Queue is Full
add(e)	Collection	Throws an IllegalStateException
offer(e)	Queue	Returns false gracefully

Why they behave identically in a PriorityQueue
A standard Java PriorityQueue is unbounded. It relies on an internal array that dynamically grows whenever it runs out of space.
Because the queue grows as needed, it never fills up.
Since it never fills up, offer() will always successfully insert the element and return true.
Consequently, add() will never have a reason to throw an exception.