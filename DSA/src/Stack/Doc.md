🟢 1️⃣ **NORMAL STACK (java.util.Stack)**
🔹 A legacy LIFO stack implemented on top of Vector.
Stack<Integer> stack = new Stack<>();
• LIFO (Last In First Out)
• Thread-safe (synchronized)
• Slower than modern alternatives
Operation	Method
Push	push(x)
Pop	pop()
Peek	peek()
Empty	empty()
Search	search(x)
Time
• push → O(1)
• pop → O(1)
• peek → O(1)
Verdict
❌ Legacy
❌ Slow
✔ Still valid
⚠️ Not recommended for interviews

🟢 2️⃣ **MODERN STACK (Deque as Stack)**
🔹 A stack implemented using ArrayDeque with O(1) operations.
Deque<Integer> stack = new ArrayDeque<>();
Core Functions
Operation	Method
Push	push(x)
Pop	pop()
Peek	peek()
Empty	isEmpty()
How it works
• Uses resizable array
• Top = end of array
• No shifting
Time
• All ops → O(1)
Use when
✔ Backtracking
✔ Parentheses
✔ Monotonic stack
✔ Undo/Redo

🟢 3️⃣ QUEUE / DEQUE

**Queue (FIFO)**
🔹 A FIFO data structure where insertion is at rear and removal at front.
Queue<Integer> q = new LinkedList<>();
// or
Deque<Integer> q = new ArrayDeque<>();
Core Functions
Operation	Method
Add	offer(x)
Remove	poll()
Peek	peek()
Empty	isEmpty()
Time
• All ops → O(1)
Uses
✔ BFS
✔ Scheduling
✔ Sliding window

**Deque (Double Ended Queue)**
🔹 A queue that allows insertion and removal at both ends.
Deque<Integer> dq = new ArrayDeque<>();
Functions
Operation	Method
Add front	addFirst(x)
Add back	addLast(x)
Remove front	removeFirst()
Remove back	removeLast()
Peek front	peekFirst()
Peek back	peekLast()
Uses
✔ Sliding window
✔ Monotonic queue
✔ BFS
✔ Can act as stack or queue

🟢 4️⃣ PRIORITY QUEUE (Heap)
🔹 A heap-based queue that always removes the highest (or lowest) priority element.
PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap
Core Functions
Operation	Method
Insert	add(x) / offer(x)
Remove top	poll()
Peek	peek()
Size	size()
Empty	isEmpty()
How it works
• Binary heap
• Stored as array
• Parent-child priority ordering
Time
• insert → O(log n)
• remove → O(log n)
• peek → O(1)
Uses
✔ Top K
✔ Kth largest
✔ Dijkstra
✔ Task scheduling