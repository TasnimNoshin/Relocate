package project.Relocate;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGraph {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
		Searcher s = new Searcher();
		File in = new File("data/testText.txt");
		boolean correct = true;
		Scanner input = new Scanner(in);
		
		input.nextLine();
		input.nextLine();
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] args = line.split(",");
			ArrayList<City> results = s.searchIncomeCityTest(args[0], args[1], args[2]);
			CityGraph G = new CityGraph(results);
			for (City city : results) {
				ArrayList<City> relCities = G.getRelatedCitiesTest(city);
					for (City c : relCities) {
						if ((Integer)c.getOutlook().getPotential() != (Integer)city.getOutlook().getPotential()) {
							System.out.println(c.getOutlook().getPotential());
							System.out.println(city.getOutlook().getPotential());
							correct = false;
						}
						System.out.println();
					}
					if (relCities.size() == 0)
						correct = true;
			}
			System.out.println();
		}
		assertTrue(correct);
	}

}
