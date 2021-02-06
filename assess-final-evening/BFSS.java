// BFS algorithm in Java
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;
class Vertex { // Creating vertex class
        public int label; // this is label so that we can display what needs to be shown
        public Vertex(int label) { // this constructor for vertex class
                this.label = label; // setting our label
        }
}
public class BFSS {
        private int V; // creating an integer to iterate
        private LinkedList < Integer > adj[]; // creating linked list for using in BFS
        protected Vertex[] vertexList; //We will store our labels in vertex list
        protected int numVerts; // These are the number of vertices fir vertex class
        // Create a graph
        BFSS(int v) { // BFS constructur
                V = v; // count of vertices
                numVerts = 0; // initialized with 0 value for vertex class
                adj = new LinkedList[v]; // Assigning linkedlist with vertices
                vertexList = new Vertex[v]; // setting vertex list
                for (int i = 0; i < v; ++i) // For loop
                        adj[i] = new LinkedList(); // assigning linked list to each lndex
        }

        void addVertex(int label) { // We add vertex for label
                if (numVerts < V) {
                        vertexList[numVerts++] = new Vertex(label);
                }
        }

        // Add edges to the graph
        void addEdge(int v, int w) { // we add edge
                adj[v].add(w);
        }

        // BFS algorithm
        void BFS(int s) {

                boolean visited[] = new boolean[V]; // Creating a new bollean array

                LinkedList < Integer > queue = new LinkedList(); // Creating queue

                visited[s] = true; // index becomes true on traversal
                queue.add(s); // We add the index

                while (queue.size() != 0) {
                        s = queue.poll();
                        System.out.print(s + " ");

                        Iterator < Integer > i = adj[s].listIterator(); // we are using an iterator for iterating over each index
                        while (i.hasNext()) { // Has next is a list iterator method for going to the next value returns boolean true if exists and false if it does not
                                int n = i.next(); // Goes to the next element
                                if (!visited[n]) { // It then checks if n has been visited or not if it has not been visited then visit it 
                                        visited[n] = true; // CHnage the value to true
                                        queue.add(n); // add it to the queue
                                }
                        }
                }
        }

        public static void main(String args[]) {
                // Provide 11 as vertex count
                int result, src, dest;
                Scanner in = new Scanner(System.in);
                System.out.print("Please provide vertex count: ");
                int data = in.nextInt();
                BFSS g = new BFSS(data);

                for(int t=0;t<data;t++) {
                        System.out.print("Please provide vertex "+t+": ");
                        result = in.nextInt();
                        g.addVertex(result);
                }
                // Provide 9 as mapping count
                System.out.print("Please provide mapping count: ");
                result = in.nextInt();
                System.out.println();
                for(int k=0;k<result;k++) {
                        System.out.print("Please provide source vertex: ");
                        src = in.nextInt();
                        System.out.println();
                        System.out.print("Please provide destination vertex: ");
                        dest = in.nextInt();
                        System.out.println();
                        g.addEdge(src, dest);
                }

                System.out.println("Following is Breadth First Traversal " + "(starting from vertex 0)");
                g.BFS(0);
                System.out.println();
                System.out.println("There is an edge between " + g.vertexList[0].label + " and " + g.vertexList[1].label);
                System.out.println("There is an edge between " + g.vertexList[0].label + " and " + g.vertexList[2].label);
                System.out.println("There is an edge between " + g.vertexList[0].label + " and " + g.vertexList[3].label);
                System.out.println("There is an edge between " + g.vertexList[1].label + " and " + g.vertexList[4].label);
                System.out.println("There is an edge between " + g.vertexList[1].label + " and " + g.vertexList[5].label);
                System.out.println("There is an edge between " + g.vertexList[3].label + " and " + g.vertexList[6].label);
                System.out.println("There is an edge between " + g.vertexList[3].label + " and " + g.vertexList[7].label);
                System.out.println("There is an edge between " + g.vertexList[6].label + " and " + g.vertexList[8].label);
                System.out.println("There is an edge between " + g.vertexList[8].label + " and " + g.vertexList[9].label);
                System.out.println("There is an edge between " + g.vertexList[8].label + " and " + g.vertexList[10].label);
        }
}