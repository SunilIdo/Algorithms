package lab12_directed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This extension of DFS successfully outputs a topological ordering as long as
 * input graph is a DAG. If every vertex is reachable from the starting vertex,
 * the output will be a topological ordering of the entire graph; if not, it
 * will be a topological ordering of all vertices in the graph that are
 * reachable from the starting vertex. (As mentioned in the slides, this
 * approach can be improved so that all vertices are output in topologically
 * sorted order.)
 * 
 * IMPLEMENT
 */
public class TopologicalSort extends DepthFirstSearch {
	private Vertex[] sortedVertices;
	private Vertex topSortStartVertex = null;
	private Stack<Vertex> vStack = null;
	int k = 0;
	HashMap<Vertex,Integer> topSortMap=new HashMap<Vertex,Integer>();
	public Vertex getTopSortStartVertex() {
		return topSortStartVertex;
	}

	/**
	 * Assumption: g is a DAG. If not, there is no guarantee concerning the
	 * nature of the output.
	 */
	public TopologicalSort(Digraph g) {
		super(g);
		// implement
		k = graph.vertices.size();
		sortedVertices = new Vertex[k];
		loadNoInVertices();		
		while(!vStack.isEmpty()){		
			computeTopStartVertex();
			start(topSortStartVertex);
		}
	}
	public List<Vertex> getTopologicalOrdering() {
		// warning: will return null until this class has been implemented!
		return Arrays.asList(sortedVertices);
	}
	private void loadNoInVertices(){
		vStack = new Stack<Vertex>();
		for (Vertex v : graph.vertices) {
			if (!graph.inList.containsKey(v)) {
				vStack.push(v);
			}
		}
	}
	// finds a vertex that has no in-vertices and sets this value
	// in topSortStartVertex
	public void computeTopStartVertex() {
		// implement
		topSortStartVertex = vStack.pop();
	}

	@Override
	protected void processVertex(Vertex w) {
		// TODO Auto-generated method stub			
		sortedVertices[k-1] = w;
		k--;
	}

}
