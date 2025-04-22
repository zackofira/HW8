/******************************************************************
 *
 *   Zach Kofira / Section 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor 
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */ 
  
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
             "Invalid vertex index: " + vertexIndex);
    }
  }


  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */ 
  
  public void printGraph() {
    System.out.println(
         "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }


  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * 
   */
  
  public int findRoot() {

    // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME/SECTION AT TOP OF FILE

    /*
    DISCLAIMER: This solution isn't very good, it runs on O(n^3) time.  Using a hash table could bring it down to n^2 + n, or just O(n^2).
     */

    boolean couldRoot;
    int root = -1;

    //outer for loop selects a vertex to check roothood of
    for (int i = 0; i < numVertices; i++) {
      couldRoot = true;

      //double loop checks every destination against current vertex, if it finds a match then it can't be the root
      for (int j = 0; j < numVertices; j++) {
        for (Integer dest : adjListArr[j]) {
          if (dest == i) {
            couldRoot = false;
          }
        }
      }
      //if a root vertex is found for the first time, it becomes the new root.  If it isnt the first time, root is -2 to indicate multiple roots.
      if (couldRoot) {
        if (root == -1) {
          root = i;
        } else {
          root = -2;
        }

      }
    }

    //if root is invalid (none or too many), make sure its -1, else get the value of the root and return it
    if (root < 0) {
      root = -1;
    } else {
      root = vertexValues.get(root);
    }
    return root;
  } 
}
