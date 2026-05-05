package LinkedList;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        int carry = 0;

        // Correct condition: Continue if there is data OR a leftover carry
        while (l1 != null || l2 != null || carry != 0) {

            // Ternary checks prevent NullPointerException
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;

            carry = sum / 10; // Standard way to get the 'tens' place
            dummy.next = new ListNode(sum % 10); // Standard way to get the 'ones' place

            dummy = dummy.next;

            // Only move to .next if the current node isn't already null
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return head.next;
    }
}
