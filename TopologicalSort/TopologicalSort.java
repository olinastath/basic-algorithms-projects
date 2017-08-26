import java.io.*;
import java.util.*;

public class TopologicalSort{

	public static void main(String[] args) throws IOException {
		String input = args[args.length-1];
		FileReader file = new FileReader(input);
		Scanner in = new Scanner(file);	
		
		int n = in.nextInt();
		int m = in.nextInt();
		Vertex v, w;
		Graph G = new Graph(n);
		G.edges = m;
	
		while (m > 0) {
			v = new Vertex(in.nextInt());
			w = new Vertex(in.nextInt());
			G.addEdge(v, w);
			m--;
		}

		G.topSort();
	}
}

class minHeap {
	Vertex[] Heap;
	int size;
	int maxSize;

	public minHeap(int maxSize){
		this.maxSize = maxSize;
		size = 0;
		Heap = new Vertex[this.maxSize];
		Heap[0] = new Vertex(0);
	}

	public int findParent(int i) {
		if (i%2 == 0) {
			return (i-1) / 2;
		} else {
			return i/2;	
		}
	}

	public int findLeftChild (int i) {
		int index = 2*i + 1;
		if (index >= size) {
			return -1;
		} else {
			return index;
		}
	}

	public int findRightChild (int i) {
		int index = 2*i + 2;
		if (index >= size) {
			return -1;
		} else {
			return index;
		}
	}

	public boolean hasChildren (int i) {
		if (i >= size/2 && i <= size) {
			return false;
		} else {
			return true;
		}
	}

	public void insert(Vertex v) {
		size++;
		int i = size - 1;
		Heap[i] = v;
		
		while (Heap[i].key < Heap[findParent(i)].key) {
			swap(i, findParent(i));
			i = findParent(i);
		}
	}

	public Vertex pop() {
		Vertex min = Heap[0];
		Heap[0] = Heap[size - 1];
		Heap[size - 1] = null;
		size--;
		balance();		
		return min;
	}

	public void balance() {
		balance(0);
	}

	private void balance (int i) {
		int left, right;
		if (hasChildren(i)) {
			left = findLeftChild(i);
			right = findRightChild(i);

			if (i < this.size) {
				if (right != -1) {
					// compare both children
					if (Heap[i].key > Heap[left].key || Heap[i].key > Heap[right].key) {
						if (Heap[left].key <= Heap[right].key) {
							swap(i, left);
							balance(left);
						} else {
							swap(i, right);
							balance(right);
						}
					} 
				} else {
					// compare left child
					if (Heap[i].key > Heap[left].key) {
						swap(i, left);
						balance(left);
					}
				}
			}
		}
	}

	public void swap(int a, int b) {
		Vertex temp = Heap[a];
		Heap[a] = Heap[b];
		Heap[b] = temp;
	}
}

class Graph {
	int size; // number of vertices
	Vertex[] adjacencyList; // array of Vertices
	int edges;

	public Graph(int size) {
		this.size = size;
		adjacencyList = new Vertex[size];
		for (int i = 0; i < size; i++) {
			adjacencyList[i] = new Vertex(i+1); // create n number of Vertices from 1 to n;
		}
	}

	public void addEdge(Vertex v, Vertex w) {
		adjacencyList[v.key - 1].successors.add(w);
		adjacencyList[w.key - 1].inDeg++;
	}

	public void topSort() {
		Vertex next, temp;
		minHeap zeroVertices = new minHeap(this.size);
		// add vertices of G with in-degree 0 to zeroVertices
		for (int i = 0; i < adjacencyList.length; i++) {
			if (adjacencyList[i].inDeg == 0) {
				zeroVertices.insert(adjacencyList[i]);
			}
		}

		if (zeroVertices.size == 0) {
			System.out.println("-1");
			return;
		}

		Vertex[] orderedVertices = new Vertex[this.size];
		int index = 0;
		while (zeroVertices.size > 0) {
			next = zeroVertices.pop(); // need to adjust minHeap to be of vertices
			orderedVertices[index] = next;
			index++;

			for (int i = 0; i < adjacencyList[next.key - 1].successors.size(); i++) {
				temp = adjacencyList[next.key - 1].successors.get(i);
				adjacencyList[temp.key - 1].inDeg--;
				if (adjacencyList[temp.key - 1].inDeg == 0) {
					zeroVertices.insert(adjacencyList[temp.key - 1]);
				}
				this.edges--;
			}
		}

		if (edges != 0) {
			System.out.println("-1");
		} else {
			for (int i = 0; i < orderedVertices.length; i++) {
				System.out.print(orderedVertices[i].key + " ");
			}
		}
	}
}

class Vertex {
	int key;
	int inDeg;
	LinkedList<Vertex> successors;

	public Vertex(int key) {
		this.key = key;
		this.inDeg = 0;
		this.successors = new LinkedList<Vertex>();
	}
}