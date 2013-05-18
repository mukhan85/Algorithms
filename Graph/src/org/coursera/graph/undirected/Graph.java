package org.coursera.graph.undirected;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
	
	private final int vertices;
	private int edges;

	private boolean[] marked;
	
	private int components = 0;
	private int [] componentID;
	
	private List<List<Integer>> adjVertices;
	
	private int [] edgeTo;
	
	public Graph(int v) {
		this.vertices = v;
		this.marked = new boolean[this.vertices];
		this.componentID = new int [this.vertices];
		this.edgeTo = new int [this.vertices];
		
		this.adjVertices = new ArrayList<List<Integer>>(this.vertices);
		
		for(int i = 0; i < this.vertices; ++i) {
			adjVertices.add(new ArrayList<Integer>());
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
}
