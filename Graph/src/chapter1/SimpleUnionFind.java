package chapter1;

public class SimpleUnionFind extends UnionFind {

	public SimpleUnionFind(int n) {
		super(n);
	}
	
	@Override
	public void union(int p, int q) {
		int pId = id[p];
		int qId = id[q];
		
		for(int i= 0; i < id.length; ++i) {
			if(id[i] == pId) {
				id[i] = qId;
			}
		}
	}
	
	@Override
	public boolean isConnected (int p, int q) {
		System.out.println(p + " -> " + q + " : " + (id[p] == id[q]));
		return id[p] == id[q];
	}

}
