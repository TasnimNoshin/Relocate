package project.Relocate;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {

	private static Province province;
	private static City cityH;
	private static City cityT;
	private static Outlook outlook;
	//private static Income income;
	private static Job job;
	private static CityIncome cityIncome;
	private static CityData cityData;
	private static ProvinceMap map;
	//private static CityGraph cityGraph;
	private static GraphVertex vertex1;
	private static GraphVertex vertex2;
	private static GraphEdge edge;
	private static Parser p;
	private static JobArray jobs;
	private static Income i;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		job = new Job("finance manager");
		province = new Province("ON",job);
		cityH  = new City(province,"Hamilton",30000.00);
		cityT  = new City(province,"Toronto",30000.00);
		cityH.setOutlook(3, "Excellent!!");
		outlook = new Outlook(cityH,3,"Excellent!!");
		province.addOutlook(cityT, 2, "Fair");
		province.addOutlook(cityH, 3, "Excellent!!");
		province.setProvinceIncome(31000.00);
		job.addOutlook(province, cityT, 2, "Fair");
		cityIncome = new CityIncome("Vancouver","Birtish Columbia");
		cityIncome.addIncome(35000.00);
		//income = new Income();
		cityData = new CityData(2014," Orillia  - Ontario ","35569","Percentage of persons "
				+ "with total income $35 -000 and over","v61487699",114.24,44.0);
		map = new ProvinceMap();
		//cityGraph = new CityGraph(province.getCities());
		vertex1 = new GraphVertex(cityH, 2.5);
		vertex2 = new GraphVertex(cityT, 3.0);
		vertex1.connect(vertex2, 30000.00);
		edge = new GraphEdge(vertex1, vertex2, 30000.00);
		p = new Parser();
		jobs = new JobArray();
		i = new Income();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Test
	public void testCity() {
		
		assertTrue(cityH.getCityName().equals("Hamilton"));
		assertEquals(cityH.getIncome(),30000.00,0.1);
		assertTrue(cityH.getProvince().equals(province));
		assertEquals(cityH.getOutlook().getPotential(),3);
		assertTrue(cityH.getOutlook().getCity().equals(cityH));
		assertTrue(cityH.getOutlook().getTrend().equals("Excellent!!"));
		
	}
	
//	@Test
	public void testCityData() {
		
		assertTrue(cityData.getCity().equals("Orillia "));
		assertEquals(cityData.getCoordinate(),114.24,0.01);
		assertTrue(cityData.getDataType().equals("Percentage of persons with total income $35 -000 and over"));
		assertTrue(cityData.getGeographicalID().equals("35569"));
		assertEquals(cityData.getIncome(),44.0,0.01);
		assertTrue(cityData.getProvince().equals("Ontario"));
		assertTrue(cityData.getVector().equals("v61487699"));
		assertEquals(cityData.getYear(),2014);
		
	}
	
//	@Test
	public void testCityIncome() {
		
		assertEquals(cityIncome.getAvgIncome(),35000.00,0.01);
		assertTrue(cityIncome.getCityName().equals("Vancouver"));
		assertTrue(cityIncome.getIncomes().contains(35000.00));
	}
	
//	@Test
	public void testGraphEdge() {
		
		assertEquals(edge.getWeight(),30000.00,0.01);
		assertTrue(edge.getConnection(vertex1).getCityName().equals("Toronto"));
		
	}
	
//	@Test
	public void testGraphVertex() {
		
		assertTrue(vertex1.getCityName().equals("Hamilton"));
		assertEquals(vertex2.getOutlook(),3.0,0.01);
		assertFalse(vertex1.getAdj().equals(vertex1));
		assertEquals(vertex2.getCity().getIncome(),30000.00,0.01);
		
	}
	
	
//	@Test
	public void testJob() {
		
		assertEquals(job.getAvgPotential(province),2.25,0.01);
		assertTrue(job.getName().equals("finance manager"));
		assertEquals(job.getPotential(province,cityH),3);
		assertTrue(job.getProvinces().contains(province));
		assertTrue(job.getCities(province).contains(cityT));
		assertTrue(job.getCities(province).contains(cityH));

	}
	
//	@Test
	public void testOutlook() {
		
		assertTrue(outlook.getTrend().equals("Excellent!!"));
		assertEquals(outlook.getPotential(),3);
		assertTrue(outlook.getCity().equals(cityH));

	}
	
//	@Test
	public void testProvince() {
		
		assertEquals(province.getAvgPotential(),2.25,0.01);
		assertEquals(province.getPotential(cityT),2);
		assertEquals(province.getPotential(cityH),3);
		assertTrue(province.getProvinceCode().equals("ON"));
        assertEquals(province.getProvinceIncome(),31000.00,0.01);
        assertTrue(province.getJob().getName().equals("finance manager"));
        assertTrue(province.getCities().contains(cityH));
        assertTrue(province.getCities().contains(cityT));
		
	}
	
//	@Test
	public void testProvinceMap() {
		
		assertTrue(map.getBackward("ON").equals("Ontario"));
		assertTrue(map.getForward("Ontario").equals("ON"));
		
	}
	
	
//	@Test
	public void testParser() throws IOException {
		OutlookData[] data = p.getOutlookDataArray();
		String title = "Senior Government Managers and Officials";
		String location = null;
		String tdate = "15/04/2016";
		int cityID = 0;
		int code = 0;
		String CPP = "Fair";
		String lang = "EN";
		int NOC = 12;
		int pot = 3;
		String prov = "NL";
		int provID = 10;
		String trend = "For the 2015-2017 period, the employment outlook is expected to be fair for Senior Government Managers and Officials (NOC 0012) in Newfoundland and Labrador.   This outlook is the result of an analysis of a number of factors that influence employment prospects in this occupation. Some of the key findings are that:   Employment is expected to decline.  A large number of people are expected to retire.  This occupation has recently experienced moderate levels of unemployment.   Here are some key facts about Senior Government Managers and Officials in Newfoundland and Labrador:  Approximately 550 people worked in this occupation in May 2011.  Senior Government Managers and Officials mainly work in the following sectors:  Local, municipal, regional, aboriginal and other public administration (NAICS 913-919): 57%  Provincial and territorial public administration (NAICS 912): 34%  Federal government public administration (NAICS 911): 7%    86% of Senior Government Managers and Officials work all year, while 14% work only part of the year, compared to 57% and 43% respectively among all occupations.  They fall into the following age groups:  15 to 24: less than 5% compared to 11% for all occupations  25 to 54: 64% compared to 70% for all occupations  55 years and over: 35% compared to 19% for all occupations    The gender distribution of people in this occupation is:   Men: 51% compared to 51% for all occupations  Women: 49% compared to 49% for all occupations    The educational attainment of workers in this occupation is:  No high school diploma: 7% compared to 14% for all occupations  High school diploma or equivalent: 9% compared to 22% for all occupations  Apprenticeship or trades certificate or diploma: 18% compared to 15% for all occupations  College certificate or diploma or university certificate below bachelor's: 27% compared to 30% for all occupations  Bachelor's degree: 27% compared to 13% for all occupations  University certificate, degree or diploma above bachelor level: 12% compared to 7% for all occupations";
		
		assertEquals(data[1].getTitle(),title);
		assertEquals(data[1].getLocation(), location);
		assertEquals(data[1].getTrendsDate(),tdate);
		assertEquals(data[1].getCityID(),cityID);
		assertEquals(data[1].getCode(),code);
		assertEquals(data[1].getCPP(),CPP);
		assertEquals(data[1].getLang(), lang);
		assertEquals(data[1].getNOC(), NOC);
		assertEquals(data[1].getPotential(), pot);
		assertEquals(data[1].getProvAbbr(), prov);
		assertEquals(data[1].getProvID(), provID);
		assertEquals(data[1].getTrends(), trend);
		
	}
	
	

//	@Test
	public void testJobArray() throws IOException {
		ArrayList<Job> theJobs = jobs.getJobArray();
		Province p = theJobs.get(1).getProvinces().get(0);
		City c = theJobs.get(1).getCities().get(0);
		String name = "Senior Government Managers and Officials";
		String city = "Avalon Peninsula";
		int citypot = 3;
		assertEquals(theJobs.get(1).getName(), name);
		assertEquals(theJobs.get(1).getProvinces().get(0).getCities().get(0).getCityName(),city);
		assertEquals(theJobs.get(1).getPotential(p, c),citypot);
		assertEquals(theJobs.get(1).getCities().get(0).getCityName(),city);
		assertEquals(theJobs.get(1).getCities(p).get(0).getCityName(),city);
		
	}
	
	
	@Test
	public void testIncome_cityIncome() {
		ArrayList<CityIncome> u = i.getCities();
		CityIncome k = u.get(2);
		ArrayList<Double> t = k.getIncomes();
		assertEquals(k.getCityName(), "St. John's ");
		assertTrue(k.getAvgIncome() == 29043.555555555555);
		assertTrue(t.get(3) == 27900.0);
	}
	
	
}
