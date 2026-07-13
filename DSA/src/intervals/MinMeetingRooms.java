package intervals;

import java.util.List;
import java.util.PriorityQueue;

public class MinMeetingRooms {
    public int minMeetingRooms(List<Interval> intervals) {
        intervals.sort((a,b)-> a.start - b.start);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Interval interval : intervals){

            if(!pq.isEmpty() && pq.peek() <= interval.start){
                pq.poll();
            }

            pq.offer(interval.end);
        }

        return pq.size();
    }
}
