package graphs;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph
{
	int nodes;
	LinkedList<Integer> edges[];

	public Graph(int n)
	{
		this.nodes = n;
		edges = new LinkedList[nodes];
		for (int i = 0; i < nodes; ++i)
			edges[i] = new LinkedList<Integer>();

	}

	private void addEdge(int i, int j)
	{
		edges[i].add(j);

	}

	private void BFS(int i)
	{
		boolean visited[] = new boolean[nodes];

		LinkedList<Integer> output = new LinkedList<Integer>();
		visited[i] = true;
		output.add(i);

		while (output.size() != 0)
		{

			i = output.poll();
			System.out.println(i + " ");
			Iterator<Integer> iterator = edges[i].iterator();
			while (iterator.hasNext())
			{

				int newNode = iterator.next();

				if (!visited[newNode])
				{
					output.add(newNode);
					visited[newNode] = true;
				}
			}

		}

	}

	public static void main(String[] args)
	{
		Graph g = new Graph(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

		g.BFS(2);

	}

}
