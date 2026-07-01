package intervals;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {

        public int[][] insert(int[][] intervals, int[] newInterval) {
            int n = intervals.length;
            int i = 0;
            List<int[]> result = new ArrayList<>();

            // -------------------------------------------------------------------
            // SCENARIO 1: Add all intervals that come strictly BEFORE newInterval
            // -------------------------------------------------------------------
            // If the current interval ends before the new interval even begins,
            // there is absolutely no overlap.
            while (i < n && intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
                i++; // Move to the next interval
            }

            // -------------------------------------------------------------------
            // SCENARIO 2: Merge all overlapping intervals
            // -------------------------------------------------------------------
            // If the current interval starts before (or exactly when) the new interval ends,
            // they clash! We merge them by stretching the bounds of newInterval.
            while (i < n && intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++; // Keep moving to see if we clash with the *next* one too
            }

            // Once the clashing stops, we finally lock the merged newInterval into our result.
            result.add(newInterval);

            // -------------------------------------------------------------------
            // SCENARIO 3: Add all remaining intervals that come AFTER newInterval
            // -------------------------------------------------------------------
            // Since the initial list was already sorted, everything left over
            // is guaranteed to be completely past the merged interval.
            while (i < n) {
                result.add(intervals[i]);
                i++;
            }

            // Convert our dynamic List back into the required 2D primitive array
            return result.toArray(new int[result.size()][2]);
        }

}
