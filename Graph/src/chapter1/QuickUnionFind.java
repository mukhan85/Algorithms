package chapter1;


public class QuickUnionFind extends UnionFind {

	public QuickUnionFind(int n) {
		super(n);
	}

	@Override
	public void union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		id[pRoot] = qRoot;
	}

	@Override
	public boolean isConnected(int p, int q) {
		boolean result = findRoot(p) == findRoot(q); 
		System.out.println(p + " -> " + q + " : " + result);
		return result;
	}
	
	@Override
	protected int findRoot(int p) {
		int rootId = id[p];
		while(rootId != id[rootId]) {
			rootId = id[rootId];
		}
		return rootId;
	}
}
