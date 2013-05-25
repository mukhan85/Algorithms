package org.coursera.graph.undirected;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	private static final int INFINITY = Integer.MAX_VALUE;

	private final int vertices;
	
	private int edges;
	
	private boolean[] marked;
	
	private int components = 0;
	private int [] componentID;
	
	private List<List<Integer>> adjVertices;
	private Queue<Integer> queue;
	private int [] distTo;
	private int [] edgeTo;
	
	public Graph(int v) {
		this.vertices = v;
		this.marked = new boolean[this.vertices];
		this.componentID = new int [this.vertices];
		this.edgeTo = new int [this.vertices];
		this.distTo = new int[this.vertices];
		
		this.adjVertices = new ArrayList<List<Integer>>(this.vertices);
		this.queue = new LinkedList<Integer>();
		
		for(int i = 0; i < this.vertices; ++i) {
			adjVertices.add(new ArrayList<Integer>());
			this.distTo[i] = INFINITY;
		}
	}
	
	public Iterable<Integer> getAdjVertices(int vert) {
		return this.adjVertices.get(vert);
	}

	public int getNumVertices() {
		return this.vertices;
	}

	public void addEdge(int fromVert, int toVert) {
		this.adjVertices.get(fromVert).add(toVert);
		this.adjVertices.get(toVert).add(fromVert);
		++edges;	
	}

	public int getNumEdges() {
		return this.edges;
	}

	/** BreadthFirstSearch algorithm implementation.
	 * @param sourceVert
	 */
	public void bfs(int sourceVert) {
		// 1. Put @sourceVert into the queue.
		// 2. Retrieve a vertex from the queue.
		// 3. Mark all its Adjacent vertices as MARKED.
		// 4. Put all Adjacent vertices into the queue.
		this.marked[sourceVert] = true;
		this.queue.add(sourceVert);
		this.distTo[sourceVert] = 0;
		
		while(!queue.isEmpty()) {
			
			Integer nextVertex = queue.poll();
			Iterable<Integer> adjVerts = this.getAdjVertices(nextVertex.intValue());
			
			for(int nextAdjVertex : adjVerts) {
				if(!this.marked[nextAdjVertex]) {
					this.edgeTo[nextAdjVertex] = nextVertex;
					this.marked[nextAdjVertex] = true;
					this.distTo[nextAdjVertex] = distTo[nextVertex] + 1;
					
					queue.add(nextAdjVertex);
				}
			}
		}
	}
	
	public void dfs(int sourceVert) {
		marked[sourceVert] = true;
		componentID[sourceVert] = this.components;
		
		for(Integer eachAdjVert : this.adjVertices.get(sourceVert)) {
			if(!marked[eachAdjVert]) {
				marked[eachAdjVert] = true;
				edgeTo[eachAdjVert] = sourceVert;
				
				dfs(eachAdjVert);
			}
		}
	}
	
	public int getDistanceTo(int dest) {
		return this.distTo[dest];
	}

	public Iterable<Integer> getPath(int dest) {
		Stack<Integer> path = new Stack<Integer>();
		int x = edgeTo[dest];
		path.push(dest);
		path.push(x);
		
		while(x != 0) {
			x = edgeTo[x];
			path.push(x);
		}		
		return path;
	}

	public boolean isConnected(int fromVert, int toVert) {
		return this.componentID[fromVert] == this.componentID[toVert];
	}

	public int getNumConnectedComponents() {
		return this.components;
	}

	public void componentCount() {
		for(int i = 0; i < this.vertices; ++i) {
			if(!marked[i]) {
				dfs(i);
				++this.components;
			}
		}
	}

	public boolean[] getMarked() {
		return marked;
	}

	public void setMarked(boolean[] marked) {
		this.marked = marked;
	}
}
