package LinkedList;

public class RemoveNodeFromEndOFlist {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node that points to head
        // This handles the edge case where we need to remove the first node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast pointer n + 1 steps ahead
        // We want 'slow' to stop at the node BEFORE the one we want to remove
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow is now right before the target node
        slow.next = slow.next.next;

        // Return the actual head (not dummy)
        return dummy.next;
    }
}
