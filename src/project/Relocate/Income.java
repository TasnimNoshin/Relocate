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

	private ArrayList<CityIncome> cities;

	public Income() throws IOException {
		this.cities = new ArrayList<CityIncome>();
		CityData[] cityData = (new Parser()).getCityDataArray();
		toCityIncome(cityData);
	}

	// transfer CityData to CityIncome
	public void toCityIncome(CityData[] c) {
		int index;
		for (CityData cd : c) {
			CityIncome cI = new CityIncome(cd.getCity(), cd.getProvince());
			index = searchCity(cd.getCity());
			if (index == -1 && (cd.getDataType().toLowerCase().contains("median total income") 
					|| cd.getDataType().toLowerCase().contains("median employment income")) 
					&& !cd.getDataType().contains("of")) {
				// adds income to the city
				cI.addIncome(cd.getIncome());
				// adds city to cities
				cities.add(cI);
			} else if(index > -1 && (cd.getDataType().toLowerCase().contains("median total income")
					|| cd.getDataType().toLowerCase().contains("median employment income"))
					&& !cd.getDataType().contains("of")){
				// if city already exists add income to the city
				CityIncome temp = cities.get(index);
				temp.addIncome(cd.getIncome());
				cities.set(index, temp);
			}else if(index == -2 && (cd.getDataType().toLowerCase().contains("median total income")
					|| cd.getDataType().toLowerCase().contains("median employment income"))
					&& !cd.getDataType().contains("of")){
				cI = new CityIncome(cd.getProvince(), cd.getProvince());
				//this is searching province
				index = searchCity(cd.getProvince());
				if(index == -1){
					cI.addIncome(cd.getIncome());
					cities.add(cI);
				}else{
					CityIncome temp = cities.get(index);
					temp.addIncome(cd.getIncome());
					cities.set(index, temp);
				}
			}
		}
	}

	// search and get the index of the city, if not found return -1
	public int searchCity(String cityName) {
		
		if (cityName == null) return -2;
		for (int i = 0; i < this.cities.size(); i++) {
			if (this.cities.get(i).getCityName().equals(cityName) || this.cities.get(i).getCityName().contains(cityName)
					|| cityName.contains(this.cities.get(i).getCityName())) {
				return i;
			}
		}

		return -1;
	}
	
	public ArrayList<CityIncome> getCities() {
		return cities;
	}
	
//	public static void main(String[] args) throws IOException {
//
//		Income n = new Income();
//		for(CityIncome e : n.cities){
//			System.out.println(e.getCityName()+" "+e.getAvgIncome());
//		}
//	}
}
