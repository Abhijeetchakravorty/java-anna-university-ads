// Floyd Warshall Algorithm in Java
class FloydWarshallAlgo {
        final static int INF = 9999, nV = 4; // nV is the number of vertex and INF is infinity we can also use INTEGER.MAX_VALUE instead

        // Implementing floyd warshall algorithm
        void floydWarshallAlgo(int graph[][]) {
                int matrix[][] = new int[nV][nV];
                int i, j, k;

                for (i = 0; i < nV; i++)
                        for (j = 0; j < nV; j++)
                                matrix[i][j] = graph[i][j];

                for (k = 0; k < nV; k++) {
                        for (i = 0; i < nV; i++) {
                                for (j = 0; j < nV; j++) {
                                        if (matrix[i][k] + matrix[k][j] < matrix[i][j])
                                                matrix[i][j] = matrix[i][k] + matrix[k][j];
                                }
                        }
                }
                printMatrix(matrix);
        }

        void printMatrix(int matrix[][]) {
                for (int i = 0; i < nV; ++i) {
                        for (int j = 0; j < nV; ++j) {
                                if (matrix[i][j] == INF)
                                        System.out.print("INF ");
                                else
                                        System.out.print(matrix[i][j] + "  ");
                        }
                        System.out.println();
                }
        }

        public static void main(String[] args) {
                int graph[][] = {
                        {
                                0,
                                3,
                                INF,
                                5
                        },
                        {
                                2,
                                0,
                                INF,
                                4
                        },
                        {
                                INF,
                                1,
                                0,
                                INF
                        },
                        {
                                INF,
                                INF,
                                2,
                                0
                        }
                };
                FloydWarshallAlgo a = new FloydWarshallAlgo();
                a.floydWarshallAlgo(graph);
        }
}