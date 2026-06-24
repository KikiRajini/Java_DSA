package PQ;

import java.util.Collections;
import java.util.PriorityQueue;

public class lastStone {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone: stones){
            pq.offer(stone);
        }
        while(pq.size()>1){
            int a = pq.poll();
            int b = pq.poll();
            if(a<b){pq.offer(b-a);}
            else if(b<a){pq.offer(a-b);}
            else {pq.offer(0);}

        }

        return pq.poll();
    }
}
