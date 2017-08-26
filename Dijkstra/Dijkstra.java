import java.io.*;
import java.util.*;

public class Dijkstra {

	static final int inf = Integer.MAX_VALUE;
		
	public static void main(String[] args) throws IOException {
		String input = args[args.length-1];
		FileReader file = new FileReader(input);
		Scanner in = new Scanner(file);	

		int n = in.nextInt() + 1;
		int m = in.nextInt();
		
		Edge[] graph = new Edge[n];
		int[] distance = new int[n];

		for (int i = 1; i < n; i++) {
			graph[i] = new Edge(i, 0, 0);
			distance[i] = inf;
		}

		while (m > 0) {
			int v = in.nextInt();
			int u = in.nextInt();
			int water = in.nextInt();
			Edge w = graph[v];
			if (graph[v].second == 0) {
				graph[v] = new Edge(v, u, water);
			} else {
				while (w.next != null) {
					w = w.next;
				}
				w.next = new Edge(v, u, water);
			}
			m--;
		}
		int result = RedBlue(graph, 1, n - 1);
				
		if (result == inf) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}
				
	}
	
	public static int RedBlue (Edge[] G, int source, int sink) {
		int newWeight;
		Edge[] D = new Edge[2*G.length - 1];
		for (int i = 1; i < G.length; i++) {
			D[i] = new Edge(G[i].first, 0, 0);
			D[i + G.length - 1] = new Edge(G[i].first, 0, 0);
		}
		
		for (int i = 1; i < G.length - 1; i++) {
			Edge e = G[i];
			while (e != null) {
				newWeight = 1;
				if (e.weight == 1) {
					newWeight = 0;
					addEdge(D, e.first, e.second + G.length - 1, newWeight);
				}
				addEdge(D, e.first, e.second, newWeight);
				addEdge(D, e.first + G.length - 1, e.second + G.length - 1, newWeight);
				e = e.next;
			}
		}
		int[] distance = Dijkstra(D, source);
		return distance[sink + G.length - 1];
	}
	
	public static int[] Dijkstra(Edge[] G, int source) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		int[] d = new int[G.length];
		
		for (int i = 1; i < G.length; i++) {
			d[i] = inf;
		}
		
		d[source] = 0;
		
		queue.add(source);
		
		while (!queue.isEmpty()) {
			int u = queue.poll();
			Edge neighbor = G[u];
			while (neighbor != null) {
				int v = neighbor.second;
				if (d[u] + weight(G, u,v) < d[v]) {
					if (d[v] != inf) {
						queue.remove(v);
					}
					d[v] = d[u] + weight(G, u,v);
					queue.add(v);
				}
				neighbor = neighbor.next;
			}
		}
		return d;
	}
	
	private static int weight(Edge[] G, int u, int v) {
		Edge w = G[u];
		while (w.second != v) {
			w = w.next;
			if (w == null) {
				return -1;
			}
		}
		return w.weight;
	}

	public static void addEdge(Edge[] D, int v, int u, int weight) {
		Edge w = D[v];
		if (D[v].second == 0) {
			D[v] = new Edge(v, u, weight);
		} else {
			while (w.next != null) {
				w = w.next;
			}
			w.next = new Edge(v, u, weight);
		}
	}
}

class Edge {
	int first;
	int second;
	int weight;
	Edge next;
	
	public Edge(int first, int second, int water) {
		this.first = first;
		this.second = second;
		this.weight = water;
		this.next = null;
	}
	
	public Edge(int first, int second, int water, Edge next) {
		this.first = first;
		this.second = second;
		this.weight = 0;
		this.next = next;
	}
}