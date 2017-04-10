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
		Province searchProvince = new Province(provinceCode, searchJob);
		int index = searchJob.searchJob(jobs);
		if (index == -1) return "Job not found.";
		String result = "";
		ArrayList<City> cities = jobs.get(index).sortCity();
		
		for (City city : cities) {
			if(city.getProvince().getProvinceCode().equalsIgnoreCase(provinceCode)) result += city.toString() + "\n";
		}
		return result;
	}
}