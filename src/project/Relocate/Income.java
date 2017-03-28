package project.Relocate;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Tasnim Bari Noshin, Jenny Feng Chen
 * 
 *
 */
public class Income {

	ArrayList<CityIncome> cities;

	public Income() throws IOException {
		this.cities = new ArrayList<CityIncome>();
		CityData[] cityData = (new Parser()).getCityDataArray();
		toCityIncome(cityData);
	}

	// transfer CityData to CityIncome
	public void toCityIncome(CityData[] c) {
		int index;
		for (CityData cd : c) {
			CityIncome cI = new CityIncome(cd.getCity());
			index = searchCity(cd.getCity());
			if (index == -1) {
				// adds income to the city
				cI.addIncome(cd.getIncome());
				// adds city to cities
				cities.add(cI);
			} else if(index > -1){
				// if city already exists add income to the city
				CityIncome temp = cities.get(index);
				temp.addIncome(cd.getIncome());
				cities.set(index, temp);
			}
		}
	}

	// search and get the index of the city, if not found return -1
	public int searchCity(String cityName) {
		if (cityName == null) return -2;
		for (int i = 0; i < this.cities.size(); i++) {
			if (this.cities.get(i).getCityName().equals(cityName)) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {

		Income n = new Income();
		for(CityIncome e : n.cities){
			System.out.println(e.getCityName());
		}
	}
}
