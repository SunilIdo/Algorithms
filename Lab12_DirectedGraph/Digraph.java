package lab12_directed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a directed graph
 */
public class Digraph {
	LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	LinkedList<DirectedEdge> directedEdges = new LinkedList<DirectedEdge>();
	/* For any vertex v, lists the vertices w for which (v,w) is an edge */
	HashMap<Vertex, LinkedList<Vertex>> outList = new HashMap<Vertex, LinkedList<Vertex>>();
	/* For any vertex v, lists the vertices w for which (w,v) is an edge */
	HashMap<Vertex, LinkedList<Vertex>> inList = new HashMap<Vertex, LinkedList<Vertex>>();

	public LinkedList<Vertex> getOutVertices(Vertex v) {
		return outList.get(v);
	}

	public LinkedList<Vertex> getInVertices(Vertex v) {
		return inList.get(v);
	}

	/**
	 * Returns true iff Vertex v is reachable from Vertex u
	 */
	public boolean reaches(Vertex u, Vertex v) {
		// implement
		DepthFirstSearch dfs = new DepthFirstSearch(this);
		dfs.start(u);
		if (dfs.visitedVertices.containsKey(v))
			return true;
		return false;
	}

	/**
	 * Returns a list of vertices reachable from a given vertex
	 */
	public List<Vertex> reachable(Vertex u) {
		// implement
		DepthFirstSearch dfs = new DepthFirstSearch(this);
		dfs.start(u);
		return new ArrayList<Vertex>(dfs.visitedVertices.values());
	}

	/**
	 * Returns true if G is strongly connected
	 */
	public boolean isStronglyConnected() {
		// implement (optional)
		int n = vertices.size(); // # of vertices.
		Vertex _startVertex = vertices.getFirst();
		int visitedVerticesCount1 = 0, visitedVerticesCount2 = 0;
		DepthFirstSearch dfs = new DepthFirstSearch(this);
		dfs.start(_startVertex);
		visitedVerticesCount1 = dfs.visitedVertices.size();
		reverseEdges();		
		dfs.reset();
		dfs.start(_startVertex);
		visitedVerticesCount2 = dfs.visitedVertices.size();
		if (visitedVerticesCount1 == n && visitedVerticesCount2 == n)
			return true;
		return false;
	}

	public void reverseEdges() {
		HashMap<Vertex, LinkedList<Vertex>> tempList = new HashMap<Vertex, LinkedList<Vertex>>();
		tempList = inList;
		inList = outList;
		outList = tempList;
	}
	public void resetEdges() {
		HashMap<Vertex, LinkedList<Vertex>> tempList = new HashMap<Vertex, LinkedList<Vertex>>();
		tempList = inList;
		inList = outList;
		outList = tempList;
	}
	/**
	 * Returns a List of vertices in topological order. Precondition: this G is
	 * a DAG (i.e. no directed cycles) and all vertices are reachable from the
	 * start vertex. If not all are reachable, then returns list of all vertices
	 * reachable from start vertex, in topological order.
	 */
	public List<Vertex> topologicalSort() {
		resetEdges();
		TopologicalSort ts = new TopologicalSort(this);
		
//		ts.start(ts.getTopSortStartVertex());
		return ts.getTopologicalOrdering();
	}

	/**
	 * Constructs a digraph object from an array of directed edges.
	 */
	public Digraph(DirectedEdge[] inputEdges) {
		HashMap<Vertex, Vertex> dupverts = new HashMap<Vertex, Vertex>();

		for (DirectedEdge e : inputEdges) {
			// Assume dup edges are not allowed
			directedEdges.add(e);
			Vertex source = e.u;
			Vertex head = e.v;
			if (!dupverts.containsKey(source)) {
				dupverts.put(source, source);
				vertices.add(source);
			}

			if (!dupverts.containsKey(head)) {
				dupverts.put(head, head);
				vertices.add(head);

			}

			LinkedList<Vertex> l = outList.get(source);
			if (l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(head);
			outList.put(source, l);

			LinkedList<Vertex> l2 = inList.get(head);
			if (l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(source);
			outList.put(head, l2);
		}
	}

	/**
	 * Constructs a digraph from a list of pairs. A pair (A,B) is transformed
	 * into vertices A and B together with a directed edge A->B.
	 */
	public Digraph(List<Pair> pairs) {
		HashMap<Vertex, Vertex> dupverts = new HashMap<Vertex, Vertex>();
		HashMap<DirectedEdge, DirectedEdge> dupedges = new HashMap<DirectedEdge, DirectedEdge>();
		for (Pair e : pairs) {
			// handle the vertices and edges simultaneously
			Vertex source = new Vertex(e.ob1);
			Vertex head = new Vertex(e.ob2);
			DirectedEdge edge = new DirectedEdge(source, head);
			if (!dupverts.containsKey(source)) {
				dupverts.put(source, source);
				vertices.add(source);

			}
			if (!dupverts.containsKey(head)) {
				dupverts.put(head, head);
				vertices.add(head);

			}

			if (!dupedges.containsKey(edge)) {
				dupedges.put(edge, edge);
				directedEdges.add(edge);
			}

			LinkedList<Vertex> l = outList.get(source);
			if (l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(head);
			outList.put(source, l);

			LinkedList<Vertex> l2 = inList.get(head);
			if (l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(source);
			inList.put(head, l2);
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Vertices: \n" + " ");
		for (Vertex v : vertices) {
			sb.append(v + " ");
		}
		sb.append("\nDirected Edges:\n" + " ");

		for (Vertex v : vertices) {
			LinkedList<Vertex> l = outList.get(v);
			if (l != null && !l.isEmpty()) {
				for (Vertex w : l) {
					String edge = v.toString() + "->" + w.toString();
					sb.append(edge + " ");
				}
			}
		}
		return sb.toString();
	}

}
