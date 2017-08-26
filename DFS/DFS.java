import java.io.*;
import java.util.*;


public class DFS {

	static int backEdge = 0;
	static int prev = 0;
	static LinkedList<Integer> finished = new LinkedList<Integer>();
	static LinkedList<Integer>[] graph;
	static int[] color;
	static int[] parent;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		String input = args[args.length-1];
		FileReader file = new FileReader(input);
		Scanner in = new Scanner(file);	
		int n = in.nextInt() + 1;
		int m = in.nextInt();

		graph = (LinkedList<Integer>[]) new LinkedList[n];
		color = new int[n];
		parent = new int[n];
		
		for (int i = 1; i < n; i++) {
			graph[i] = new LinkedList<Integer>();
			color[i] = -1;
			parent[i] = 0;
		}
		
		while (m > 0) {
			int v = in.nextInt();
			int w = in.nextInt();
			graph[v].add(w);
			m--;
		}
		
		DFS();
		                                                                     
	}


	private static void DFS() {
		for (int i = 1; i < graph.length; i++) {
			if (color[i] == -1) {
				RecDFS(i);
			}
		}
		
		if (backEdge != 0 && prev != 0) {
			System.out.println("1");
			Stack<Integer> stack = new Stack<Integer>();
			while (backEdge != prev) {
				stack.push(backEdge);
				backEdge = parent[backEdge];
			}
			stack.push(backEdge);
			
			while (!stack.isEmpty()) {
				System.out.print(stack.pop() + " ");
			}
		}
		else { 
			System.out.println("0");
		}		
	}


	private static void RecDFS(int i) {
		color[i] = 0;
		for (int j = 0; j < graph[i].size(); j++) {
			if (color[graph[i].get(j)] == -1) {
				parent[graph[i].get(j)] = i;
				RecDFS(graph[i].get(j));
			} else if (color[graph[i].get(j)] == 0) {
				backEdge = i;
				prev = graph[i].get(j);
			}
		}
		color[i] = 1;
		finished.add(i);
	}
}