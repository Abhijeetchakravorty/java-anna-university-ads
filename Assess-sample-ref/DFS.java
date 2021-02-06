import java.util.*;
class DFS
{
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    DFS(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w)
    {
        adj[v].add(w);
    }

    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;
        System.out.print(" -> "+(char)v);

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v)
    {
        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    public static void main(String args[])
    {
        DFS g = new DFS((int)'E');

        g.addEdge('A', 'B');
        g.addEdge('A', 'C');
        g.addEdge('B', 'C');
        g.addEdge('C', 'A');
        g.addEdge('C', 'D');
        g.addEdge('D', 'D');

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex C)");

        g.DFS((int)'C');
    }
}