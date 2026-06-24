package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
            List<int[]> result = new ArrayList<>();
            result.add(intervals[0]);

            for(int[] interval: intervals){
                int start = interval[0];
                int end = interval[1];
                int lastEnd = result.get(result.size()-1)[1];

                if(start<=lastEnd){ //update the last element's end
                    result.get(result.size()-1)[1]= Math.max(lastEnd,end);
                }else{
                    result.add(interval);
                }
            }
            return result.toArray(new int[result.size()][2]);
        }


}
