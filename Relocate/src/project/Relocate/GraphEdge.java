package project.Relocate;

/**
 * This class will be used as an edge to connect two vertices
 * @author Patrick Laskowski
 *
 */
public class GraphEdge implements Comparable{
	private GraphVertex v;
	private GraphVertex w;
	private long weight;
	
	/**
	 * Creates a new connection between two vertices
	 * @param v The first vertex to connect
	 * @param w The second vertex to connect
	 * @param weight A long value that stores the weight of the node
	 */
	public GraphEdge(GraphVertex v, GraphVertex w, long weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	/**
	 * Returns the vertex connected to the given vertex
	 * @param c The source vertex to get the second connection of
	 * @return The second connected vertex or null if c is not a vertex on this edge
	 */
	public GraphVertex getConnection(GraphVertex c){
		if (c == v){
			return w;
		} else if (c == w){
			return v;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the weight of this edge
	 * @return A long value holding the weight of this edge
	 */
	public long getWeight(){
		return weight;
	}
	
	public int compareTo(Object oThat){
		GraphEdge that = (GraphEdge)oThat; //Cast this to an edge since this should only be called on other edges
		if (that.weight == this.weight){
			return 0;
		} else if (this.weight > that.weight){
			return 1;
		} else {
			return -1;
		}
	}
}
