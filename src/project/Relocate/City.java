package project.Relocate;

import java.util.ArrayList;

public class City implements Comparable {
	private final String cityName;
	private Province province;
	private Outlook cityOutlook;
	private double income;
	
	City (Province province, String name, double income) {
		this.province = province;
		this.cityName = name;
		this.income = income;
	}
	
	public String getCityName() {
		return this.cityName;
	}
	
	public Province getProvince() {
		return this.province;
	}
	
	public Outlook getOutlook() {
		return this.cityOutlook;
	}
	
	public void setOutlook(int potential, String trend) {
		this.cityOutlook = new Outlook(this, potential, trend);
	}
	
	protected int searchCity(ArrayList<City> cities) {
		int i = 0;
		for (City city : cities) {
			if (this.cityName == city.getCityName())
				return i;
			i++;
		}
		return -1;
	}
	
//	public int hashCode() {
//		return cityName.hashCode();
//	}

	@Override
	public String toString() {
		return "City : " + cityName + "\n"+"Province: "+province.getProvinceCode() +"\n"+ cityOutlook
				+ "\nAverage Median Income: "+income+"\n";
	}
	
	/**
	 * @param that: Type: City
	 * @return If this city has a higher potential than that city, return 1. If lower, return -1. Else: return 0.
	 */
	@Override
	public int compareTo(Object thatC) {
		City that = (City) thatC;
		if (this.cityOutlook.getPotential() == that.getOutlook().getPotential()) return 0;
		if (this.cityOutlook.getPotential() > that.getOutlook().getPotential()) return 1;
		return -1;
	}

	public long getIncome() {
		return (long) income;
	}
}
