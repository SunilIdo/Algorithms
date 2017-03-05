package lab4;

public class AlgorithmG {
	
	public int G(int m, int n){
		if(n==0) return m;
		else
			return G(n,m%n);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlgorithmG g=new AlgorithmG();
		System.out.println(g.G(30, 20));
	}

}
