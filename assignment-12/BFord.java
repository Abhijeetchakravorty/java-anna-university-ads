/* Bellman Ford Algorithm*/
import java.util.Arrays;
import java.util.List;
class EdgeData {
        int source, dest, weight;

        public EdgeData(int source, int dest, int weight) {
                this.source = source;
                this.dest = dest;
                this.weight = weight;
        }
}

public class BFord {
        static void printPathTraversal(int parent[], int vertex) {
                if (vertex < 0)
                        return;

                printPathTraversal(parent, parent[vertex]);
                System.out.print(vertex + " ");
        }

        public static void bf(List < EdgeData > edges, int source, int N) {
                int distance[] = new int[N];
                int parent[] = new int[N];
                Arrays.fill(distance, Integer.MAX_VALUE);
                distance[source] = 0;
                Arrays.fill(parent, -1);
                for (int i = 0; i < N - 1; i++) {
                        for (EdgeData edge: edges) {
                                int u = edge.source;
                                int v = edge.dest;
                                int w = edge.weight;

                                if (distance[u] + w < distance[v]) {
                                        distance[v] = distance[u] + w;
                                        parent[v] = u;
                                }
                        }
                }

                for (EdgeData edge: edges) {
                        int u = edge.source;
                        int v = edge.dest;
                        int w = edge.weight;

                        if (distance[u] + w < distance[v]) {
                                System.out.println("Negative Weight Cycle Found!");
                                return;
                        }
                }

                for (int i = 0; i < N; i++) {
                        System.out.print("Distance of vertex " + i + " from the " + "source is " + distance[i] + ". It's path is [ ");
                        printPathTraversal(parent, i);
                        System.out.println("]");
                }
        }

        public static void main(String[] args) {
                List < EdgeData > edges = Arrays.asList(
                        new EdgeData(0, 1, -1), new EdgeData(0, 2, 4),
                        new EdgeData(1, 2, 3), new EdgeData(1, 3, 2),
                        new EdgeData(1, 4, 2), new EdgeData(3, 2, 5),
                        new EdgeData(3, 1, 1), new EdgeData(4, 3, -3)
                );

                final int N = 5;

                int source = 0;

                bf(edges, source, N);
        }
}