package project.Relocate;

import java.io.IOException;
import java.util.ArrayList;

public class Searcher {
	JobArray jobsFetcher;
	ArrayList<Job> jobs;
	/**
	 * This m TODO Patrick!
	 * @param jobName Datatype: String
	 * @throws IOException 
	 */
	Searcher () throws IOException {
		jobsFetcher = new JobArray();

		jobs = jobsFetcher.getJobArray();

		
	}
	
	public String searchCity(String jobName) throws IOException {
		// Create a new array to hold all available jobs
		JobArray jobsFetcher = new JobArray();
		// Populate the array using the parser to fetch data sets
		jobsFetcher.toJob((new Parser().getOutlookDataArray()));
		ArrayList<Job> jobs = jobsFetcher.getJobArray();
		Job searchJob = new Job(jobName);
		int index = searchJob.searchJob(jobs);
		if (index == -1)
			return "Job not found.";
		String result = "";
		ArrayList<City> cities = jobs.get(index).sortCity();
		for (City city : cities) {
			result += city.toString() + "\n";
		}
		return result;
		
	}
	
	public String searchProvinceCity(String jobName, String provinceCode) throws IOException {
		Job searchJob = new Job(jobName);
//		Province searchProvince = new Province(provinceCode, searchJob);
		int index = searchJob.searchJob(jobs);
		if (index == -1) return "Job not found.";
		String result = "";
		ArrayList<City> cities = jobs.get(index).sortCity();
		int n =0;
		for (City city : cities) {
			if(city.getProvince().getProvinceCode().equalsIgnoreCase(provinceCode)) {
				result += city.toString() + "\n";
				n++;
			}
			if(n==5) break;
		}
		return result;
	}
	
	public String searchIncomeCity(String jobName, String provinceCode,String aveIncome){
		double Income = Double.parseDouble(aveIncome);
		Job searchJob = new Job(jobName);
//		Province searchProvince = new Province(provinceCode, searchJob);
		int index = searchJob.searchJob(jobs);
		if (index == -1) return "Job not found.";
		String result = "";
		ArrayList<City> cities = jobs.get(index).sortCity();
		ArrayList<City> filter = new ArrayList<City>();
		for(City c : cities){
			if(c.getIncome()>=Income){
				filter.add(c);
			}
		}
		ArrayList<City> provFilter = new ArrayList<City>();
		int n = 0;
		for (City city : filter) {
			if (city.getProvince().getProvinceCode().equalsIgnoreCase(provinceCode)) {
				result += city.toString() + "\n";
				provFilter.add(city);
				n++;
			}
			if(n==5) break;
		}
		result+="\n";
		CityGraph G = new CityGraph(provFilter);
		for(int i = 0; i < provFilter.size();i++){
			result+=G.getRelatedCities(provFilter.get(i))+"\n";
		}
		
		return result;
	}
	
	
////////////////////////////////////TEST FUNCTIONS ////////////////////////////////////////////
/*	these are slightly modified versions of the above methods to make testing simpler
	they perform the same tasks, just return different things that are more relevant to the testing process
	than strings*/
	public int searchCityTest(String jobName) throws IOException {
		JobArray jobsFetcher = new JobArray();
		jobsFetcher.toJob((new Parser().getOutlookDataArray()));
		ArrayList<Job> jobs = jobsFetcher.getJobArray();
		Job searchJob = new Job(jobName);
		int index = searchJob.searchJob(jobs);
	
		return index;
	}
	
	public ArrayList<City> searchProvinceCityTest(String jobName, String provinceCode) throws IOException {
		Job searchJob = new Job(jobName);
		int index = searchJob.searchJob(jobs);
		ArrayList<City> c = new ArrayList<City>();
		if (index == -1) return c;
		ArrayList<City> cities = jobs.get(index).sortCity();
		ArrayList<City> provFilter = new ArrayList<City>();
		int n = 0;
		for (City city : cities) {
			if (city.getProvince().getProvinceCode().equalsIgnoreCase(provinceCode)) {
				provFilter.add(city);
				n++;
			}
			if(n==5) break;
		}
		return provFilter;
	}
	
	public ArrayList<City> searchIncomeCityTest(String jobName, String provinceCode,String aveIncome){
		double Income = Double.parseDouble(aveIncome);
		Job searchJob = new Job(jobName);
		int index = searchJob.searchJob(jobs);
		ArrayList<City> p = new ArrayList<City>();
		if (index == -1) return p;
		ArrayList<City> cities = jobs.get(index).sortCity();
		ArrayList<City> filter = new ArrayList<City>();
		for(City c : cities){
			if(c.getIncome()>=Income) {
				filter.add(c);
			}
		}
		ArrayList<City> provFilter = new ArrayList<City>();
		int n = 0;
		for (City city : filter) {
			if (city.getProvince().getProvinceCode().equalsIgnoreCase(provinceCode)) {
				provFilter.add(city);
				n++;
			}
			if(n==5) break;
		}
		return provFilter;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws IOException {
		//String s = new Searcher().searchProvinceCity("architects","on");
		int s = new Searcher().searchCityTest("Civil Engineers");
		System.out.println(s);
	}
}