import java.util.*;

public class DA {
    private static final graphs.Edge[] GRAPH = {
            // Distance from node "a" to node "b" is 7.
            // In the current graphs there is no way to move the other way (e,g, from "b" to "a"),
            // a new edge would be needed for that
            new graphs.Edge("s", "a", 2),
            new graphs.Edge("a", "b", 3),
            new graphs.Edge("b", "c", 1),
            new graphs.Edge("a", "d", 5),
            new graphs.Edge("b", "d", 1),
    };
    private static final String START = "s";
    private static final String END = "d";

    public static void main(String[] args) {
        graphs g = new graphs(GRAPH);
        g.dijkstra(START);
        g.printPath(END);
        // g.printAllPaths();
    }
}

class graphs {
    private final Map<String, Vertex> graph;
    public static class Edge {
        public final String v1, v2;
        public final int dist;

        public Edge(String v1, String v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public int dist = Integer.MAX_VALUE;
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.dist);
            }
        }

        public int compareTo(Vertex other) {
            if (dist == other.dist)
                return name.compareTo(other.name);

            return Integer.compare(dist, other.dist);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            if (!super.equals(object)) return false;

            Vertex vertex = (Vertex) object;

            if (dist != vertex.dist) return false;
            if (name != null ? !name.equals(vertex.name) : vertex.name != null) return false;
            if (previous != null ? !previous.equals(vertex.previous) : vertex.previous != null) return false;
            if (neighbours != null ? !neighbours.equals(vertex.neighbours) : vertex.neighbours != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + dist;
            result = 31 * result + (previous != null ? previous.hashCode() : 0);
            result = 31 * result + (neighbours != null ? neighbours.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "(" + name + ", " + dist + ")";
        }
    }
    public graphs(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        for (Edge e : edges) {
            if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
            if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
        }

        for (Edge e : edges) {
            graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
            // graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }
    }



    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("graphs doesn't contain start vertex \"%s\"%n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();
        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {
            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE)
                break;
            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) {
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("graphs doesn't contain end vertex \"%s\"%n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }
    // just foor fun
    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

}