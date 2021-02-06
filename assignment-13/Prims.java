import java.util.Arrays;
import java.util.Scanner;

class Prims {
        int V, arr[][];

        public Prims(int v) {
                this.V = v;
                arr = new int[v][v];
                for (int i = 0; i < v; i++) {
                        for (int j = 0; j < v; j++) {
                                arr[i][j] = 0;
                        }
                }
        }

        void add(int src, int dest, int weight) {
                arr[src][dest] = weight;
                arr[dest][src] = weight;

        }

        public void Prim() {
                int INF = 9999999;
                int no_edge;
                boolean[] selected = new boolean[V];

                Arrays.fill(selected, false);
                no_edge = 0;
                selected[0] = true;


                System.out.println("Edge : Weight");

                while (no_edge < V - 1) {
                        int min = INF;
                        int x = 0;
                        int y = 0;

                        for (int i = 0; i < V; i++) {
                                if (selected[i] == true) {
                                        for (int j = 0; j < V; j++) {
                                                // not in selected and there is an edge
                                                if (!selected[j] && arr[i][j] != 0) {
                                                        if (min > arr[i][j]) {
                                                                min = arr[i][j];
                                                                x = i;
                                                                y = j;
                                                        }
                                                }
                                        }
                                }
                        }
                        System.out.println(x + " - " + y + " :  " + arr[x][y]);
                        selected[y] = true;
                        no_edge++;
                }
        }

        public static void main(String[] args) {
                System.out.print("Enter the number of vertex : ");
                Scanner scan = new Scanner(System.in);
                int V = scan.nextInt();
                System.out.print("Enter Number of Edges : ");
                int e = scan.nextInt();
                Prims g = new Prims(V);
                for (int i = 0; i < e; i++) {
                        System.out.println("--------- " + i + " -Edge-------- ");
                        System.out.print("Enter source node : ");
                        int s = scan.nextInt();
                        System.out.print("Enter the destination : ");
                        int d = scan.nextInt();
                        System.out.print("Enter Weight : ");
                        int w = scan.nextInt();
                        g.add(s, d, w);
                }
                g.Prim();
        }
}