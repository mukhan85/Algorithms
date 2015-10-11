package chapter1;

public class WeightedQuickFind extends UnionFind {
	private int [] size;

	public WeightedQuickFind(int n) {
		super(n);
		
		size = new int[n];
		for(int i= 0; i < n; ++i) {
			size[i] = 1;
		}
	}

	@Override
	public void union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);
		
		if(size[pRoot] > size[qRoot]) { 
			id[qRoot] = pRoot;
			size[pRoot] += size[qRoot];
		} else {
			id[pRoot] = qRoot;
			size[qRoot] += size[pRoot];
		}
	}

	public int findRoot(int p) {
		while(p != id[p]) {
			p = id[p];
		}
		
		return p;
	}
	
	@Override
	public boolean isConnected(int p, int q) {
		boolean result = findRoot(p) == findRoot(q); 
		System.out.println(p + " -> " + q + " : " + result);
		return result;
	}
}
