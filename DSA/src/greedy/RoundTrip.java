package greedy;

public class RoundTrip {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //totalSurplus is for computing if the total trip can be accomplished
        int totalSurplus =0;
        int start = 0;
        int currGas = 0;
        for(int i =0; i<gas.length ; i++){
            currGas = currGas+gas[i]-cost[i];
            totalSurplus += gas[i]-cost[i];
            if(currGas<0){
                currGas = 0;
                start = i+1;
            }
        }
        if(totalSurplus<0){return -1;}

        return start;
    }
}
