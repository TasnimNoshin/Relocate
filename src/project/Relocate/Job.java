package project.Relocate;

import java.util.ArrayList;

/**
 * 
 * @author Umme Salma Gadriwala 
 * JobADT uses LocationADT. The idea behind this is
 * that each job has multiple provinces. Therefore, each job has an
 * array of provinces where the job is available.
 *
 */

public class Job {
	private final String name;
	private ArrayList<Province> provinces;

	Job(String name) {
		this.name = name;
		this.provinces = new ArrayList<Province>();
	}

	public String getName() {
		return this.name;
	}

	public Province[] getProvinces() {
		return (Province[]) this.provinces.toArray();
	}

	public void addOutlook(Province province, City city, int potential, String trend) {
		// search for index of location with same province ID
		int index = province.searchProvince(this.provinces);

		// if province does not already exist
		// create new province
		// and add it to the province array
		if (index == -1) {
			province.addOutlook(city, potential, trend);
			provinces.add(province);
			return;
		}

		// if province already exists
		// add outlook in for that province
		Province prov = this.provinces.get(index);
		prov.addOutlook(city, potential, trend);
		this.provinces.set(index, prov);
		return;
	}

	public int getPotential(Province province, City city) {
		int index = province.searchProvince(this.provinces);
		if (index == -1)
			return 0; // if job does not exist in this location
		return this.provinces.get(index).getPotential(city);
	}

	public double getAvgPotential(Province province) {
		int index = province.searchProvince(this.provinces);
		if (index == -1)
			return 0; // if job does not exist in this location
		return this.provinces.get(index).getAvgPotential();
	}
	
	/**
	 * 
	 * @return An array of all cities in which the job exists irrespective of their provinces
	 */
	public ArrayList<City> getCities() {		// TODO: Is there a better way to do this?
		ArrayList<City> allCities = new ArrayList<City>();
		for (Province province : this.provinces) 
			for (City city : province.getCities())
				if (city.getCityName() != null)
					allCities.add(city);
		return allCities;
	}
	
	public ArrayList<City> getCities(Province province) {
		int index = province.searchProvince(provinces);
		if (index == -1) return new ArrayList<City>();
		return this.provinces.get(index).getCities();
	}

	/**
	 * 
	 * @param jobs
	 * Type: Job array
	 * @return Index of this job in jobs, -1 if job does not exist
	 */
	public int searchJob(ArrayList<Job> jobs) {
		int i = 0;
		for (Job job : jobs) {
			if (this.name.equalsIgnoreCase(job.getName()) || job.getName().toLowerCase().contains(name.toLowerCase()))
				return i;
			i++;
		}
		return -1;
	}

	/**
	 * Sorts the provinces array according to avgPotential
	 */
	public void sortProvinces() {
		MergeSort.sortMerge((ArrayList)provinces, provinces.size());
	}

	/**
	 * 
	 * @return An array of all cities in which the job is available sorted by potential
	 */
	public ArrayList<City> sortCity() {
		ArrayList<City> sortCities = getCities();
		MergeSort.sortMerge((ArrayList)sortCities, sortCities.size());
		return sortCities;
	}

	@Override
	public boolean equals(Object that) {
		if (that.getClass() == this.getClass())
			return this.name.equals(((Job) that).getName());
		return false;
	}

	@Override
	public String toString() {
		String provincesArrayString = "";
		int i = provinces.size();
		for (Province p : provinces) {
			if (i == 1)
				provincesArrayString += p.toString();
			else {
				provincesArrayString += p.toString() + ", \n";
				i--;
			}
		}
		return "(Name: " + name + "; " + provincesArrayString + ")";
	}
}
