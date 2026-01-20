**Arrays** : Objects used to store multiple values of same type in a single variable. 
Primitive values -> Stored in memory , Class Objects -> Actual objs are stored in heap
Dynamically allocated -> Declare array variable + assign array to it -> this is stored in contiguous memory.

_Declaration_ : type var_name[]/ type[] var_name; //creates only a reference
_Instantiating_ : var_name = new type[size]; //allocates memory to array.
                Array literal : int[] arr = {1,2,3,4,5};
                > For type int, all values are initialised to 0
                > For boolean, it is initialised to false
                > For reference types, it is initialised to null.
_Access element_: var_name[position]
_Update element:_ var_name[position] = value;
- Fixed size
+ Makes memory nmanagement easier

1. Length: arr.length
2. Clone: arr1 = arr.clone() -> Only primitive values are deeply copied. For objects, references are copied and objects are not duplicated.

_Array Class functions_:
1. Arrays.asList(arr) -> Converts elements to a list.
2. Arrays.sort() -> Sorts array in ascending order.
3. Arrays.sort(arrays, Comparator.reverseOrder()) -> Sorts in descending order
4. Arrays.binarySearch(arr, values_to_search) -> searches using binary search
5. Arrays.compare(arr1,arr2) -> O/p : 0 -> If both are equal; -ve no. -> If arr2 > arr1; +ve no. -> arr1 > arr2. The comparison is done lexicographically (length wise and element by element wise)
6. Arrays.equals(arr1,arr2) -> O/p : True if same elements are in same order, else False. This is for primitive types. For object arrays , Arrays.deepEquals(arr1,arr2)
7. Arrays.toString(arr)
8. Arrays.copyOf(arrays); Arrays.copyOf(arrays, new_length); Arrays.copyOf(arrays,start_postn,end_postn)

_Some extra functions_:
1. str.toCharArray() -> will return char[]
2. char1.isLetterOrDigit(); 
3. char1.toLowerCase(); 
4. StringUtils.isAlphanumeric(str);
5. str.tolowerCase();
6. str1.equalsIgnoreCase(str2);
7. str.charAt(index);
8. Integer.parseInt(str);
9. String Builder:
    StringBuilder sb = new StringBuilder(str); or can create empty using StringBuilder sb = new StringBuilder();
    sb.append(str);
    sb.toString();
    String s = sb.substring(start,end);

**List** : In Java, a List is a part of the Java Collections Framework and represents an ordered collection (or sequence) of elements. The List interface is implemented by several classes, such as ArrayList, LinkedList, and Vector.


**ArrayList** : ArrayList (Class) -> Implements -> List (Interface) -> Extends -> Collection
Class provides dynamic array. Can have only wrapper class objects like Integer
+ Need not mentions size when initialising. If size increases, creates bigger memory on heap, copies data and deletes old memory.
+ Insertion order is preserved.
+ Null insertion is possible.
- Slower than array but provides useful funcs.
- Is not synchronised (coordination of access to shared resources) -> not thread safe (can lead to concurrency when multiple threads access/modify the list simultaneously).
For synchronisation,you can use: List<String> sync_arr = Collections.synchronisedList(list);
Declaration: ArrayList<Integer> arr = new ArrayList<Integer>;
To add data : arr.add(i);
Note: Cannot use primitive types.

_ArrayList functions_:
1. arr.add(value); arr.add(position, value) -> will insert and move elements in position to right.
2. arr.set(position, value) -> will update/set value
3. arr.remove(position)/ arr.remove(value)
4. arr.size()
5. arr.get(position)
6. Collections.sort(list);
7. Collections.max(list);
8. arr1.addAll(arr2); arr1.addAll(position, arr2) -> add arr2 at position and shifts rest to end.
9. arr.indexOf(value) -> first position of value; arr.lastIndexOf(value) -> last position of value
10. arr1.equals(arr2) -> checks for both order and value
11. arr1.containsAll(arr2); -> compares regardless of order

**Map** : 
It is an interface which helps to map a key to a value.
> Hashmap and LinkedHashMap allows null key and value.
> TreeMap and LinkedHashMap has ordering. TreeMap sorts keys in ascending order. LinkedHashMap maintains order of insertion.
> TreeMap implements SortedMap which extends Map. LinkedHashMap extends HashMap which implements Map interface.

_Implementation_:
1. HashMap: Map<String, String> map = new HashMap<>();
> Uses Hashing -> Technique to convert large string to small string that represents same string.
  The hash function that maps keys to some values. Key -> Hash_Func -> HashCode -> func -> Index in array.
  If multiple key has same index (Hash collision) -> value is stored in linked list in index and uses hashcode to retrieve respective value.
2. LinkedHashMap: Map<String, Integer> map = new LinkedHashMap<>();
   ✔ Maintains insertion order
   ✔ Internally:
   • HashMap
   • PLUS doubly linked list
3. TreeMap: Map<String, Integer> map = new TreeMap<>(); -> Internally uses binary search tree.
   ✔ Uses Red-Black Tree
   ✔ Keys stored in sorted order
