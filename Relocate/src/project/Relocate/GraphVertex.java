package project.Relocate;

//We will use the array list provided as part of the standard Java library to store adjacent edges
import java.util.ArrayList;

/**
 * This class will be used as the vertices of the graph
 * @author Patrick Laskowski
 *
 */


public class GraphVertex {
	private City city;
	private double outlook;
	private ArrayList<GraphEdge> adj;
	
	/**
	 * Creates a new vertex
	 * @param city A City object
	 * @param outlook The outlook of the city
	 */
	public GraphVertex(City city, double outlook){
		this.city = city;
		this.outlook = outlook;
		this.adj = new ArrayList<GraphEdge>();
	}
	
	/**
	 * Connects this vertex to another vertex
	 * @param other The other vertex to connect to
	 * @param weight The weight of the connection as a long value
	 */
	public void connect(GraphVertex other,long weight){ 
		GraphEdge toAdd = new GraphEdge(this,other,weight);
		this.adj.add(toAdd);
		other.adj.add(toAdd); //This is bidirectional so both vertices get the edge
	}
	
	/**
	 * Returns an iterable object to iterate over all edges connected to this vertex
	 * @return An iterable object containing all edges connected to this vertex
	 */
	public Iterable<GraphEdge> getAdj() {
		return adj;
	}
	
	/**
	 * Returns the name of the city
	 * @return A string representing the name of the city
	 */
	public String getCityName() {
		return city.getCityName();
	}
	
	/**
	 * Returns the city object itself
	 * @return The City object originally stored in this vertex
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * Returns the outlook of the city
 	 * @return A double representing the outlook of the city
	 */
	public double getOutlook() {
		return outlook;
	}
	
	
}
