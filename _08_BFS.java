import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class _08_BFS {

	int[][] mat;
	int[] color;
	int[] distance;
	int[] father;
	int V; //numberOfVertecies
	
	public _08_BFS(int[][] matrix)
	{
		mat = matrix;
		V = mat.length;
		color = new int[V];
		distance = new int[V];
		father = new int[V];
	} 
	// 0 - not reached yet - WHITE
	// 1 - In Queue - GRAY
	// 2 - Finished - BLACK
	private void Initialize() {
		for (int v = 0; v < V; v++) {
			color[v] = 0;
			distance[v] = 0;
			father[v] = -1;
		}	
	}
	
	private void Run_BFS(int s) {
		Queue<Integer> q = new LinkedList<>();
		
		Initialize();
		
		q.add(s);
		color[s] = 1;
		distance[s] = 0;
		father[s] = -1;
		
		int v;
		while (!q.isEmpty())
		{
			v = q.poll();
			for (int u = 0; u < V; u++)
			{
				if (color[u] == 0 && mat[v][u] == 1)
				{
					color[u] = 1; // paint Gray
					distance[u] = distance[v]+1;
					father[u] = v;
					q.add(u);
				}
			}
			color[v] = 2;
		}
	}



	public boolean IsConnected()
	{
		for (int v = 0; v < V; v++)
		{
			if (color[v] == 0)
				return false;
		}
		return true;
	}

	public boolean IsTherePathBetween_v_and_u(int v, int u)
	{
		Run_BFS(v);
		if (color[u] == 2)
			return true;
		return false;
	}
	
	//after checking IsTherePathBetween_v_and_u
	public static void printPathBetween_v_and_u(int v, int u, father)
	{
		if (v == s)
			print s;
		else if (parent[v] == -1)
			print "no path";
		else
			print_pathBetween_v_and_u(v,u,father[v]);
	}
	
	public static void main(String[] args) {
		 int[][] graph = {{0,0,0,1,0,0},
				          {0,0,0,1,1,1},
				          {0,0,0,1,1,1},
				          {1,1,1,0,0,0},
				          {0,1,1,0,0,0},
				          {0,1,1,0,0,0}};
		 
		 _08_BFS bfs = new _08_BFS(graph);
		 System.out.println(bfs.IsTherePathBetween_v_and_u(5,0));
		 printPathBetween_v_and_u(5, 0);
	}
}
