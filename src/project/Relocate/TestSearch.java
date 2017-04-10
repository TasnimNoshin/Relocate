package project.Relocate;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class TestSearch {

	@Test
	/*this test tests to see if the job is being found properly in the array of jobs that is created, and thus checks if the rest of the information the
	full version of this method returns is correct
	the correctness of the information given by the full method is tested in the unit tests for the City module*/
	public void jobTest() throws IOException {
		Searcher s = new Searcher();
		File in = new File("data/testText.txt");
		boolean correct = true;
		Scanner input = new Scanner(in);
		int[] indeces = new int[13];
		int count = 0;
		
		input.nextLine();
		//creating the list of correct indeces
		//putting it in the file like this ensures that the tests can be easily changed and nothing is hardcoded here
		String[] indexList = input.nextLine().split(",");
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] args = line.split(",");
			int index = s.searchCityTest(args[0]);
			indeces[count] = index;
			count ++;
		}
		input.close();
		
		for (int i = 0; i < indexList.length; i++) {
			if (indeces[i] != Integer.parseInt(indexList[i]))
				correct = false;
		}
		assertTrue(correct);
	}

	
	@Test
	/* This method checks each of the 5 cities that the full version of this method returns to the user
	 * and sees if the province code matches what it should be from the input specified in the text file
	 */
	public void provinceTest() throws Exception {
		Searcher s = new Searcher();
		File in = new File("data/testText.txt");
		boolean correct = true;
		Scanner input = new Scanner(in);
		
		input.nextLine();
		input.nextLine();
		while (input.hasNext()) {
			String line = input.nextLine();
			String[] args = line.split(",");
			ArrayList<City> results = s.searchProvinceCityTest(args[0],args[1]);
			for (City city : results) {
				if (!city.getProvince().getProvinceCode().equals(args[1])) {
					correct = false;
				}
			if (results.size() == 0) 
				correct = true;
			}
		}
		input.close();
		assertTrue(correct);
	}
	
	
	@Test
	/* This method checks to see the 5 jobs returned to the user to see
	 * if the income is in the correct range from the number specified in the text file
	 */
	public void incomeTest() throws Exception {
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
			for (City city : results) {
				if (city.getIncome() > Double.parseDouble(args[2])) {
					correct = false;
				}
			}
			if (results.size() == 0) 
				correct = true;
		}
		input.close();
		assertTrue(correct);
	}
}
