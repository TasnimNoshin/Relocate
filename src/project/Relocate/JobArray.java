package project.Relocate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JobArray {
    ArrayList<Job> jobs;
    
    /**
     * 
     * @param data Type: OutlookData
     * This method takes an array of OutlookData and converts it into an array of Jobs
     * @throws IOException 
     */
    JobArray () throws IOException {
    	this.jobs = new ArrayList<Job>();
    	OutlookData[] od = (new Parser()).getOutlookDataArray();
    	toJob(od);
    }
    
    public void toJob(OutlookData[] data) {
    	int index;
        for(OutlookData od: data) {
        	Job newJob = new Job(od.getTitle());
        	index = newJob.searchJob(jobs);
            if(index == -1) {
            	// Province City Potential Trend
            	Province province = new Province(od.getProvAbbr(), newJob);
            	City city = new City(province, od.getLocation());
                newJob.addOutlook(province, city, od.getPotential(), od.getTrends());
                jobs.add(newJob);
            } else {
                Job j = jobs.get(index);
                Province province = new Province(od.getProvAbbr(), j);
            	City city = new City(province, od.getLocation());
                j.addOutlook(province, city, od.getPotential(), od.getTrends());
                jobs.set(index, j);
            }
        }
    }
    
    public ArrayList<Job> getJobArray() throws IOException {
//    	OutlookData[] od = (new Parser()).getOutlookDataArray();
//    	toJob(od);
    	return jobs;
    }
    
//    public void hasher() {
//    	HashMap<String> map = new HashMap<String>(getJobArray().length);   // TODO
//    	for (Job j: getJobArray()) {
//			map.put(j.getName());
//		}	
//    }
}