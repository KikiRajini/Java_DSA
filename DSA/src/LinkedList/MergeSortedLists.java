package LinkedList;

public class MergeSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Step 1: Create a Dummy Anchor (The Head)
        ListNode dummy = new ListNode(0);
        // Step 2: Create a 'Current' pointer to build the chain
        ListNode list3 = dummy;
        while(list1 !=null && list2 !=null){
            if(list2.val > list1.val){
                list3.next = list1;
                list1 = list1.next;
            }else{
                list3.next = list2;
                list2 = list2.next;
            }
            list3 = list3.next;
        }
        if(list1 !=null){
            list3.next = list1;
        }
        if(list2!=null){
            list3.next = list2;
        }
        return dummy.next;
    }
}
