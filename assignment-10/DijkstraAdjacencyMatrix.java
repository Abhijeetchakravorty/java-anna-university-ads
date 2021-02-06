import java.util.Scanner;

public class DijkstraAdjacencyMatrix {

        static class Graph {
                int vertices; // number of vertices
                int matrix[][]; // creating an empty matrix

                public Graph(int vertex) { // graph constructor
                        this.vertices = vertex; // setting number of vertices
                        matrix = new int[vertex][vertex]; // starting matrix with 
                }

                public void addEdge(int source, int destination, int weight) {

                        matrix[source][destination] = weight;

                        matrix[destination][source] = weight;
                }
                int getMinimumVertex(boolean[] mst, int[] key) {
                        int minKey = Integer.MAX_VALUE; //setting max value
                        int vertex = -1;
                        for (int i = 0; i < vertices; i++) {
                                if (mst[i] == false && minKey > key[i]) {
                                        minKey = key[i];
                                        vertex = i;
                                }
                        }
                        return vertex;
                }

                public void dijkstra_GetMinDistances(int sourceVertex) {
                        boolean[] spt = new boolean[vertices];
                        int[] distance = new int[vertices];
                        int INFINITY = Integer.MAX_VALUE;
                        for (int i = 0; i < vertices; i++) {
                                distance[i] = INFINITY;
                        }

                        distance[sourceVertex] = 0;

                        for (int i = 0; i < vertices; i++) {

                                int vertex_U = getMinimumVertex(spt, distance);

                                spt[vertex_U] = true;

                                for (int vertex_V = 0; vertex_V < vertices; vertex_V++) {
                                        if (matrix[vertex_U][vertex_V] > 0) {
                                                if (spt[vertex_V] == false && matrix[vertex_U][vertex_V] != INFINITY) {
                                                        int newKey = matrix[vertex_U][vertex_V] + distance[vertex_U];
                                                        if (newKey < distance[vertex_V]) distance[vertex_V] = newKey;
                                                }
                                        }
                                }
                        }
                        printDijkstra(sourceVertex, distance);
                }

                public void printDijkstra(int sourceVertex, int[] key) {
                        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
                        for (int i = 0; i < vertices; i++) {
                                System.out.println("Source Vertex: " + sourceVertex + " to vertex " + +i + " distance: " + key[i]);
                        }
                }
        }

        public static void main(String[] args) {
                System.out.print("Enter the number of nodes: ");
                Scanner in = new Scanner(System.in);
                int s = in.nextInt();
                int[] arr = new int[s];
                int vertices = s;
                Graph g = new Graph(vertices);
                for (int i = 0; i < s; i++) {
                        System.out.print(" enter the nodes : ");
                        int data = in.nextInt();
                        arr[i] = data;
                }
                int choice = 0;
                while (true) {
                        System.out.println("1. Initialize edges");
                        System.out.println("2. Perform DFS");
                        System.out.println("3. Press -1 to exit ");
                        System.out.println("Enter your choice :  ");
                        choice = in.nextInt();

                        if (choice == 1) {
                                int nod = 0;
                                int flag = 0;
                                System.out.println("press -1 to exit");
                                while (true) {
                                        System.out.print("enter the node :");
                                        nod = in.nextInt();
                                        for (int j = 0; j < s; j++) {
                                                if (arr[j] == nod) {
                                                        flag = 1;
                                                }
                                        }
                                        if (flag == 0) {
                                                System.out.println("Invalid data enter");
                                                break;
                                        }
                                        System.out.print("Enter its adjacent node : ");
                                        int adj = in.nextInt();

                                        System.out.print("enter the weight : ");
                                        int weight = in.nextInt();

                                        if (nod == -1) {
                                                break;
                                        }
                                        System.out.println("Press -1 for all nodes to exit or enter further edges: ");
                                        g.addEdge(nod, adj, weight);
                                }

                        } else if (choice == 2) {
                                System.out.println("Enter the nodes from which distance is calculated ");
                                int data = in.nextInt();
                                g.dijkstra_GetMinDistances(data);
                                System.out.println("");
                        } else if (choice == -1) {
                                break;
                        }
                }
        }
}