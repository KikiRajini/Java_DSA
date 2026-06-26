package pq;

import java.util.*;

public class TaskScheduler {

        public int leastInterval(char[] tasks, int n) {
            int[] count = new int[26];
            for(char task: tasks){ //Count freq of each task and add to maxHeap
                count[task-'A']++;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int c: count){
                if(c>0){
                    pq.offer(c);
                }
            }

            int time =0;
            Queue<int[]> q = new ArrayDeque<>(); //FIFO
            while(!pq.isEmpty() || !q.isEmpty()){
                time++; //process the one task of the task with highest frequency..put in queue with the time it can be processed next


                if (pq.isEmpty()) {
                    time = q.peek()[1];}
                //if pq is empty, but next tasks can be processed after being idle for some time..we just pick the time from q,
                //instead of waiting for time to increment
                else{
                    int c = pq.poll()-1;
                    if(c>0){q.add(new int[]{c,time+n});}}//time+n -> next interval in which it can be processed

                if(!q.isEmpty() && q.peek()[1]==time){pq.add(q.poll()[0]);}
            }

            return time;
        }


}
