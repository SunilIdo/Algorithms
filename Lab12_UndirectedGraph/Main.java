package lab12_undirected;


import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Pair> l = new ArrayList<Pair>();
		l.add(new Pair("A","B"));
		l.add(new Pair("B","C"));
		l.add(new Pair("A","D"));
		l.add(new Pair("B","D"));
		l.add(new Pair("X", "Y"));
		l.add(new Pair("X", "Z"));
		Graph g = new Graph(l);
		String s = g.toString();
		System.out.println(s);
		System.out.println("Are B and C adjacent? "+g.areAdjacent(new Vertex("B"),new Vertex("C")));
		System.out.println("Are A and C adjacent? "+g.areAdjacent(new Vertex("A"),new Vertex("C")));
		System.out.println("Spanning Tree:");
		Graph t = g.getSpanningTree();
		System.out.println(t);
		System.out.println("Spanning Tree of the spanning tree:");
		System.out.println(t.getSpanningTree());
		System.out.println("Connected Components:");
		List<Graph> gList=g.getConnectedComponents();
		int comp=0;
		for(Graph graph:gList){			
			System.out.println("Component"+comp+": ");
			System.out.println(graph);
			comp++;
		}
		
		
	}

}
