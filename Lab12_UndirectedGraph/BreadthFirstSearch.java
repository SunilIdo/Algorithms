package lab12_undirected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//implement
public class BreadthFirstSearch extends AbstractGraphSearch {
	protected HashMap<Vertex, Vertex> visitedVertices = new HashMap<Vertex, Vertex>();
	Queue<Vertex> queue = new LinkedList<>();
	Iterator<Vertex> iterator = null;
	protected int VerticesSize;
	HashMap<Vertex, LinkedList<Vertex>> adjacencyList;
	Graph graph;

	public BreadthFirstSearch(Graph graph) {
		VerticesSize = graph.vertices.size();
		adjacencyList = graph.getAdjacencyList();
		iterator = graph.vertices.iterator();
		this.graph = graph;
	}

	@Override
	protected boolean someVertexUnvisited() {
		return visitedVertices.size() < VerticesSize;
	}

	@Override
	protected void handleInitialVertex() {
		Vertex v = nextUnvisited();
		if (v != null) {
			setHasBeenVisited(v);
			processVertex(v);
			queue.add(v);
		}
	}

	protected void processVertex(Vertex v) {
	}

	public Vertex nextUnvisited() {
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (!visitedVertices.containsKey(next)) {
				return next;
			}
		}
		return null;
	}

	@Override
	protected void singleComponentLoop() {
		while (!queue.isEmpty()) {
			Vertex u = queue.remove();
			for (Vertex v : unvisitedAdjacents(u)) {
				if (v == null)
					queue.remove();
				else {
					setHasBeenVisited(v);
					processEdge(new Edge(u, v));
					processVertex(v);
					queue.add(v);
				}
			}
		}
	}

	protected void processEdge(Edge edge) {
	}

	public void setHasBeenVisited(Vertex v) {
		visitedVertices.put(v, v);
	}

	public List<Vertex> unvisitedAdjacents(Vertex v) {
		List<Vertex> listOfAdjacent = adjacencyList.get(v);
		Iterator<Vertex> it = listOfAdjacent.iterator();
		List<Vertex> retVert = new ArrayList<Vertex>();
		while (it.hasNext()) {
			Vertex u = it.next();
			if (visitedVertices.containsKey(u)) {
				it.remove();
			}
			if (!visitedVertices.containsKey(u)) {
				retVert.add(u);
				it.remove();
			}
		}
		return retVert;
	}

	@Override
	protected void additionalProcessing() {
		// TODO Auto-generated method stub
	}
}
