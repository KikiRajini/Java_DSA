Greedy:
- Doesn.t look back at it's previous selection unlike DP.
- At each step, it makes the choice that looks the best right now (the "locally optimal" choice) in the hopes that these choices will lead to the best overall solution (the "globally optimal" choice).
- How It Works (The Core Logic)
    Selection: Choose the best current option.
    Feasibility: Check if this option is valid and doesn't violate the problem's rules.
    Maximization/Minimization: Add it to the solution and move to the next step.
    ⚠️ The Golden Rule: Greedy algorithms never reconsider their decisions. Once a choice is made, it is locked in, for better or worse.


                       Pros	                                                                           Cons
Fast: Usually has a very low time complexity (often O(n) or O(nlogn)).	Short-sighted: Can get stuck in "local traps" and miss the bigger picture.
Simple: Easy to design, code, and debug.	                            Not always optimal: Does not guarantee the absolute best solution for every problem.

**Famous Greedy Algorithms**
Greedy strategies are used to solve some of computer science's most famous problems:
**Dijkstra's Algorithm**: Finding the shortest path in a graph (like Google Maps finding a route).
**Huffman Coding**: Used for data compression (making file sizes smaller).
**Kruskal’s & Prim’s Algorithms**: Finding the Minimum Spanning Tree (connecting points with the least amount of wire/road).