//Given an undirected tree, return its diameter: the number of edges in a longes
//t path in that tree. 
//
// The tree is given as an array of edges where edges[i] = [u, v] is a bidirecti
//onal edge between nodes u and v. Each node has labels in the set {0, 1, ..., edg
//es.length}. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: edges = [[0,1],[0,2]]
//Output: 2
//Explanation: 
//A longest path of the tree is the path 1 - 0 - 2.
// 
//
// Example 2: 
//
// 
//
// 
//Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
//Output: 4
//Explanation: 
//A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
// 
//
// 
// Constraints: 
//
// 
// 0 <= edges.length < 10^4 
// edges[i][0] != edges[i][1] 
// 0 <= edges[i][j] <= edges.length 
// The given edges form an undirected tree. 
// 
// Related Topics Tree Depth-first Search Breadth-first Search


// Use two standard BFSs
class Solution {
    int n;  // num of nodes
    List<Integer>[] adjacencyList;

    public int treeDiameter(int[][] edges) {
        n = edges.length + 1;
        adjacencyList = new LinkedList[n];

        for(int i = 0; i < n; i++)
            adjacencyList[i] = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            int v1 = edges[i][0], v2 = edges[i][1];
            adjacencyList[v1].add(v2);
            adjacencyList[v2].add(v1);
        }

        // BFS1: find furthest node from node 0
        int[] temp = bfs(0);
        // BFS2: find furthest node from "furthest node from node 0"
        int[] res = bfs(temp[0]);

        return res[1];
    }

    public int[] bfs (int start) {
        // BFS
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> closed = new HashSet<>();
        int[] distances = new int[n];

        q.offer(start);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                closed.add(node);
                for (int j = 0; j < adjacencyList[node].size(); j++) {
                    int child = adjacencyList[node].get(j);
                    if (closed.contains(child))
                        continue;
                    q.offer(child);
                    distances[child] = distances[node] + 1;
                }
            }
        }

        // find furthestNode from start
        int furthestNode = start;
        for (int i = 0; i < n; i++) {
            if (distances[i] > distances[furthestNode])
                furthestNode = i;
        }

        return new int[]{furthestNode, distances[furthestNode]};
    }
}


// Get rid of HashSet
class Solution {
    int n;  // num of nodes
    List<Integer>[] adjacencyList;

    public int treeDiameter(int[][] edges) {
        n = edges.length + 1;
        adjacencyList = new LinkedList[n];

        for(int i = 0; i < n; i++)
            adjacencyList[i] = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            int v1 = edges[i][0], v2 = edges[i][1];
            adjacencyList[v1].add(v2);
            adjacencyList[v2].add(v1);
        }

        // BFS1: find furthest node from node 0
        int[] temp = bfs(0);
        // BFS2: find furthest node from "furthest node from node 0"
        int[] res = bfs(temp[0]);

        return res[1];
    }

    public int[] bfs (int start) {
        // BFS
        Queue<Integer> q = new ArrayDeque<>();
        int[] distances = new int[n];
        Arrays.fill(distances, -1);
        distances[start] = 0;

        q.offer(start);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                for (int j = 0; j < adjacencyList[node].size(); j++) {
                    int child = adjacencyList[node].get(j);
                    if (distances[child] > -1)
                        continue;
                    q.offer(child);
                    distances[child] = distances[node] + 1;
                }
            }
        }

        // find furthestNode from start
        int furthestNode = start;
        for (int i = 0; i < n; i++) {
            if (distances[i] > distances[furthestNode])
                furthestNode = i;
        }

        return new int[]{furthestNode, distances[furthestNode]};
    }
}