package project.Relocate;

/**
 * 
 * @author Umme Salma Gadriwala
 * OutlookADT is used by location.
 * The idea is that for each location for a particular job, there are multiple outlooks.
 *
 */

public class Outlook {
	private int potential;
	private String trend;
	private City city;
	
	Outlook(City city, int potential, String trend) {
		this.city = city;
		this.potential = potential;
		this.trend = trend;
	}
	
	public int getPotential() { return this.potential; }
	
	public String getTrend() { return this.trend; }
	
	public City getCity() { return this.city; }
	
	@Override
	public String toString() {
		return "<Outlook: " + potential + "; Trend: " + trend + ">";
	}
}
