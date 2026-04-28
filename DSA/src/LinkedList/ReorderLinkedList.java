package LinkedList;
/*You are given the head of a singly linked-list.

The positions of a linked list of length = 7 for example, can intially be represented as:

[0, 1, 2, 3, 4, 5, 6]

Reorder the nodes of the linked list to be in the following order:

[0, 6, 1, 5, 2, 4, 3]

Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:

[0, n-1, 1, n-2, 2, n-3, ...]

You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.

Example 1:

Input: head = [2,4,6,8]

Output: [2,8,4,6]
Example 2:

Input: head = [2,4,6,8,10]

Output: [2,10,4,8,6]
*/

public class ReorderLinkedList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // Phase 1: Find the Middle of the List
        // Using Slow and Fast pointers to locate the split point.
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Phase 2: Reverse the Second Half
        // Disconnect the first half and reverse everything after 'slow'.
        ListNode prev = null;
        ListNode curr = slow.next;
        slow.next = null; // CRITICAL: Break the link to prevent cycles

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // Phase 3: Merge the Two Halves (Zig-Zag Merge)
        // Link nodes from the first half to the reversed second half.
        ListNode first = head;
        ListNode second = prev; // Head of the reversed second half

        while (second != null) {
            // Save next pointers
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;

            // Wire: First -> Second -> nextFirst
            first.next = second;
            second.next = nextFirst;

            // Move pointers forward
            first = nextFirst;
            second = nextSecond;
        }
    }
}
