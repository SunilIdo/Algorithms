package lab12_directed;


public class DirectedEdge {
	Vertex u;
	Vertex v;
	public DirectedEdge(Vertex u, Vertex v) {
		this.u=u;
		this.v = v;
	}
	
	public boolean equals(Object ob) {
		if(ob ==null) return false;
		if(ob.getClass() != getClass()) return false;
		DirectedEdge other = (DirectedEdge)ob;
		return (u==other.u && v == other.v);
	}
	
	public int hashCode() {
		int result = 17;
		result += 31 * result + u.hashCode();
		result += 31 * result + v.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return "(" + u.toString() + ", " + v.toString() + ")";
	}
}
