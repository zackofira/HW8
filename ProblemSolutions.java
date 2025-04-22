/******************************************************************
 *
 *   Zach Kofira / Section 002
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

import java.util.*;

class ProblemSolutions {

    /**
     * Method canFinish
     * <p>
     * You are building a course curriculum along with required intermediate
     * exams certifications that must be taken by programmers in order to obtain
     * a new certification called 'master programmer'. In doing so, you are placing
     * prerequisites on intermediate exam certifications that must be taken first.
     * You are allowing the flexibility of taking the exams in any order as long as
     * any exam prerequisites are satisfied.
     * <p>
     * Unfortunately, in the past, your predecessors have accidentally published
     * curriculums and exam schedules that were not possible to complete due to cycles
     * in prerequisites. You want to avoid this embarrassment by making sure you define
     * a curriculum and exam schedule that can be completed.
     * <p>
     * You goal is to ensure that any student pursuing the certificate of 'master
     * programmer', can complete 'n' certification exams, each being specific to a
     * topic. Some exams have prerequisites of needing to take and pass earlier
     * certificate exams. You do not want to force any order of taking the exams, but
     * you want to make sure that at least one order is possible.
     * <p>
     * This method will save your embarrassment by returning true or false if
     * there is at least one order that can taken of exams.
     * <p>
     * You wrote this method, and in doing so, you represent these 'n' exams as
     * nodes in a graph, numbered from 0 to n-1. And you represent the prerequisite
     * between taking exams as directed edges between two nodes which represent
     * those two exams.
     * <p>
     * Your method expects a 2-dimensional array of exam prerequisites, where
     * prerequisites[i] = [ai, bi] indicating that you must take exam 'bi' first
     * if you want to take exam 'ai'. For example, the pair [0, 1], indicates that
     * to take exam certification '0', you have to first have the certification for
     * exam '1'.
     * <p>
     * The method will return true if you can finish all certification exams.
     * Otherwise, return false (e.g., meaning it is a cyclic or cycle graph).
     * <p>
     * Example 1:
     * Input: numExams = 2, prerequisites = [[1,0]]
     * Output: true
     * Explanation: There are a total of 2 exams to take.
     * To take exam 1 you should have completed the
     * certification of exam 0. So, it is possible (no
     * cyclic or cycle graph of prereqs).
     * <p>
     * <p>
     * Example 2:
     * Input: numExams = 2, prerequisites = [[1,0],[0,1]]
     * Output: false
     * Explanation: There are a total of 2 exams to take.
     * To take exam 1 you should have completed the
     * certification of exam 0, and to take exams 0 you
     * should also have completed the certification of exam
     * 1. So, it is impossible (it is a cycle graph).
     *
     * @param numExams      - number of exams, which will produce a graph of n nodes
     * @param prerequisites - 2-dim array of directed edges.
     * @return boolean          - True if all exams can be taken, else false.
     */

    public boolean canFinish(int numExams,
                             int[][] prerequisites) {

        int numNodes = numExams;  // # of nodes in graph

        // Build directed graph's adjacency list
        ArrayList<Integer>[] adj = getAdjList(numExams,
                prerequisites);

        // ADD YOUR CODE HERE - ADD YOUR NAME / SECTION AT TOP OF FILE
        boolean finishable = true;

        //pop counter to check for infinite loop
        int pops = 0;
        int current;

        //stack to do depth first traversal of graph
        Stack<Integer> traverseStack = new Stack<Integer>();

        //this loop will traverse the whole graph via the stack, if there is a loop then it will get stuck
        //we check for getting stuck by seeing if it goes through n^2 pops (this would mean every node connected to every other node, so there is for sure a loop
        for (int i = 0; i < numExams; i++) {
            traverseStack.add(i);
            while (pops <= numNodes * numNodes && !traverseStack.isEmpty()) {
                current = traverseStack.pop();
                pops++;
                for (int j = 0; j < adj[current].size(); j++) {
                    traverseStack.addAll(adj[current]);
                }
            }
        }

        //if the stack traversal was terminated by pop count, the curriculum is not finishable, so update variable accordingly
        if (pops > numNodes * numNodes) {
            finishable = false;
        }

        return finishable;

    }


    /**
     * Method getAdjList
     * <p>
     * Building an Adjacency List for the directed graph based on number of nodes
     * and passed in directed edges.
     *
     * @param numNodes - number of nodes in graph (labeled 0 through n-1) for n nodes
     * @param edges    - 2-dim array of directed edges
     * @return ArrayList<Integer>[]  - An adjacency list representing the provided graph.
     */

    private ArrayList<Integer>[] getAdjList(
            int numNodes, int[][] edges) {

        ArrayList<Integer>[] adj
                = new ArrayList[numNodes];      // Create an array of ArrayList ADT

        for (int node = 0; node < numNodes; node++) {
            adj[node] = new ArrayList<Integer>();   // Allocate empty ArrayList per node
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);              // Add connected node edge [1] for node [0]
        }
        return adj;
    }


    /*
     * Assignment Graphing - Number of groups.
     *
     * There are n people. Some of them are connected
     * as friends forming a group. If person 'a' is
     * connected to person 'b', and person 'b' is
     * connected to person 'c', they form a connected
     * group.
     *
     * Not all groups are interconnected, meaning there
     * can be 1 or more groups depending on how people
     * are connected.
     *
     * This example can be viewed as a graph problem,
     * where people are represented as nodes, and
     * edges between them represent people being
     * connected. In this problem, we are representing
     * this graph externally as an non-directed
     * Adjacency Matrix. And the graph itself may not
     * be fully connected, it can have 1 or more
     * non-connected compoents (subgraphs).
     *
     * Example 1:
     *   Input :
         AdjMatrix = [[0,1,0], [1,0,0], [0,0,0]]
     *   Output: 2
     *   Explanation: The Adjacency Matrix defines an
     *   undirected graph of 3 nodes (indexed 0 to 2).
     *   Where nodes 0 and 1 aee connected, and node 2
     *   is NOT connected. This forms two groups of
     *   nodes.
     *
     * Example 2:
     *   Input : AdjMatrix = [ [0,0,0], [0,0,0], [0,0,0]]
     *   Output: 3
     *   Explanation: The Adjacency Matrix defines an
     *   undirected graph of 3 nodes (indexed 0 to 2).
     *   There are no connected nodes, hence forming
     *   three groups.
     *
     * Example 3:
     *   Input : AdjMatrix = [[0,1,0], [1,0,0], [0,1,0]]
     *   Output: 1
     *   Explanation, The adjacency Matrix defined an
     *   undirected graph of 3 nodes (index 0 to 2).
     *   All three nodes are connected by at least one
     *   edge. So they form on large group.
     */

    public int numGroups(int[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        Map<Integer, List<Integer>> graph = new HashMap();
        int i = 0, j = 0;

        /*
         * Converting the Graph Adjacency Matrix to
         * an Adjacency List representation. This
         * sample code illustrates a technique to do so.
         */

        for (i = 0; i < numNodes; i++) {
            for (j = 0; j < numNodes; j++) {
                if (adjMatrix[i][j] == 1 && i != j) {
                    // Add AdjList for node i if not there
                    graph.putIfAbsent(i, new ArrayList());
                    // Add AdjList for node j if not there
                    graph.putIfAbsent(j, new ArrayList());

                    // Update node i adjList to include node j
                    graph.get(i).add(j);
                    // Update node j adjList to include node i
                    graph.get(j).add(i);
                }
            }
        }


        // YOUR CODE GOES HERE - you can add helper methods, you do not need
        // to put all code in this method.

        //array to track visited nodes
        boolean[] visited = new boolean[numNodes];
        //int to track group #
        int groups = 0;


        //loop that goes finds unvisited nodes, marks it and every node reachable from it as visited, then continues to search for unvisited nodes
        //every time it finds a new, it will be a new group of nodes entirely, so we increment the group counter
        for (int k = 0; k < numNodes; k++) {
            if (!visited[k]) {
                visited = traverse(k, graph, visited);
                groups++;
            }
        }

        return groups;
    }

    public boolean[] traverse(int k, Map<Integer, List<Integer>> graph, boolean[] visited) {
        //queue for breadth first traversal of graph
        Queue<Integer> queue = new LinkedList<Integer>();

        //start with the first unvisited node
        queue.add(k);
        visited[k] = true;

        int current;

        //populate queue with adjacent nodes, only if they have not been visited before, and set each one to be visited
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (graph.get(current) != null) {
                for (int i = 0; i < graph.get(current).size(); i++) {
                    if (!visited[graph.get(current).get(i)]) {
                        queue.add(graph.get(current).get(i));
                        visited[graph.get(current).get(i)] = true;
                    }
                }

            }
        }

        //return updated visited array to parent function
        return visited;
    }
}
