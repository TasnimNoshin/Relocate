package project.Relocate;

import java.util.ArrayList;

/**
 * 
 * @author Umme Salma Gadriwala
 * LocationADT uses OutlookADT, and is used by JobADT.
 * The idea is that for each location for a particular job, there are multiple outlooks.
 * Each location for a particular job has an average potential for that job.
 * Locations for a particular job are sorted by average potential.
 *
 */
public class Province implements Comparable {
	private final String provinceCode;
	private double avgPotential;
	private double provinceIncome;
	private Job job;
	private ArrayList<City> cities;
	private ArrayList<String> provinceTrends;
	
	Province (String provinceCode, Job job) {
		this.provinceCode = provinceCode;
		this.job = job;
		this.cities = new ArrayList<City>();
		this.provinceTrends = new ArrayList<String>();
		this.avgPotential = 0.0;
	}
	
	public String getProvinceCode() {
		return this.provinceCode;
	}
	
	public Job getJob() {
		return this.job;
	}
	
	public double getAvgPotential() {
		return this.avgPotential;
	}
	
	public ArrayList<City> getCities() {
		return this.cities;
	}
	
	public ArrayList<String> getProvinceTrends() {
		return this.provinceTrends;
	}
	
	public double getProvinceIncome() {
		return provinceIncome;
	}
	
	public void setProvinceIncome(double provinceIncome) {
		this.provinceIncome = provinceIncome;
	}
	/**
	 * 
	 * @param city Type: City
	 * @param potential Type: int
	 * @param trend Type: String
	 * @return Adds the outlook into the respective city and updates the avgPotential
	 */
	public void addOutlook (City city, int potential, String trend) {
		if (city.getCityName() == null)
			this.provinceTrends.add(trend);
		else {
			// search for index of location with same province ID
			int index = city.searchCity(this.cities);
			// if city does not exist
			// create new city and add it in cities array
			if (index == -1) {
				city.setOutlook(potential, trend);
				cities.add(city);
			} else {
				City temp = this.cities.get(index);
				temp.setOutlook(potential, trend);
				this.cities.set(index, temp);
			}
		}
		// Simple mean manipulation
		this.avgPotential = ((this.avgPotential*(cities.size() - 1)) + potential)/cities.size();
	}
	
	public int getPotential (City city) {
		int index = city.searchCity(this.cities);
		if (index == -1)
			return 0; // if job does not exist in this location
		return this.cities.get(index).getOutlook().getPotential();
	}
	
	/**
	 * 
	 * @param provinces An array of provinces
	 * @return Search this province in the array of provinces, and find the index of the province.
	 * If this province does not exist in the array, then return -1.
	 */
	public int searchProvince(ArrayList<Province> provinces) {
		int i = 0;
		for (Province province : provinces) {
			if (this.provinceCode.equalsIgnoreCase(province.getProvinceCode()))
				return i;
			i++;
		}
		return -1;
	}
		
	/**
	 * Sorts the cities global array by potential using merge sort.
	 */
	public void sortCities() {
		MergeSort.sortMerge((ArrayList) cities, cities.size());
	}
	
	public int compareTo(Object thatP) {
		// compares this location to that location
		// 2 symbolizes error in the compare method!
		// Returns 2 if this and that are not provinces for the same Job
		// Returns 0 if the avgPotential of this and that is the same
		// Returns 1 if this has a higher avgPotential
		// Returns -1 if that has a higher avgPotential
		Province that = (Province)thatP;
		if (!this.job.equals(that.getJob())) return 2;
		if (this.avgPotential == that.getAvgPotential()) return 0;
		if (this.avgPotential > that.getAvgPotential()) return 1;
		return -1;
	}
	
	@Override
	public String toString() {
		String cityArrayString = "";
		int i = cities.size();
		for (City c: cities) {
			if (i == 1) cityArrayString += c.toString();
			else {
				cityArrayString += c.toString() + ", \n";
				i--;
			}
		}
		return "[Province: " + provinceCode +
				"; Average Potential: " + avgPotential +
				"; Province Trends Potential: " + provinceTrends.toString() +
				"; " + cityArrayString + "]";
	}
}
