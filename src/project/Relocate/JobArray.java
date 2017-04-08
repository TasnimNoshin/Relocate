package project.Relocate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JobArray {
    private ArrayList<Job> jobs;
    private Income incomeFetcher;
    private ArrayList<CityIncome> income;
    private ProvinceMap map;
    /**
     * 
     * @param data Type: OutlookData
     * This method takes an array of OutlookData and converts it into an array of Jobs
     * @throws IOException 
     */
    JobArray () throws IOException {
    	this.jobs = new ArrayList<Job>();
		incomeFetcher = new Income();
		map = new ProvinceMap();
		income = incomeFetcher.getCities();
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
            	//get the province name by province abbr
            	String provinceName = map.getBackward(od.getProvAbbr());
            	Province province = new Province(od.getProvAbbr(), newJob);
            	int i = incomeFetcher.searchCity(provinceName);
            	//get the avrIncome for a province
            	province.setProvinceIncome(income.get(i).getAvgIncome());
            	//if the outlook data does not contain a city or the city does not exist in the income dat
            	//city income would base on the province income
            	if(od.getLocation()!=null){	
            		String city = od.getLocation();
                	if(incomeFetcher.searchCity(city)>0){
                		i = incomeFetcher.searchCity(city);	
                	}
            	}

            	City city = new City(province, od.getLocation(), income.get(i).getAvgIncome());
                newJob.addOutlook(province, city, od.getPotential(), od.getTrends());
                jobs.add(newJob);
            } else {
                Job j = jobs.get(index);
            	//get the province name by province abbr
            	String provinceName = map.getBackward(od.getProvAbbr());
                Province province = new Province(od.getProvAbbr(), j);
            	int i = incomeFetcher.searchCity(provinceName);
            	//get the avrIncome for a province
            	province.setProvinceIncome(income.get(i).getAvgIncome());
            	//if the outlook data does not contain a city or the city does not exist in the income data
            	//city income would base on the province income
            	if(od.getLocation()!=null){	
            		String city = od.getLocation();
                	if(incomeFetcher.searchCity(city)>0){
                		i = incomeFetcher.searchCity(city);	
                	}
            	}
            	City city = new City(province, od.getLocation(), income.get(i).getAvgIncome());
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
}