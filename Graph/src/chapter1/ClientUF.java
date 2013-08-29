package chapter1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientUF {
	public static void main(String ... args) throws IOException {
		
		InputStreamReader isr = new FileReader(new File("resources/chapter1/inputUFtiny"));
		BufferedReader input = new BufferedReader(isr);
		String line = "";
		
		int n = Integer.parseInt(input.readLine().trim());
		
		UnionFind ufSimple = new SimpleUnionFind(n);
		UnionFind ufQuickFind = new QuickUnionFind(n);
		UnionFind weightedUf = new WeightedQuickFind(n);
		
		while((line=input.readLine()) != null) { 
			String[] data = line.split("\\s");
			int p = Integer.parseInt(data[0]);
			int q = Integer.parseInt(data[1]);
			ufSimple.union(p, q);
			ufQuickFind.union(p, q);
			weightedUf.union(p, q);
		}
		
		System.out.println("SimpleUnionFind Test. \nConnected");
		ufSimple.isConnected(4, 3);
		ufSimple.isConnected(3, 4);
		ufSimple.isConnected(6, 5);
		ufSimple.isConnected(3, 8);
		System.out.println("Not connected");
		ufSimple.isConnected(0, 9);
		
		System.out.println("======");
		System.out.println("QuickUnionFind Test. \nConnected");
		ufQuickFind.isConnected(4, 3);
		ufQuickFind.isConnected(3, 4);
		ufQuickFind.isConnected(6, 5);
		ufQuickFind.isConnected(3, 8);
		System.out.println("Not connected");
		ufQuickFind.isConnected(0, 9);

		System.out.println("======");
		System.out.println("WeightedQuickUnionFind Test. \nConnected");
		weightedUf.isConnected(4, 3);
		weightedUf.isConnected(3, 4);
		weightedUf.isConnected(6, 5);
		weightedUf.isConnected(3, 8);
		System.out.println("Not connected");
		weightedUf.isConnected(0, 9);

		input.close();
	}
}
