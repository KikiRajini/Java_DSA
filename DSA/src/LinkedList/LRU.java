package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRU {

        private Map<Integer, Node> cache;
        private int capacity;
        private int currCap;
        private Node left;  // LRU Dummy (Oldest)
        private Node right; // MRU Dummy (Newest)

        public LRU(int capacity) {
            this.cache = new HashMap<>();
            this.capacity = capacity;
            this.currCap = 0;

            // Initialize dummy nodes to avoid null pointer checks
            this.left = new Node(0, 0);
            this.right = new Node(0, 0);
            this.left.next = this.right;
            this.right.prev = this.left;
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                // Move node to the "Most Recently Used" position
                remove(node);
                insert(node.key, node.val);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // Remove old version if key already exists
                remove(cache.get(key));
            }

            if (currCap >= capacity) {
                // Evict the Least Recently Used node (next to left dummy)
                remove(this.left.next);
            }

            // Insert the new/updated entry
            insert(key, value);
        }

        /**
         * Helper: Handles List wiring, Map addition, and counter increment.
         * Always inserts at the "Right" side (Most Recently Used).
         */
        private void insert(int key, int value) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);

            Node prevNode = this.right.prev;

            // Wiring logic: prev <-> newNode <-> right
            prevNode.next = newNode;
            newNode.prev = prevNode;
            newNode.next = this.right;
            this.right.prev = newNode;

            currCap++;
        }

        /**
         * Helper: Handles List unwiring, Map removal, and counter decrement.
         */
        private void remove(Node node) {
            // Unplug node from the Doubly Linked List
            Node toTheLeft = node.prev;
            Node toTheRight = node.next;

            toTheLeft.next = toTheRight;
            toTheRight.prev = toTheLeft;

            // Remove from Map and update current capacity
            cache.remove(node.key);
            currCap--;
        }

    class Node {
        int val;
        int key;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}
