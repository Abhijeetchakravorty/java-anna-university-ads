import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class topology {
        static private class topologyNode {
                int nodeId;
                topologyNode next;

                public topologyNode(int id) {
                        this.nodeId = id;
                }
        }

        ArrayList < topologyNode > nodeList;

        public topology() {
                nodeList = new ArrayList < topologyNode > ();
        }

        public topologyNode addNode(int id) {
                topologyNode node = new topologyNode(id);
                nodeList.add(0, node);

                return nodeList.get(0);
        }
        public void addEdge(int id1, int id2) {
                boolean node1Found = false,
                        node2Found = false;
                topologyNode node1 = null,
                        node2 = null;

                for (int i = 0; i < nodeList.size(); i++) {
                        if (nodeList.get(i).nodeId == id1) {
                                node1Found = true;
                                node1 = nodeList.get(i);
                        }
                        if (nodeList.get(i).nodeId == id2) {
                                node2Found = true;
                                node2 = nodeList.get(i);
                        }
                        if (node1Found && node2Found) break;
                }

                if (!node1Found) {
                        node1 = this.addNode(id1);
                }

                if (!node2Found) {
                        node2 = this.addNode(id2);
                }

                topologyNode temp = new topologyNode(id2);
                temp.next = node1.next;
                node1.next = temp;

                return;
        }

        public topologyNode getNode(int id) {
                for (int i = 0; i < nodeList.size(); i++) {
                        if (id == nodeList.get(i).nodeId) {
                                return nodeList.get(i);
                        }

                }

                return null;
        }

        public void printTopoSortedNodes() {
                Hashtable inDegrees = new Hashtable < Integer,
                        Integer > ();
                ArrayList < topologyNode > zeroDegreeList = new ArrayList < topologyNode > ();
                ArrayList < topologyNode > nodes = this.nodeList;

                for (int i = 0; i < nodes.size(); i++) {
                        topologyNode temp = nodes.get(i);
                        temp = temp.next;
                        while (temp != null) {
                                int count = (inDegrees.get(temp.nodeId) == null) ? 0 : (int) inDegrees.get(temp.nodeId);
                                inDegrees.put(temp.nodeId, count + 1);
                                temp = temp.next;
                        }
                }

                for (int i = 0; i < nodes.size(); i++) {
                        topologyNode temp = nodes.get(i);
                        if (inDegrees.get(temp.nodeId) == null) {
                                zeroDegreeList.add(0, temp);
                        }

                }
                while (!zeroDegreeList.isEmpty()) {
                        topologyNode curr = zeroDegreeList.remove(0);
                        System.out.print(curr.nodeId + " ");

                        topologyNode temp = curr.next;
                        while (temp != null) {
                                int prevInDegree = (int) inDegrees.get(temp.nodeId);

                                inDegrees.put(temp.nodeId, prevInDegree - 1);
                                if (prevInDegree == 1) {
                                        zeroDegreeList.add(this.getNode(temp.nodeId));
                                }
                                temp = temp.next;
                        }
                }

        }

        public static void main(String[] args) {

                topology gr = new topology();
                Scanner sc = new Scanner(System.in);
                while (true) {
                        System.out.println("1. Insert data");
                        int ch = sc.nextInt();
                        if (ch == 1) {
                                System.out.print("ENTER the Source : ");
                                int src = sc.nextInt();
                                System.out.print("ENTER the Destination : ");
                                int dest = sc.nextInt();
                                gr.addEdge(src, dest);
                                gr.printTopoSortedNodes();
                        }
                }
        }
}