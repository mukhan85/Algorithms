package chapter1;

public abstract class UnionFind {
	protected int [] id;
	public UnionFind(int n) {
		id = new int[n];
		for(int i = 0; i < n; ++i) {
			id[i] = i;
		}
	}
	
	protected int findRoot(int p) {
		return 0;
	}
	
	abstract public void union(int p, int q);
	abstract public boolean isConnected (int p, int q);
	
	protected int count() {
		return id.length;
	}
}


