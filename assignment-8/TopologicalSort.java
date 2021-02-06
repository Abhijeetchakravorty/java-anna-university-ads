import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
 
public class TopologicalSort
{ 
	Stack<Node> stack;
	public TopologicalSort() {
		stack=new Stack<>();
	}
	static class Node {
		int data;
		boolean visited;
		List<Node> neighbours;
 
		Node(int data)
		{
			this.data=data;
			this.neighbours=new ArrayList<>();
 
		}
		public void nextNeighbour(Node neighbourNode)
		{
			this.neighbours.add(neighbourNode);
		}
		public List<Node> fetchNeighbours() {
			return neighbours;
		}
		public void setNeighbours(List<Node> neighbours) {
			this.neighbours = neighbours;
		}
		public String toString()
		{
			return ""+data;
		}
	}
 
	// Recursive toplogical Sort
	public  void toplogicalSorting(Node node)
	{
		List<Node> neighbours=node.fetchNeighbours();
		for (int i = 0; i < neighbours.size(); i++) {
			Node n=neighbours.get(i);
			if(n!=null && !n.visited)
			{
				toplogicalSorting(n);
				n.visited=true;
			}
		}
		stack.push(node);
	}
 
	public static void main(String arg[])
	{
 
		TopologicalSort topological = new TopologicalSort();
		Node nodeX =new Node(40);
		Node nodeA =new Node(10);
		Node nodeB =new Node(20);
		Node nodeC =new Node(30);
		Node nodeD =new Node(60);
		Node nodeE =new Node(50);
		Node nodeF =new Node(70); 
		nodeX.nextNeighbour(nodeA);
		nodeX.nextNeighbour(nodeB);
		nodeA.nextNeighbour(nodeC);
		nodeB.nextNeighbour(nodeA);
		nodeB.nextNeighbour(nodeC);
		nodeB.nextNeighbour(nodeD);
		nodeB.nextNeighbour(nodeE);
		nodeC.nextNeighbour(nodeD);
		nodeD.nextNeighbour(nodeF);
		nodeE.nextNeighbour(nodeF);
		System.out.println("Topological Sort Order:");
		topological.toplogicalSorting(nodeX);
		
		// Print contents of stack
		Stack<Node> resultStack=topological.stack;
		while (resultStack.empty()==false) {
			System.out.print(resultStack.pop() + " ");
		}
	}
}