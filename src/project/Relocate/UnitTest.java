package project.Relocate;
import static org.junit.Assert.*;

import java.io.IOException;

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
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCity() {
		
		assertTrue(cityH.getCityName().equals("Hamilton"));
		assertEquals(cityH.getIncome(),30000.00,0.1);
		assertTrue(cityH.getProvince().equals(province));
		assertEquals(cityH.getOutlook().getPotential(),3);
		assertTrue(cityH.getOutlook().getCity().equals(cityH));
		assertTrue(cityH.getOutlook().getTrend().equals("Excellent!!"));
		
	}
	
	@Test
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
	
	@Test
	public void testCityIncome() {
		
		assertEquals(cityIncome.getAvgIncome(),35000.00,0.01);
		assertTrue(cityIncome.getCityName().equals("Vancouver"));
		assertTrue(cityIncome.getIncomes().contains(35000.00));
	}
	
	@Test
	public void testGraphEdge() {
		
		assertEquals(edge.getWeight(),30000.00,0.01);
		assertTrue(edge.getConnection(vertex1).getCityName().equals("Toronto"));
		
	}
	
	@Test
	public void testGraphVertex() {
		
		assertTrue(vertex1.getCityName().equals("Hamilton"));
		assertEquals(vertex2.getOutlook(),3.0,0.01);
		assertFalse(vertex1.getAdj().equals(vertex1));
		assertEquals(vertex2.getCity().getIncome(),30000.00,0.01);
		
	}
	
	
	@Test
	public void testJob() {
		
		assertEquals(job.getAvgPotential(province),2.25,0.01);
		assertTrue(job.getName().equals("finance manager"));
		assertEquals(job.getPotential(province,cityH),3);
		assertTrue(job.getProvinces().contains(province));
		assertTrue(job.getCities(province).contains(cityT));
		assertTrue(job.getCities(province).contains(cityH));

	}
	
	@Test
	public void testOutlook() {
		
		assertTrue(outlook.getTrend().equals("Excellent!!"));
		assertEquals(outlook.getPotential(),3);
		assertTrue(outlook.getCity().equals(cityH));

	}
	
	@Test
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
	
	@Test
	public void testProvinceMap() {
		
		assertTrue(map.getBackward("ON").equals("Ontario"));
		assertTrue(map.getForward("Ontario").equals("ON"));
		
	}
	
	
}
