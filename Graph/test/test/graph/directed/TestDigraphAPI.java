package test.graph.directed;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.coursera.graph.directed.Digraph;
import org.junit.Before;
import org.junit.Test;

public class TestDigraphAPI {
	Digraph dg ;
	
	@Before 
	public void constructDigraph() {
		dg = new Digraph(new File("inputDigraphTiny"));
		dg.dfs(0);
	}
	
	@Test
	public void shouldConstructDigraph() {
		List<Integer> adjList = (List<Integer>) dg.getAdjList(0);
		assertEquals(adjList.size(), 2);
	}

	@Test
	public void shouldPrintDigraph() {
		String digraphPrint = dg.toString();
		assertNotNull(digraphPrint);
	}
	
	@Test
	public void vertZeroConnectedToVertOne() {
		assertTrue(dg.isVisited(1));
		assertFalse(dg.isVisited(6));
	}
	
	@Test
	public void testShortestDistance() {
		dg.bfs(0);
		assertEquals(2, dg.getDistance(4));
		assertEquals(3, dg.getDistance(2));
		
		
	}
}
