🏛️ The Structural Responsibility: Representation
Before you can traverse a graph, you must represent it in memory. In 95% of interview and production scenarios, you will use an Adjacency List. It is space-efficient (O(V+E)) and maps perfectly to Java's collection framework.
Java
import java.util.*;

// Clean boilerplate for an Adjacency List Graph representation
public class Graph {
private final int vertices;
private final Map<Integer, List<Integer>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, boolean isBidirectional) {
        adjacencyList.get(source).add(destination);
        if (isBidirectional) {
            adjacencyList.get(destination).add(source);
        }
    }

    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.getOrDefault(vertex, Collections.emptyList());
    }
    
    public int getVerticesCount() {
        return this.vertices;
    }
}
🔄 The Two Foundational Traversal Patterns
Every complex graph algorithm (Dijkstra's, Shortest Path, Connected Components) is just a modified version of these two traversals. Memorize their structural shapes.
1. Breadth-First Search (BFS) — The Shortest Path Pattern
   Responsibility: Explores the graph layer by layer (level-order).
   Core Use Case: Finding the shortest path in an unweighted graph, or broadcasting/modeling peer-to-peer networks.
   Boilerplate Keyword: Uses a Queue (FIFO) and a Set or boolean[] array to track visited nodes to avoid infinite loops.
   Java
   public void bfs(Graph graph, int startVertex) {
   Queue<Integer> queue = new LinkedList<>();
   boolean[] visited = new boolean[graph.getVerticesCount()];

   queue.add(startVertex);
   visited[startVertex] = true;

   while (!queue.isEmpty()) {
   int current = queue.poll();
   System.out.print(current + " "); // Process the node

        for (int neighbor : graph.getNeighbors(current)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.add(neighbor);
            }
        }
   }
   }
2. Depth-First Search (DFS) — The Exploration Pattern
   Responsibility: Dives as deep as possible down a path before backtracking.
   Core Use Case: Detecting cycles, maze solving, topological sorting, and evaluating dependency graphs.
   Boilerplate Keyword: Uses the Call Stack (Recursion) or an explicit Stack object, alongside a tracking mechanism for visited states.
   Java
   public void dfs(Graph graph, int startVertex) {
   boolean[] visited = new boolean[graph.getVerticesCount()];
   dfsHelper(graph, startVertex, visited);
   }

private void dfsHelper(Graph graph, int vertex, boolean[] visited) {
visited[vertex] = true;
System.out.print(vertex + " "); // Process the node

    for (int neighbor : graph.getNeighbors(vertex)) {
        if (!visited[neighbor]) {
            dfsHelper(graph, neighbor, visited);
        }
    }
}