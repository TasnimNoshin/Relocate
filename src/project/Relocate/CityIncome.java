package project.Relocate;

import java.util.ArrayList;

//cityName represents one city only
//cityName also accept a province name whne the city name is null
public class CityIncome {

	private final String cityName;
	private String province;
	private ArrayList<Double> incomes;
	private double avgIncome; 

	CityIncome(String cityName, String province) {
		this.cityName = cityName;
		this.province = province;
		this.incomes = new ArrayList<Double>();
		this.avgIncome = 0;
	}

	public String getCityName() {
		return this.cityName;
	}

	public ArrayList<Double> getIncomes() {
		return this.incomes;
	}

	//get income and add it to the list of incomes for the city
	public void addIncome(double income) {
		incomes.add(income);
		this.avgIncome = (avgIncome*(this.incomes.size()-1)+income)/(this.incomes.size());
	}
	
	public double getAvgIncome(){
		return this.avgIncome;
	}

}
