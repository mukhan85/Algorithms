package org.coursera.graph.directed;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Digraph {
	
	public int numVerts;
	public int numEdges;
	public boolean [] marked;
	private Queue<Integer> queue;
	private int [] distTo;
	
	private List<List<Integer>> adjList;
	
	public Digraph(int verts) {
		this.numVerts = verts;		
		initAdjList();
	}

	private void initAdjList() {
		this.queue = new LinkedList<Integer>();		
		this.marked = new boolean[this.numVerts];
		this.distTo = new int [this.numVerts];
		
		this.adjList = new ArrayList<List<Integer>>(this.numVerts);
		for(int i =0 ; i < this.numVerts; ++i) {
			this.adjList.add(new ArrayList<Integer>());
		}
	}
	
	public Digraph(File inputFile) {
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.numVerts = sc.nextInt();
		this.numEdges = sc.nextInt();
		
		initAdjList();
		
		for(int i = 0; i < this.numEdges; ++i) {
			int fromEdge = sc.nextInt();
			int toEdge = sc.nextInt();			
			addEdge(fromEdge, toEdge);
		}
	}
	
	void addEdge(int fromEdge, int toEdge) {
		this.adjList.get(fromEdge).add(toEdge);
	}
	
	public Iterable<Integer> getAdjList(int vert) {
		return this.adjList.get(vert);
	}
	
	public Digraph reverse() {
		Digraph revGraph = new Digraph(this.numVerts);
		for(int currentVert = 0; currentVert < this.numVerts; ++currentVert) {
			for(int nextAdjVert : getAdjList(currentVert)) {
				revGraph.addEdge(nextAdjVert, currentVert);
			}
		}
		
		return revGraph;
	}
	
	public void bfs(int sourceVert) {
		this.marked = new boolean[this.numVerts];
		this.queue.add(sourceVert);
		this.distTo[sourceVert] = 0;
		
		while(!queue.isEmpty()) {
			int currentVert = queue.poll();
			for(int nextAdjVert : getAdjList(currentVert)) {
				if(!this.marked[nextAdjVert]) {
					queue.add(nextAdjVert);
					this.marked[nextAdjVert] = true;
					this.distTo[nextAdjVert] = this.distTo[currentVert] + 1;
				}				
			}
		}		
	}
		
	public void dfs(int sourceVert) {
		for(int nextAdjVert : getAdjList(sourceVert)) {
			if(!marked[nextAdjVert]) {
				this.marked[nextAdjVert] = true;
				dfs(nextAdjVert);
			}
		}
	}
	
	public boolean isVisited(int source) {
		return this.marked[source];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.numVerts + " vertices, " + this.numEdges + " edges\n");
		for(int currentVert = 0; currentVert < this.numVerts; ++currentVert) {
			sb.append(currentVert + ": ");
			for(int nextAdjVert : getAdjList(currentVert)) {
				sb.append(nextAdjVert + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// ===============   Getters and Setters   ===============
	public int getNumVerts() {
		return numVerts;
	}

	public void setNumVerts(int numVerts) {
		this.numVerts = numVerts;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(int numEdges) {	
		this.numEdges = numEdges;
	}

	public int getDistance(int dest) {
		return this.distTo[dest];
	}	
}

