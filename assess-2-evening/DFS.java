import java.util.*;
class DFS {
        private int V;
        private LinkedList < Integer > adj[];

        @SuppressWarnings("unchecked")
        DFS(int v) {
                V = v;
                adj = new LinkedList[v];
                for (int i = 0; i < v; ++i)
                        adj[i] = new LinkedList();
        }

        void addFinalEdge(int v, int w) {
                adj[v].add(w);
        }

        void Utility(int v, boolean visited[]) {
                visited[v] = true;
                System.out.print(" -> " + (char) v);

                Iterator < Integer > i = adj[v].listIterator();
                while (i.hasNext()) {
                        int n = i.next();
                        if (!visited[n])
                                Utility(n, visited);
                }
        }

        void DFS(int v) {
                boolean visited[] = new boolean[V];
                Utility(v, visited);
        }

        public static void main(String args[]) {
                DFS g = new DFS((int)'H');

                g.addFinalEdge('A', 'B');
                g.addFinalEdge('A', 'D');
                g.addFinalEdge('B', 'E');
                g.addFinalEdge('E', 'G');
                g.addFinalEdge('E', 'C');
                g.addFinalEdge('D', 'F');
                g.addFinalEdge('F', 'G');
                g.addFinalEdge('F', 'C');
                g.addFinalEdge('C', 'A');
                g.addFinalEdge('C', 'G');

                System.out.println("Following is Depth First Traversal starting from vertex A");

                g.DFS((int)'A');
        }
}