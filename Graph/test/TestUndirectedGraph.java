import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import junit.framework.Assert;

import org.coursera.graph.undirected.Graph;
import org.junit.Before;
import org.junit.Test;


public class TestUndirectedGraph {
	
	Graph graph ;
	
	@Before
	public void setup() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("inputGraphTiny"));
		int numVerts = sc.nextInt();
		
		graph = new Graph(numVerts);
		int numEdges = sc.nextInt();
		
		for(int i = 0; i < numEdges; ++i) {
			int fromVert = sc.nextInt(); 
			int toVert = sc.nextInt();
			graph.addEdge(fromVert, toVert);
		}
		
		// Perform Depth First Search.
//		int sourceVert = 0;
//		graph.dfs(sourceVert);
		
		// Count number of connected Components.
		graph.componentCount();
	}
	
	@Test
	public void shouldHaveThirteenVertices() {
		assertEquals(13, graph.getNumVertices());
	}
	
	@Test
	public void shouldHaveThirteenEdges() {
		assertEquals(13, graph.getNumEdges());
	}
	
	@Test
	public void firstVertShouldHaveFourAdjacentVerts() {
		Iterable<Integer> adjacentVerts = graph.getAdjVertices(0);
		StringBuilder sb = new StringBuilder();
		for(Integer eachVert : adjacentVerts) {
			sb.append(eachVert);
		}
		assertEquals("5126", sb.toString());
	}
	
	@Test
	public void vertZeroShouldBeConnectedToVertOne() {
		assertTrue(graph.isConnected(0,1));
	}
	
	@Test
	public void vertZeroNotConnectedToVertSeven() {
		assertFalse(graph.isConnected(0,7));
	}
	
	@Test
	public void shouldHaveThreeConnectedComponents() {
		assertEquals(3, graph.getNumConnectedComponents());
	}
	
	@Test
	public void shouldReturnCorrectPath() {
		Iterable<Integer> path = graph.getPath(4);
		assertEquals("[4, 5, 0]", "" + path);
		System.out.println(path);
	}
	
}