> Map<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());


_Functions_:
1. map.put(key,value); -> adds new value/ replaces existing key and value.
2. map.putAll(map1) -> copies map1 to map
3. map.remove(key);
4. map.get(key);
5. Iteration: for(Map.Entry<String, Integer> mapElement: map.entrySet()){ String key = mapElement.getkey(); Integer val = mapElement.getValue();}
6. map.size();
7. map.containsKey(key);
8. map.containsValue(value);
9. map1.equals(map2);
10. map.isEmpty();
11. map.getOrDefault(key, default_value); -> returns value associated with key. If key doesn`t exists, put default_value for key in map and returns new value.
12. map.putIfAbsent(key,value); -> Puts key and value and returns null. Doesn`t overwrite if already exists and returns original value.
13. map.computeIfAbsent(key, func); -> returns value if key exists, else computes using function and returns that val.
14. map.keySet();
15. map.values();
16. For treemap; map.lastKey();


**Set**: It is an interface which doesn`t allow duplicate, nulls are accepted.
Set -> Extends -> Sorted Set -> Extends -> Navigable Set -> Implements -> Tree Set
> Order is not predicatable.

_Implementation_:
1. HashSet: Uses hashtable, no order
2. LinkedHashSet: Maintains insertion order
3. TreeSet: Sorted order of elements

_Declaration_: Set<String> set = new HashSet<>();

_Functions_:
1. set.add(value); set.addAll(collection/set);
2. set.contains(element); set.containsAll(collection/set);
3. set.size();
4. set.isEmpty();
5. set.remove(element); set.removeAll(collection/set);
6. set.toArray();
7. set.retainAll(set1); -> Gives the intersection
8. set.removeAll(set1) -> removes all values from set which is in set1
9. set1.equals(set2); //compares references.
10. Set.equals(set1,set2); // compares contents



**PriorityQueue** - Heap Data structure:
1. Default PriorityQueue (Min Heap)
   PriorityQueue<Integer> pq = new PriorityQueue<>();
   • Smallest element is always on top
   • peek() gives smallest
   • poll() removes smallest
2. How to make a MAX HEAP
   Method 1: Using Collections.reverseOrder()
   PriorityQueue<Integer> pq =
   new PriorityQueue<>(Collections.reverseOrder());
   Now:
   • Largest element on top
   • peek() → max
   • poll() → removes max
   Method 2: Custom Comparator
   PriorityQueue<Integer> pq =
   new PriorityQueue<>((a, b) -> b - a);
   Same effect:
   • Bigger value gets higher priority
3. Important PriorityQueue Operations
   Operation	Code	    What it does	Time
   Insert	   pq.add(x)	Add element	    O(log n)
   Insert	   pq.offer(x)	Same as add	    O(log n)
   Remove top  pq.poll()	Removes min/max	O(log n)
   View top	   pq.peek()	Returns top	    O(1)
   Size	       pq.size()	Count elements	O(1)
   CheckEmpty  pq.isEmpty()	True/false	    O(1)
   Remove      pq.remove(x)	Removes     	O(n)
   Clear	   pq.clear()	Remove all	    O(n)


**String**:
For length -> String.length();
For slicing -:
substring(start, end)
start → included
end   → excluded

1) Using + Operator
   String s = "Hello";
   s = s + " World";
   Internals
   • Creates new String object every time
   • Old string becomes garbage
   Complexity
   • Time → O(n) per concatenation
   • Space → O(n) (new object)
   Use when
   ✔ Very few concatenations
   ❌ Inside loops (bad)
2) String.concat()
   String s = "Hello".concat(" World");
   Same as + internally.
   Complexity
   • Time → O(n)
   • Space → O(n)
3) StringBuilder (BEST for interviews)
   StringBuilder sb = new StringBuilder();
   sb.append("Hello");
   sb.append(" World");
   String s = sb.toString();
   Why best?
   • Mutable
   • No new object each time
   • Fast
   Complexity
   • append → O(1) amortized
   • Space → O(n)
   Use when
   ✔ Building strings in loops
   ✔ Performance critical code
4) StringBuffer
   StringBuffer sb = new StringBuffer();
   sb.append("Hello");
   Same as StringBuilder but:
   • Thread-safe
   • Synchronized → slower
   Use when
   ✔ Multi-threaded environment
5) String.format()
   String s = String.format("Name: %s Age: %d", name, age);
   Complexity
   • Slower
   • More overhead
   Use when
   ✔ Formatting output
   ❌ Performance critical paths
6) String.join()
   String s = String.join(",", "A", "B", "C");
   Use when
   ✔ Joining multiple strings with delimiter
7) Using char[]
   char[] arr = {'H','i'};
   String s = new String(arr);
   Use when
   ✔ Low-level manipulation
   ✔ Passwords (security)
8) Using Streams
   String s = list.stream()
   .collect(Collectors.joining(","));
   Use when
   ✔ Working with collections


**Additional**:
log n << n < n log n
/ -> gives quotient
% -> gives remainder







