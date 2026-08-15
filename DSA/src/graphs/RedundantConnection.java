package graphs;

public class RedundantConnection {


        int[] manager;
        int[] teamSize; // Now tracking total headcount, not tree height!

        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            manager = new int[n + 1];
            teamSize = new int[n + 1];

            // STEP 1: Initialization
            for(int i = 1; i <= n; i++){
                manager[i] = i;  // Everyone is their own CEO
                teamSize[i] = 1; // Every company starts with exactly 1 employee
            }

            // STEP 2: Process each edge
            for(int[] edge : edges){
                if(!union(edge[0], edge[1])){
                    return edge; // Cycle detected!
                }
            }

            return new int[0];
        }

        // STEP 3: Find the CEO (with Path Compression)
        private int find(int a){
            if(manager[a] != a){
                manager[a] = find(manager[a]);
            }
            return manager[a];
        }

        // STEP 4: Merge Companies (Union by Size)
        private boolean union(int a, int b){
            int bossA = find(a);
            int bossB = find(b);

            if(bossA == bossB){
                return false; // They already report to the same CEO
            }

            // The larger company acquires the smaller one, and we add their headcounts!
            if(teamSize[bossA] >= teamSize[bossB]){
                manager[bossB] = bossA;            // Boss B now reports to Boss A
                teamSize[bossA] += teamSize[bossB]; // Boss A's company absorbs Boss B's headcount
            } else {
                manager[bossA] = bossB;            // Boss A now reports to Boss B
                teamSize[bossB] += teamSize[bossA]; // Boss B's company absorbs Boss A's headcount
            }

            return true;
        }


}
