package lab12_undirected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//implement
public class ConnectedComponentSearch extends DepthFirstSearch {
	private HashMap<Vertex, Integer> cnm = new HashMap<Vertex, Integer>(); // Component
																			// number
																			// map
																			// for
																			// vertices
	HashMap<Integer, List<Vertex>> componentsMap = new HashMap<Integer, List<Vertex>>();
	HashMap<Integer, List<Edge>> componentEdgeMap = new HashMap<Integer, List<Edge>>();

	public ConnectedComponentSearch(Graph graph) {
		super(graph);
	}

	public List<Graph> getConnectedComponents() {
		List<Graph> connectedComponents = new ArrayList<Graph>();		
		start();
		for(int i=0;i<componentLoopCount;i++){
			connectedComponents.add(new Graph(componentEdgeMap.get(i).toArray(new Edge[0])));
		}
		return connectedComponents;
	}

	@Override
	public void processVertex(Vertex v) {
		cnm.put(v, componentLoopCount);
		if (componentsMap.containsKey(componentLoopCount)) {
			List<Vertex> listV = componentsMap.get(componentLoopCount);
			listV.add(v);
			componentsMap.put(componentLoopCount, listV);
		} else {
			List<Vertex> vertexList = new ArrayList<Vertex>();
			vertexList.add(v);
			componentsMap.put(componentLoopCount, vertexList);
		}
	}

	@Override
	protected void processEdge(Edge e) {
		if (componentEdgeMap.containsKey(componentLoopCount)) {
			List<Edge> listE = componentEdgeMap.get(componentLoopCount);
			listE.add(e);
			componentEdgeMap.put(componentLoopCount, listE);
		} else {
			List<Edge> edgeList = new ArrayList<>();
			edgeList.add(e);
			componentEdgeMap.put(componentLoopCount, edgeList);
		}

	}
}
