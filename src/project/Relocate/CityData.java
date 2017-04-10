package project.Relocate;

public class CityData {
	private final int year;
	private final String city;
	private final String province;
	private final String GeographicalID;
	private final String DataType;
	private final String Vector;
	private final double Coordinate;
	private final double Income;
	
	/**
	 * Creates a new CityData object to represent a line of data pulled from the dataset
	 * @param y The year of the data
	 * @param r The region it is in
	 * @param Geo An identifier for the geographical region
	 * @param d The type of the data (i.e. median income/total income/etc.)
	 * @param v The vector representation of the data
	 * @param c The coordinate value of the data
	 * @param val The actual value of the data
	 */
	public CityData(int y,String r, String Geo, String d, String v, double c, double val){
		year = y;
		//If the region does not contain a " - " then it's a provincial data row so City will be null
		if (!(r.contains(" - "))){
			province = r;
			city = null;
		} else {
			//The region does contain a " - " so we split here and turn it into a city and province
			//Also the data here will be in quotes so they need to be substringed off
			String[] locSplit = r.split(" - ");
			city = locSplit[0].substring(1);
			province = locSplit[1].substring(0, locSplit[1].length() - 1);
		}
		GeographicalID = Geo;
		DataType = d;
		Vector = v;
		Coordinate = c;
		Income = val;
	}
	/**
	 * Returns the coordinate value of the data
	 * @return A double representation of the coordinate
	 */
	public double getCoordinate() {
		return Coordinate;
	}
	/**
	 * Returns the type of the data
	 * @return The string representation of the data type
	 */
	public String getDataType() {
		return DataType;
	}
	/**
	 * Returns the geographical ID
	 * @return A string representation of the geographical ID
	 */
	public String getGeographicalID() {
		return GeographicalID;
	}
	
	/**
	 * Returns the province the data was collected in
	 * @return A string representation of the province
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * Returns the city the data was collected in
	 * @return A string representing the city, or null if this is provincial data
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Returns the value of the data
	 * @return A double containing the data's numerical value
	 */
	public double getIncome() {
		return Income;
	}
	
	/**
	 * Returns the vector representation
	 * @return A string with the vector representation
	 */
	public String getVector() {
		return Vector;
	}
	
	/**
	 * Returns the year
	 * @return An integer holding the year the data was collected
	 */
	public int getYear() {
		return year;
	}
}
