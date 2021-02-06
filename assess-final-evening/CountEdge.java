public class CountEdge {

        int vertices; // Creating vertices variable
        int matrix[][]; // Creating matrix variable

        CountEdge(int vertex) { /// Count Edge counstructor
                this.vertices = vertex; // Setting vertex to vertices variable
                matrix = new int[vertex][vertex]; // Setting each row and column value to matrix
                for (int i = 0; i < vertices; i++) { // Iterating over vertices
                        for (int j = 0; j < vertices; j++) { // iterating again over vertices
                                matrix[i][j] = 0; // Setting matrix value as 0 for starting purposes
                        }
                }
        }

        void addEdge(int src, int dest) {
                matrix[src][dest] = 1; // adding our edge
        }

        void countData() {
                int counter = 0; // starting counter is 0
                for (int i = 0; i < vertices; i++) { // starting iteration of vertices again
                        for (int j = 0; j < vertices; j++) { // inner loop iteration of vertices again
                                if (matrix[i][j] == 1) { // if matrix row and column is one it means that the vertices are connected and is an edge of this graph
                                        counter = counter + 1; // increment of counter value
                                }
                        }
                }
                System.out.println("Number of edges are: " + counter); // number of edges printing
        }



        public static void main(String[] args) {
                CountEdge obj = new CountEdge(6);
                // Here data is
                // a=0 b=1 c=2 d=3 e=4 f=5
                
                //  adding edges with our values
                obj.addEdge(0, 1); 
                obj.addEdge(1, 2); 
                obj.addEdge(2, 4);
                obj.addEdge(4, 3);
                obj.addEdge(3, 1);
                obj.addEdge(4, 5);

                obj.countData();
        }
}