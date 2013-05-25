package test.undirectedgraph;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.coursera.graph.undirected.Graph;
import org.coursera.graph.undirected.processors.BreadthFirstSearch;
import org.junit.Before;
import org.junit.Test;

public class TestBFS {
	
	private Graph graph;
	
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

		graph.bfs(0);
	}
	
	
	@Test
	public void shouldSetupGraph() {
		Iterable<Integer> path = graph.getPath(4);
		assertEquals("[4, 5, 0]", "" + path);
		System.out.println(path);		
	}

	@Test
	public void shouldHaveDistOne() {
		assertEquals(1, graph.getDistanceTo(5));
		assertEquals(1, graph.getDistanceTo(1));
		assertEquals(1, graph.getDistanceTo(2));
		assertEquals(1, graph.getDistanceTo(6));
	}
	
	@Test
	public void shouldHaveDistTwo() {
		assertEquals(2, graph.getDistanceTo(3));
		assertEquals(2, graph.getDistanceTo(4));
	}	
}
