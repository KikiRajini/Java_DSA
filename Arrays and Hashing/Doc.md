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


**ArrayList** : ArrayList (Class) -> Implements -> List (Interface) -> Extends -> Collection
Class provides dynamic array. 
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
3. TreeMap: Map<String, Integer> map = new TreeMap<>(); -> Internally uses binary search tree.
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
















