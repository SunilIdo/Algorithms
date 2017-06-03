package lab12_undirected;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class determines whether a given Graph contains an odd cycle. It does
 * this by keeping track of the levels to which vertices belong in the BFS
 * rooted tree -- if any vertex is examined that has already been visited and is
 * at same level as the current vertex, there must be an odd cycle; otherwise
 * not.
 *
 */
public class OddCycle extends BreadthFirstSearch {
	HashMap<Vertex, Integer> vertexLevel = new HashMap<>();

	public OddCycle(Graph graph) {
		super(graph);
	}

	@Override
	protected void handleInitialVertex() {
		super.handleInitialVertex();
	}

	@Override
	protected void processVertex(Vertex v) {
		if (vertexLevel.size() == 0) {
			vertexLevel.put(v, 0);
		}
	}

	@Override
	protected void processEdge(Edge edge) {
		vertexLevel.put(edge.v, vertexLevel.get(edge.u) + 1);
	}

	public boolean hasOddCycle() {
		start();
		Iterator<Vertex> it = vertexLevel.keySet().iterator();
		while (it.hasNext()) {
			Vertex u = it.next();
			int level = vertexLevel.get(u);
			for (Vertex v : this.graph.getListOfAdjacentVerts(u)) {
				if (vertexLevel.get(v) == level) {
					return true;
				}
			}
		}
		return false;
	}
}
