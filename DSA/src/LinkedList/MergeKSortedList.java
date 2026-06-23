package LinkedList;

import java.util.PriorityQueue;

public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b)->a.val - b.val);

        for(ListNode list : lists){
            pq.offer(list);
        }

        ListNode res = new ListNode(0);
        ListNode curr= res;
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;

            node = node.next;
            if(node!=null){pq.offer(node);}
        }

        return res.next;

    }
}
