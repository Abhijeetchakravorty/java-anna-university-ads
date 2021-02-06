import java.util.*;

public class DFS {
        private LinkedList < Integer > adjoinedList[];
        private boolean visit[];

        DFS(int vertices) {
                adjoinedList = new LinkedList[vertices];
                visit = new boolean[vertices];

                for (int i = 0; i < vertices; i++)
                        adjoinedList[i] = new LinkedList < Integer > ();
        }
        void addEdge(int src, int dest) {
                adjoinedList[src].add(dest);
        }
        void DFS(int vertex) {
                visit[vertex] = true;
                System.out.print(vertex + " ");

                Iterator < Integer > data = adjoinedList[vertex].listIterator();
                while (data.hasNext()) {
                        int adj = data.next();
                        if (!visit[adj])
                                DFS(adj);
                }
        }

        public static void main(String args[]) {
                System.out.print("Enter the number of nodes : ");
                Scanner sc = new Scanner(System.in);
                int s = sc.nextInt();
                int[] arr = new int[s];
                DFS g = new DFS(s);
                for (int i = 0; i < s; i++) {
                        System.out.print(" enter the nodes : ");
                        int data = sc.nextInt();
                        arr[i] = data;
                }
                int choice = 0;
                while (true) {
			System.out.print("1. Initialize edges");
			System.out.println();
			System.out.print("2. Remove Edge");
			System.out.println();
			System.out.print("3. Perform DFS: ");
			System.out.println();
			System.out.print("4. Press -1 to exit: ");
			System.out.println();
			System.out.println();
			System.out.print("Enter your choice :  ");
                        choice = sc.nextInt();

                        if (choice == 1) {

                                int nod = 0;
                                int flag = 0;
                                System.out.println("press -1 to exit");
                                while (true) {
                                        System.out.print("enter the node :");
                                        nod = sc.nextInt();
                                        for (int j = 0; j < s; j++) {
                                                if (arr[j] == nod) {
                                                        flag = 1;
                                                }
                                        }
                                        if (flag == 0) {
                                                System.out.println("Invalid data entered");
                                                break;
                                        }
                                        System.out.print("enter its adjacent node : ");
                                        int adj = sc.nextInt();

                                        if (nod == -1) {
                                                break;
                                        }
                                        System.out.println("  \t\t\t\t\t\t\t\t\t press -1 for all nodes to exit or enter further edges ");
                                        g.addEdge(nod, adj);
                                }

                        } else if (choice == 2) {
                                System.out.println("remove");

                        } else if (choice == 3) {
                                System.out.println("Enter the starting nodes ");
                                int start = sc.nextInt();
                                g.DFS(start);
                                System.out.println("");

                        } else if (choice == -1) {
                                break;
                        }
                }
	}
}