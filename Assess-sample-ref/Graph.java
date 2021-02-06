import java.util.*;
class Graph
{
    private int V;
    private LinkedList<Integer> adj[];


    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }
    void BFS(int s)
    {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {

            s = queue.poll();
            System.out.print((char)s+" ");
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[])
    {
        Graph g = new Graph((int)'E');

        g.addEdge((int)'A', (int)'B');
        g.addEdge((int)'A', (int)'C');
        g.addEdge((int)'B', (int)'C');
        g.addEdge((int)'C', (int)'A');
        g.addEdge((int)'C', (int)'D');
        g.addEdge((int)'D', (int)'D');

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex C)");

        g.BFS((int)'C');
    }
}