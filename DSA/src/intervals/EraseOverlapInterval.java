package intervals;

import java.util.Arrays;

public class EraseOverlapInterval {
    public int eraseOverlapIntervals(int[][] intervals) {
        //The secret to maximizing the number of intervals you keep is to always prioritize the interval that finishes earliest.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1],b[1]));
        int count = 0;
        int prevEnd = intervals[0][1];

        for(int i = 1; i<intervals.length; i++){
            /// If the current interval overlaps with the previous one
            if(intervals[i][0]< prevEnd){count++;}
            //no overlap, increase prevEnd boundary
            else{prevEnd = intervals[i][1];}
        }

        return count;
    }
}
