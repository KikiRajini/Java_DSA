package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];//position and speed
        for(int i=0; i<position.length; i++){
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        // sort by position descending : closest to target to farthest from target to compute fleets
        Arrays.sort(cars, (a, b) -> b[0] - a[0]);

        Deque<Double> numOfFleets = new ArrayDeque<>(); //going to store time and msecs matters
        for(int[] pair: cars){
            //compute time and club into fleets
            double time = (double)(target - pair[0]) / pair[1];

            // if this car catches up, merge fleet --dont increase stack size. Only when time taken is more, it forms seperate fleet
            if(!numOfFleets.isEmpty() && time <= numOfFleets.peek()){
                continue;
            }
            numOfFleets.push(time);
        }
        return numOfFleets.size();
    }
}
