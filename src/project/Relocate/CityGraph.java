package project.Relocate;

import java.io.IOException;
//We will use ArrayLists to store the vertices in the graph. This class comes with the standard Java library.
import java.util.ArrayList;

/**
 * This class will be used to create the actual undirected graph of the city
 * @author Patrick Laskowski
 *
 */
public class CityGraph {
	
	private final double SCALING_FACTOR = 1; //The intervals at which to create new clusters, these must be able to evenly divide 3 (i.e. 0.1/0.25/0.5 are good, 0.262362 is bad). To avoid issues, use numbers that can be formed with 1/(2^n).
	private final int CITIES_TO_RETURN = 2; //How many cities should be returned when searching for cities of similair income
	private ArrayList<GraphVertex> vertices;
	/**
	 * Uses a City ArrayList to construct the graph
	 * @param input The City ArrayList to use as input
	 */
	public CityGraph(ArrayList<City> input){
		//Construct the "dummy nodes" to hold the incremental steps
		vertices = new ArrayList<GraphVertex>();
		vertices.add(new GraphVertex(null,0));
		for(double i = SCALING_FACTOR; i <= 3; i += SCALING_FACTOR){
			vertices.add(new GraphVertex(null,i)); //Null city names are dummy nodes for actual cities to branch off of
			vertices.get(vertices.size()-1).connect(vertices.get(vertices.size() - 2), -1);
		}
		//Add all cities to the graph in the proper place
		for (City city : input){
			//Create a new vertex to represent this city
			GraphVertex newCity = new GraphVertex(city, city.getOutlook().getPotential());
			//Add it to the graph
			vertices.add(newCity);
			//Link it up to the correct dummy vertex with the income as the weight
			crawlDummies(vertices.get(0),newCity.getOutlook()).connect(newCity, city.getIncome());;
		}
	}

	/**
	 * Crawls up the graph to find the node to link an outlook to 
	 * @param source The GraphVertex to inspect
	 * @param targetValue The outlook that the 
	 * @return The appropriate GraphVertex to insert at
	 */
	private GraphVertex crawlDummies(GraphVertex source, double targetValue){
		if (targetValue - source.getOutlook() >= 0 && targetValue - source.getOutlook() < SCALING_FACTOR){
			return source;
		} else{
			for (GraphEdge e : source.getAdj()){
				if (e.getWeight() == -1){ //Negative weights are used to indicate dummy links
					if (e.getConnection(source).getOutlook() > source.getOutlook()){ //This is the next highest node to crawl to
						return crawlDummies(e.getConnection(source),targetValue);
					}
				}
			}
		}
		return null; 
	}
	
	/**
	 * Returns a few cities related to the given city
	 * @param toFind The city to find related cities for
	 * @return An ArrayList of cities related to this city
	 */
	public ArrayList<City> getRelatedCities(City toFind){
		GraphVertex desiredVertex = null;
		for(GraphVertex w : vertices){
			//Scan for the city in the graph
			if (w.getCity() != null && w.getCity().getCityName().equals(toFind.getCityName()) && w.getCity().getProvince().equals(toFind.getProvince())){
				desiredVertex = w;
			}
		}
		if (desiredVertex == null){
			return null; //No city found, return null
		}
		//Every city only has one adjacency pointing back to it's parent dummy node
		GraphVertex dummyNode = desiredVertex.getAdj().iterator().next().getConnection(desiredVertex);
		//Put all the edges into the ArrayList
		ArrayList<Comparable> edges = new ArrayList<Comparable>();
		for (GraphEdge e : dummyNode.getAdj()){
			//Don't want the original city to appear or another dummy node
			if (e.getConnection(dummyNode) != desiredVertex && e.getWeight() != -1){
				edges.add(e);
			}
		}
		//Sort the arraylist
		MergeSort.sortMerge(edges, edges.size());
		//Put the desired number of cities into a City arraylist and return it
		ArrayList<City> retArray = new ArrayList<City>();
		for (int i = 0; i < Math.min(CITIES_TO_RETURN,edges.size()); i++){
			GraphEdge e = (GraphEdge) edges.get(i);
			retArray.add(e.getConnection(dummyNode).getCity());
		}
		System.out.println("Done");
		return retArray;
	}

}
